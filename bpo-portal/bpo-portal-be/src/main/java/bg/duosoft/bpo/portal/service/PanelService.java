package bg.duosoft.bpo.portal.service;

import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.portal.dto.Section;
import bg.duosoft.bpo.portal.dto.ServiceGroup;

public interface PanelService extends CrudServiceBase<String, Section> {

    ServiceGroup getPanelAndServicesByPanelId(String panelId);

    Section getPanelAndPanelChildren(String panelId);
}
