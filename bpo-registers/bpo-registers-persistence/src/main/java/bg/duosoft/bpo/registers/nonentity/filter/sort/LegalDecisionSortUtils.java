package bg.duosoft.bpo.registers.nonentity.filter.sort;

import java.util.HashMap;
import java.util.Map;

public class LegalDecisionSortUtils {
    private static final String OBJECT_ID_SORT_COLUMN = "objectId";
    private static final String DOCUMENT_TYPE_SORT_COLUMN = "documentType";
    private static final String DOCUMENT_DATE_SORT_COLUMN = "documentDate";
    private static final String DOCUMENT_NUMBER_SORT_COLUMN = "documentNumber";
    private static final String OBJECT_TYPE_SORT_COLUMN = "objectType";
    private static final String TITLE = "title";

    public static Map<String, String> sorterColumnMap() {
        Map<String, String> map = new HashMap<>();
        map.put(OBJECT_ID_SORT_COLUMN, "ld.objectId");
        map.put(DOCUMENT_TYPE_SORT_COLUMN, "ld.documentType.name");
        map.put(DOCUMENT_DATE_SORT_COLUMN, "ld.documentDate");
        map.put(DOCUMENT_NUMBER_SORT_COLUMN, "ld.documentNumber");
        map.put(OBJECT_TYPE_SORT_COLUMN, "ld.objectType.id");
        map.put(TITLE, "ld.title");

        return map;
    }
}
