package bg.duosoft.bpo.publik.core.controller.v1.nomenclature;

import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.common.web.controller.CrudController;
import bg.duosoft.bpo.publik.core.dto.nomenclature.MarkKindDTO;
import bg.duosoft.bpo.publik.core.service.nomenclature.MarkKindService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.02.2024
 * Time: 13:46
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nomenclature/markkind")
public class MarkKindController extends CrudController<String, MarkKindDTO>{

    private final MarkKindService markKindService;

    @Override
    public String getEditRole() {
        return null;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @Override
    protected CrudServiceBase<String, MarkKindDTO> getService() {
        return markKindService;
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
