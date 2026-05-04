package bg.duosoft.bpo.publik.core.controller.v1.nomenclature;

import bg.duosoft.bpo.common.web.controller.BaseAccessController;
import bg.duosoft.bpo.publik.core.service.nomenclature.PlantTaxonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nomenclature/plant-taxon")
public class PlantTaxonController extends BaseAccessController {

    private final PlantTaxonService plantTaxonService;

    @Override
    public String getEditRole() {
        return null;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @Override
    public boolean getAccessIsSecured() {
        return false;
    }

    @GetMapping("/bg-classification")
    public List<String> getAllBgClassifications() {
        List<String> strings = plantTaxonService.getAllBgClassifications();
        return strings;
    }

    @GetMapping("/latin-classification")
    public List<String> getAllLatinClassifications() {
        List<String> strings = plantTaxonService.getAllLatinClassifications();
        return strings;
    }
}
