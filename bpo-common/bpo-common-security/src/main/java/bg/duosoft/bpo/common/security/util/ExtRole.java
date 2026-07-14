package bg.duosoft.bpo.common.security.util;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.09.2025
 * Time: 16:15
 */
public enum ExtRole {
    TM("ROLE_TRADEMARKS"),
    GI("ROLE_GEO_INDICATIONS"),
    DS("ROLE_DESIGNS"),
    PT("ROLE_PATENTS"),
    UM("ROLE_UTILITY_MODELS"),
    EP("ROLE_EU_PATENTS"),
    SPC("ROLE_SPC"),
    SV("ROLE_PLANTS_BREEDS"),
    IS("ROLE_TOPOLOGIES"),
    OL_SIG("ROLE_SIGNALS"),
    CORRESPONDENCE_READ("ROLE_CORRESPONDENCE_READ_ONLY"),
    CORRESPONDENCE_WRITE("ROLE_CORRESPONDENCE_READ_WRITE"),
    PAYMENTS("ROLE_PAYMENTS"),
    SIGNATURES("ROLE_SIGNATURES"),;

    private String value;

    ExtRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ExtRole fromValue(String value) {
        return Arrays.stream(ExtRole.values()).filter(el -> el.getValue().equals(value)).findFirst().orElse(null);
    }
}
