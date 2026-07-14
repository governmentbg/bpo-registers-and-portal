package bg.duosoft.bpo.publik.core.enums;

public enum OperationCode {
    INSERT("Insert"),
    IGNORE("Ignore"),
    DELETE("Delete");
    OperationCode(String code) {
        this.code = code;
    }
    private String code;
    public String code() {
        return code;
    }
}
