package bg.duosoft.bpo.publik.core.enums;

/**
 * User: ggeorgiev
 * Date: 31.03.2022
 * Time: 10:56
 */
public enum PriorityType {
    CONVENTIONAL("C"),
    EXHIBITION("E");

    PriorityType(String code) {
        this.code = code;
    }

    private String code;

    public String code() {
        return code;
    }
}
