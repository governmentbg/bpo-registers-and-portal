package bg.duosoft.bpo.common.util.integer;

public class IntegerUtils {
    public static Integer parseInteger(String value, Integer defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
