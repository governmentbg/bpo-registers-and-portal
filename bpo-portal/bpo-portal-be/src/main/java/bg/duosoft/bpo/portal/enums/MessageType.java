package bg.duosoft.bpo.portal.enums;

import java.util.Arrays;


public enum MessageType {
    FEEDBACK,
    IP_OBJECT_ERROR;

    public static Boolean isEnumValue(String value) {
        return Arrays.stream(MessageType.values())
                .anyMatch(v -> v.name().equals(value));
    }
}
