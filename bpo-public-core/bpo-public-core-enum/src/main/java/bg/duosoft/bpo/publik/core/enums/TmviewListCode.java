package bg.duosoft.bpo.publik.core.enums;

/**
 * User: ggeorgiev
 * Date: 07.02.2024
 * Time: 11:31
 */
public enum TmviewListCode {
    FILED("Filed"), EXPIRED("Expired"), REGISTERED("Registered"), ENDED("Ended");
    private String code;
    private TmviewListCode(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
