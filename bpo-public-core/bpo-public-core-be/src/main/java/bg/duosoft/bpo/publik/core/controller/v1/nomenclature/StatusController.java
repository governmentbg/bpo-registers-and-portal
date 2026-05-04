package bg.duosoft.bpo.publik.core.controller.v1.nomenclature;

import bg.duosoft.bpo.common.web.controller.BaseAccessController;
import bg.duosoft.bpo.publik.core.dto.nomenclature.BackofficeStatusDTO;
import bg.duosoft.bpo.publik.core.service.nomenclature.StatusMapService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nomenclature/statuses")
public class StatusController extends BaseAccessController {

    private final StatusMapService statusMapService;

    @Override
    public String getEditRole() {
        return null;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @GetMapping("/bo/ip-objects")
    public Map<String, List<BackofficeStatusDTO>> getBOIpObjectStatuses(@RequestParam List<String> objectTypes) {
        return statusMapService.getBOStatusesByObjectTypes(objectTypes);
    }

    @GetMapping("/bo/userdocs")
    public List<BackofficeStatusDTO> getBOUserdocStatuses() {
        return statusMapService.getBOUserdocStatuses();
    }
}
