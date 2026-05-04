package bg.duosoft.bpo.publik.core.enums;

import java.util.Arrays;
import java.util.Objects;

public enum ObjectType {

    MARK("N"),
    PATENT("P"),
    EU_PATENT("T"),
    UTILITY_MODEL("U"),
    GEOGRAPHICAL_INDICATIONS("Г"),
    DESIGN("Д"),
    SPC("S"),
    PLANTS_AND_BREEDS("С");

    ObjectType(String code) {
        this.code = code;
    }

    private String code;

    public String code() {
        return code;
    }

    public static ObjectType selectByCode(String code) {
        return Arrays.stream(ObjectType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[ObjectType] Unknown object type code! Code: " + code));
    }
}
