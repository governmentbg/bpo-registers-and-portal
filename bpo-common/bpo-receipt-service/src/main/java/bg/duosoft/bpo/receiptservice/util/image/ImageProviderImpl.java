package bg.duosoft.bpo.receiptservice.util.image;

import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import bg.duosoft.bpo.fileservice.service.FileStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImageProviderImpl implements ImageProvider {

    private final FileStoreService fileStoreService;

    @Override
    public BufferedImage getImage(String bucket, String fullPath) {
        FileStoreEntryBaseDTO fileStoreEntry = null;
        try {
            fileStoreEntry = fileStoreService.getFileStoreEntryDetailsAndContent(bucket, fullPath);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        if (Objects.isNull(fileStoreEntry)) {
            return getFallbackImage();
        }

        String fileFormat = fileStoreEntry.getContentType();
        if (fileFormat == null) {
            fileFormat = "image/jpg";
        }

        try {
            if (fileFormat.startsWith("image/")) {
                return ImageIO.read(new ByteArrayInputStream(fileStoreEntry.getContent()));
            }
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        return getFallbackImage();
    }

    private BufferedImage getFallbackImage() {
        int width = 200;
        int height = 100;
        BufferedImage fallback = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = fallback.createGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.white);
        graphics.fillRect(4, 4, width - 8, height - 8);
        String text = "Error in image extraction";
        graphics.setFont(new Font("Serif", Font.BOLD, 12));
        FontMetrics metrics = graphics.getFontMetrics(graphics.getFont());
        int x = (width - metrics.stringWidth(text)) / 2;
        int y = ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        graphics.setColor(Color.red);
        graphics.drawString(text, x, y);
        graphics.dispose();
        return fallback;
    }

}
