package bg.duosoft.bpo.registers.util.receipt;

import java.util.Locale;

public class CommonReceiptUtils {

    public static final String PRIMARY_LANGUAGE = "bg";
    public static final String SECONDARY_LANGUAGE = "en";

    public static void validateLanguage(String lang) {
        if (!lang.equalsIgnoreCase(PRIMARY_LANGUAGE) && !lang.equalsIgnoreCase(SECONDARY_LANGUAGE)) {
            throw new IllegalArgumentException("Unrecognized language for receipt!");
        }
    }

    public static boolean isPrimaryLang(String lang) {
        return lang.equalsIgnoreCase(PRIMARY_LANGUAGE);
    }

    public static boolean isPrimaryLang(Locale locale) {
        return locale.getLanguage().equalsIgnoreCase(PRIMARY_LANGUAGE);
    }

    public static boolean isSecondaryLang(String lang) {
        return lang.equalsIgnoreCase(SECONDARY_LANGUAGE);
    }

    public static boolean isSecondaryLang(Locale locale) {
        return locale.getLanguage().equalsIgnoreCase(SECONDARY_LANGUAGE);
    }

    private CommonReceiptUtils() {
    }
}
