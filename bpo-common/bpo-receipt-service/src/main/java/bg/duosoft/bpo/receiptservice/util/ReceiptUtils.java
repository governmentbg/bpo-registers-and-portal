package bg.duosoft.bpo.receiptservice.util;

import java.util.Base64;
import java.util.Objects;

public class ReceiptUtils {
    public static String generateImageUrlByByteArray(byte[] content) throws Exception {
        if (Objects.isNull(content) || content.length == 0) {
            throw new Exception("Byte content is empty!");
        }
        String base64Image = Base64.getEncoder().encodeToString(content);
        return "data:image/jpeg;base64," + base64Image;
    }
}
