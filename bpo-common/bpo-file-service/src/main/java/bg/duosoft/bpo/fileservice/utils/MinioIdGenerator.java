package bg.duosoft.bpo.fileservice.utils;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

public class MinioIdGenerator {

    public static String generateFileId() {
        String uuid = UUID.randomUUID().toString();
        String secret = generateBase64UrlEncodedRandomString(16);
        return uuid + secret;
    }

    private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

    private static String generateBase64UrlEncodedRandomString(int length){
        SecureRandom secureRandom = new SecureRandom();
        byte[] secretBytes = new byte[length];
        secureRandom.nextBytes(secretBytes);
        String secret = encoder.encodeToString(secretBytes);
        return secret;
    }
}
