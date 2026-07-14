package bg.duosoft.bpo.publik.core.enums;

/**
 * User: ggeorgiev
 * Date: 18.03.2022
 * Time: 15:22
 */
public enum AttachmentType {
    IMAGE("IMAGE"),
    THUMBNAIL("THUMB"),
    TMVIEW_PICTURE("TMVP"),
    VIDEO("VIDEO"),
    AUDIO("AUDIO");


    AttachmentType(String code) {
        this.code = code;
    }

    private String code;

    public String code() {
        return code;
    }
}
