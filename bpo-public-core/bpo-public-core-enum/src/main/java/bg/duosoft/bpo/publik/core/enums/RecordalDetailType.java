package bg.duosoft.bpo.publik.core.enums;

import java.util.Arrays;

public enum RecordalDetailType {
    DECISION("DECISION");

    RecordalDetailType(String code) {
        this.code = code;
    }

    private String code;

    public String code() {
        return code;
    }


    public static RecordalDetailType selectByCode(String code) {
        RecordalDetailType result = Arrays.stream(
                        RecordalDetailType.values())
                .filter(c -> c.code().equals(code))
                .findFirst()
                .orElse(null);

        return result;
    }
}
