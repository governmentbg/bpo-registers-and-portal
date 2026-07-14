package bg.duosoft.bpo.registers.controller.v1.nomenclature;

import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.common.web.controller.CrudController;
import bg.duosoft.bpo.publik.core.dto.nomenclature.ObjectSubtypeDTO;
import bg.duosoft.bpo.publik.core.service.nomenclature.ObjectSubtypeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nomenclature/objectsubtype")
public class ObjectSubtypeController extends CrudController<String, ObjectSubtypeDTO> {

    private final ObjectSubtypeService objectSubtypeService;

    @GetMapping("/objecttype")
    @Operation(description = "Select all object subtypes by selected object types")
    public List<ObjectSubtypeDTO> selectByObjectTypeRange(@RequestParam("objectTypes") List<String> objectTypes) {
        return objectSubtypeService.selectByObjectTypes(objectTypes);
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
        return Set.of(ENDPOINT.LIST);
    }

    @Override
    public boolean getAccessIsSecured() {
        return false;
    }

    @Override
    protected CrudServiceBase<String, ObjectSubtypeDTO> getService() {
        return objectSubtypeService;
    }

}
