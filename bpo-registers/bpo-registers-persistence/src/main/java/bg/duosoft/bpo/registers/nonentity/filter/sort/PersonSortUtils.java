package bg.duosoft.bpo.registers.nonentity.filter.sort;

import java.util.HashMap;
import java.util.Map;

/**
 * User: ggeorgiev
 * Date: 07.11.2024
 * Time: 15:46
 */
public class PersonSortUtils {
    public static final String ID = "id";
    public static final String NAME = "name";
    private static final Map<String, String> map = new HashMap<>();
    static {
        map.put(ID, "r.id");
        map.put(NAME, "r.name");
    }

    public static Map<String, String> sorterColumnMap() {
        return map;
    }
}
