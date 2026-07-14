package bg.duosoft.bpo.publik.core.controller.v1.nomenclature;

import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.common.web.controller.CrudController;
import bg.duosoft.bpo.publik.core.dto.nomenclature.LegalDecisionGroundTypeDTO;
import bg.duosoft.bpo.publik.core.service.nomenclature.LegalDecisionGroundTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nomenclature/legal-decision-ground-type")
public class LegalDecisionGroundTypeController extends CrudController<Integer, LegalDecisionGroundTypeDTO> {

    private final LegalDecisionGroundTypeService service;


    @Override
    public String getEditRole() {
        return null;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @Override
    protected CrudServiceBase<Integer, LegalDecisionGroundTypeDTO> getService() {
        return service;
    }


    @Override
    protected Set<ENDPOINT> getSupportedEndpoints() {
        return Set.of(ENDPOINT.LIST);
    }

    @Override
    public boolean getAccessIsSecured() {
        return false;
    }
}
