package bg.duosoft.bpo.publik.core.enums;

import java.util.Arrays;

public enum CivilIdType {
    NATIONAL_ID,
    FOREIGN_ID,
    PERSONAL_DOCUMENT;

    public String value() {
        return name();
    }

    public static CivilIdType selectByName(String name) {
        CivilIdType result = Arrays.stream(CivilIdType.values())
                .filter(c -> c.name().equals(name))
                .findFirst()
                .orElse(null);

        return result;
    }

}
