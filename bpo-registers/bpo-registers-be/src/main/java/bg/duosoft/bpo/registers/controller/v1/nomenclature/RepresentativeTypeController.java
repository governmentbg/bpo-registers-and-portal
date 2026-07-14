package bg.duosoft.bpo.registers.controller.v1.nomenclature;

import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.common.web.controller.CrudController;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RepresentativeTypeDTO;
import bg.duosoft.bpo.publik.core.service.nomenclature.RepresentativeTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 14.03.2024
 * Time: 17:07
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nomenclature/reptype")
public class RepresentativeTypeController extends CrudController<String, RepresentativeTypeDTO> {

    private final RepresentativeTypeService representativeTypeService;
    @Override
    public String getEditRole() {
        return null;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @Override
    protected CrudServiceBase<String, RepresentativeTypeDTO> getService() {
        return representativeTypeService;
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
