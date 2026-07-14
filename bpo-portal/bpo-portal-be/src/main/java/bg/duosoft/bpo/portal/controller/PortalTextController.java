package bg.duosoft.bpo.portal.controller;

import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.common.web.controller.CrudController;
import bg.duosoft.bpo.portal.dto.PortalText;
import bg.duosoft.bpo.portal.service.PortalTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/portal-texts")
public class PortalTextController extends CrudController<String, PortalText> {

    private final PortalTextService portalTextService;

    @GetMapping("/dynamic")
    public PortalText getActivePortalText() {
        return this.portalTextService.getActivePortalText();
    }

    @Override
    protected CrudServiceBase<String, PortalText> getService() {
        return this.portalTextService;
    }

    @Override
    public String getEditRole() {
        return "";
    }

    @Override
    public String getAccessRole() {
        return "";
    }

    @Override
    protected Set<ENDPOINT> getSupportedEndpoints() {
        return Set.of(ENDPOINT.GET);
    }

    @Override
    public boolean getAccessIsSecured() {
        return false;
    }
}
