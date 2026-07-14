package bg.duosoft.bpo.publik.core.enums;

/**
 * User: ggeorgiev
 * Date: 16.07.2025
 * Time: 13:09
 */
public enum AgentStatus {
    ACTIVE(1),
    DELETED(2),
    TEMPORARY_DELETED(3);
    private final int code;
    private AgentStatus(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
