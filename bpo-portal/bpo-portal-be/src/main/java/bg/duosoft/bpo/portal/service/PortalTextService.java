package bg.duosoft.bpo.portal.service;

import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.portal.dto.PortalText;

public interface PortalTextService extends CrudServiceBase<String, PortalText> {

    PortalText getActivePortalText();
}
