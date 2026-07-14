package bg.duosoft.bpo.excelgenerator.data;

/**
 * User: ggeorgiev
 * Date: 25.10.2024
 * Time: 17:42
 */
public enum BorderStyle {
    NONE(0),
    THIN(1),
    MEDIUM(2),
    DASHED(3),
    DOTTED(4),
    THICK(5),
    DOUBLE(6),
    HAIR(7),
    MEDIUM_DASHED(8),
    DASH_DOT(9),
    MEDIUM_DASH_DOT(10),
    DASH_DOT_DOT(11),
    MEDIUM_DASH_DOT_DOT(12),
    SLANTED_DASH_DOT(13);
    private short code;
    private BorderStyle(final int value) {
        this.code = (short)value;
    }
    public short code() {return code;}
}
