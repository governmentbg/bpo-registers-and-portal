package bg.duosoft.bpo.publik.core.enums;

public enum RecordalGroupType {
    OPPOSITION("OPPOSITION"),
    CANCELLATION("CANCELLATION"),
    RECORDAL("RECORDAL");


    RecordalGroupType(String groupTypeId) {
        this.groupTypeId = groupTypeId;
    }

    private String groupTypeId;

    public String groupTypeId() {
        return groupTypeId;
    }
}
