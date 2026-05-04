package bg.duosoft.bpo.publik.core.enums;

/**
 * User: ggeorgiev
 * Date: 07.11.2024
 * Time: 17:54
 */
public enum LegalType {
    LEGAL_TYPE_PHYSICAL(0), LEGAL_TYPE_LEGAL(1);
    private int code;
    private LegalType(int code) {
        this.code = code;
    }
    public int code() {
        return code;
    }
}
