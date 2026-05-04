package bg.duosoft.bpo.registers.service.ipobject.impl.search.util;

import bg.duosoft.bpo.common.service.util.SearchSortUtil;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;

@Component
public class IpObjectSearchSortUtil implements SearchSortUtil {
    public static final String SORT_COLUMN_OBJECT_ID = "objectId";
    public static final String SORT_COLUMN_FILING_DATE = "filingDate";
    public static final String SORT_COLUMN_REGISTRATION_NUMBER = "registrationNumber";
    public static final String SORT_COLUMN_TITLE = "title";
    public static final String SORT_COLUMN_STATUS = "statusId";
    public static final String SORT_COLUMN_MAIN_OWNER = "mainOwner";

    private Map<String, String> map;

    public IpObjectSearchSortUtil() {
        map = new HashMap<>();
        map.put(SORT_COLUMN_OBJECT_ID, IpObjectSearchFields.FIELD_OBJECT_FILE_ID);
        map.put(SORT_COLUMN_FILING_DATE, IpObjectSearchFields.FIELD_OBJECT_FILING_DATE);
        map.put(SORT_COLUMN_REGISTRATION_NUMBER, IpObjectSearchFields.FIELD_OBJECT_REGISTRATION_NUMBER);
        map.put(SORT_COLUMN_TITLE, IpObjectSearchFields.FIELD_OBJECT_TITLE_SORT);
        map.put(SORT_COLUMN_STATUS, IpObjectSearchFields.FIELD_OBJECT_STATUS_ID);
        map.put(SORT_COLUMN_MAIN_OWNER, IpObjectSearchFields.FIELD_OBJECT_MAIN_OWNER_NAME);
    }

    @Override
    public String toSearchSortField(String searchResultColumn) {
        return map.get(searchResultColumn);
    }
}
