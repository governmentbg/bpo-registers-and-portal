package bg.duosoft.bpo.portal.service;

import bg.duosoft.bpo.portal.dto.Breadcrumb;
import java.util.Map;

public interface BreadcrumbService {
    Map<String, Breadcrumb> getDynamicBreadcrumbData();
}
