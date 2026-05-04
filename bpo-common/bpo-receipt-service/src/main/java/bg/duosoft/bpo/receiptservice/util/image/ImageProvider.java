package bg.duosoft.bpo.receiptservice.util.image;

import java.awt.image.BufferedImage;

public interface ImageProvider {
    BufferedImage getImage(String bucket, String fullPath);
}
