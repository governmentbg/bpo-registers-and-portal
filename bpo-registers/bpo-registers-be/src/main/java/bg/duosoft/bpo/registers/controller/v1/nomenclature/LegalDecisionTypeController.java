package bg.duosoft.bpo.registers.controller.v1.nomenclature;

import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.common.web.controller.CrudController;
import bg.duosoft.bpo.publik.core.dto.nomenclature.LegalDecisionTypeDTO;
import bg.duosoft.bpo.publik.core.service.nomenclature.LegalDecisionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nomenclature/legal-decision-type")
public class LegalDecisionTypeController extends CrudController<Integer, LegalDecisionTypeDTO> {

    private final LegalDecisionTypeService legalDecisionTypeService;

    @Override
    public String getEditRole() {
        return null;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @Override
    protected CrudServiceBase<Integer, LegalDecisionTypeDTO> getService() {
        return legalDecisionTypeService;
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
