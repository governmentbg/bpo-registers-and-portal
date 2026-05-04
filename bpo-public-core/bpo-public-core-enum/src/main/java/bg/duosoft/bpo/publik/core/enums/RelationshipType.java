package bg.duosoft.bpo.publik.core.enums;

/**
 * User: ggeorgiev
 * Date: 09.06.2025
 * Time: 14:28
 */
public enum RelationshipType {
    SPC("SPC"),
    DESIGN("Д"),
    PARALLEL_PATENT("ОП"),
    DIVIDED_DESIGN("РД"),
    DIVIDED_EUROPEAN_PATENT("РЕП"),
    DIVIDED_MARK("РНМ"),
    DIVIDED_NATIONAL_PATENT("РНП"),
    TRANSFORMED_MARK("ТМ"),
    TRANSFORMED_PATENT("ТП"),
    DIVIDED_BREED_VARIETY("РСП"),
    REPLACED_INTERNATIONAL_REGISTRATION("ЗАМ"),
    SENIORITY("СТР")

    ;
    private String code;
    private RelationshipType(String code) {
        this.code = code;
    }
    public String code() {
        return code;
    }
}
