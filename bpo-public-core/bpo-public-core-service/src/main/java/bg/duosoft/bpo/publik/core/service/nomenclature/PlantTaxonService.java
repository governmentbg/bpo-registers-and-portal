package bg.duosoft.bpo.publik.core.service.nomenclature;

import bg.duosoft.bpo.common.service.service.CrudServiceBase;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PlantTaxonDTO;

import java.util.List;

public interface PlantTaxonService extends CrudServiceBase<Integer, PlantTaxonDTO> {

    List<String> getAllBgClassifications();

    List<String> getAllLatinClassifications();

    List<PlantTaxonDTO> getAutocompletePlantTaxon(String word, Integer limit);
}
