package bg.duosoft.bpo.common.imageconverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * User: ggeorgiev
 * Date: 18.06.2025
 * Time: 13:21
 */
public class ImageConverter {
    public static byte[] convertImage(byte[] source, String outputFormat) {
        try {
            BufferedImage inputImage = ImageIO.read(new ByteArrayInputStream(source));

            BufferedImage outputImage;
            // If output is JPEG, strip alpha
            if ("jpg".equalsIgnoreCase(outputFormat) || "jpeg".equalsIgnoreCase(outputFormat)) {
                outputImage = new BufferedImage(
                        inputImage.getWidth(), inputImage.getHeight(),
                        BufferedImage.TYPE_INT_RGB);

                Graphics2D g2d = outputImage.createGraphics();
                g2d.setColor(Color.WHITE); // White background
                g2d.fillRect(0, 0, outputImage.getWidth(), outputImage.getHeight());
                g2d.drawImage(inputImage, 0, 0, null);
                g2d.dispose();
            } else {
                // Preserve alpha for formats like PNG
                outputImage = inputImage;
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            // Write to output file
            ImageIO.write(outputImage, outputFormat, os);
            return os.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
