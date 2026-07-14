package bg.duosoft.bpo.common.util.regex;

public class RegexUtils {
    public static String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static String PHONE_REGEX = "^\\+?[0-9]{1,4}?[-.\\s]?\\(?[0-9]{1,3}?\\)?[-.\\s]?[0-9]{3}[-.\\s]?[0-9]{4}$";
}
