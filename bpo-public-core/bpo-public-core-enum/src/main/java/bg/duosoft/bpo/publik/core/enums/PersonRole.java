package bg.duosoft.bpo.publik.core.enums;

public enum PersonRole {

    OWNER("OWNER"),
    REPRESENTATIVE("REPRESENTATIVE"),
    INVENTOR("INVENTOR"),
    CORRESPONDENCE_ADDRESS("CORRESPONDENCE_ADDRESS");


    PersonRole(String code) {
        this.code = code;
    }

    private String code;

    public String code() {
        return code;
    }
}
