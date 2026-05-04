package bg.duosoft.bpo.publik.core.controller.v1.nomenclature;

import bg.duosoft.bpo.publik.core.dto.nomenclature.NiceClassDTO;
import bg.duosoft.bpo.publik.core.service.nomenclature.NiceClassService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nomenclature/nice-classes")
public class NiceClassController {

    private final NiceClassService niceClassService;

    @GetMapping()
    @Operation(description = "Select nice classes by type - GOODS or GOODS_AND_SERVICES")
    public List<NiceClassDTO> selectNiceClassRange(@RequestParam(name = "onlyGoods", defaultValue = "false") Boolean onlyGoods) {
        return this.niceClassService.selectNiceClassRange(onlyGoods);
    }
}
