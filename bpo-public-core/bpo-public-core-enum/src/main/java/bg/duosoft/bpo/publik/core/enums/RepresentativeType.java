package bg.duosoft.bpo.publik.core.enums;

import java.util.Arrays;
import java.util.List;

public enum RepresentativeType {

    AGENT("AG"),
    COMPANY("PC"),
    PARTNERSHIP("PP"),
    INTELLECTUAL_PROPERTY_REPRESENTATIVE("IP"),
    TEMPORARY_AGENT("TS"),
    LAWYER_COMPANY("LC"),
    LAWYER_PARTNERSHIP("LP"),
    LAWYER("LA"),
    PATENT_SPECIALIST("RE"),
    INTERNATIONAL_REPRESENTATIVE("IR"),
    ;
    RepresentativeType(String code) {
        this.code = code;
    }
    public static List<RepresentativeType> REAL_REPRESENTATIVE_TYPES = Arrays.asList(RepresentativeType.AGENT, RepresentativeType.PARTNERSHIP, RepresentativeType.LAWYER, RepresentativeType.COMPANY, RepresentativeType.LAWYER_PARTNERSHIP, RepresentativeType.LAWYER_COMPANY, RepresentativeType.TEMPORARY_AGENT);
    private String code;

    public String code() {
        return code;
    }

    public static RepresentativeType selectByName(String name) {
        RepresentativeType result = Arrays.stream(
                        RepresentativeType.values())
                .filter(c -> c.name().equals(name))
                .findFirst()
                .orElse(null);

        return result;
    }
}
