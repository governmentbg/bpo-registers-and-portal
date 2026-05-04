package bg.duosoft.bpo.portal.controller;

import bg.duosoft.bpo.portal.dto.Section;
import bg.duosoft.bpo.portal.dto.ServiceGroup;
import bg.duosoft.bpo.portal.service.PanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/panels")
public class PanelController {

    private final PanelService panelService;

    @GetMapping("/services/{panelId}")
    public ServiceGroup getPanelAndServicesByPanelId(@PathVariable("panelId") String panelId) {
        return this.panelService.getPanelAndServicesByPanelId(panelId);
    }

    @GetMapping("/{panelId}")
    public Section getPanelAndPanelChildren(@PathVariable("panelId") String panelId) {
        return this.panelService.getPanelAndPanelChildren(panelId);
    }

}
