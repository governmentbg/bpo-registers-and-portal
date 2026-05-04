package bg.duosoft.bpo.publik.core.enums;

import java.util.Arrays;

public enum AgentSpecialityType {
    MARKS_GEO_INDICATIONS_DESIGNS("M"),
    PATENTS_UTILITY_MODELS("P"),
    BOTH("MP");

    AgentSpecialityType(String code) {
        this.code = code;
    }

    private String code;

    public String value() {
        return name();
    }
    public String code() {
        return code;
    }

    public static AgentSpecialityType selectByName(String name) {
        AgentSpecialityType result = Arrays.stream(AgentSpecialityType.values())
                .filter(c -> c.name().equals(name))
                .findFirst()
                .orElse(null);

        return result;
    }
}
