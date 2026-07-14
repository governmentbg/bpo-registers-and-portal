package bg.duosoft.bpo.registers.nonentity.filter.sort;

import java.util.HashMap;
import java.util.Map;

/**
 * User: ggeorgiev
 * Date: 11.11.2024
 * Time: 14:10
 */
public class VwAgentSortUtils {
    private static final Map<String, String> map = new HashMap<>();
    static {
        map.put("id", "id");
        map.put("name", "ag.person.name");
    }
    public static Map<String, String> sorterColumnMap() {
        return map;
    }
}
