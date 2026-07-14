package bg.duosoft.bpo.portal.controller;

import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.common.web.controller.CrudController;
import bg.duosoft.bpo.portal.dto.ServiceDefinition;
import bg.duosoft.bpo.portal.service.ServiceDefinitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/service-definitions")
public class ServiceDefinitionController extends CrudController<String, ServiceDefinition> {

    private final ServiceDefinitionService serviceDefinitionService;

    @Override
    protected CrudServiceBase<String, ServiceDefinition> getService() {
        return this.serviceDefinitionService;
    }

    @Override
    public String getEditRole() {
        return null;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @Override
    protected Set<ENDPOINT> getSupportedEndpoints() {
        return Set.of(ENDPOINT.GET, ENDPOINT.LIST);
    }

    @Override
    public boolean getAccessIsSecured() {
        return false;
    }
}
