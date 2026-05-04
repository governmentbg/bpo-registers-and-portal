package bg.duosoft.bpo.publik.core.mapper.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.EPlantTaxon;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PlantTaxonDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * User: ggeorgiev
 * Date: 05.05.2022
 * Time: 13:20
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class PlantTaxonMapper extends BaseObjectMapper<EPlantTaxon, PlantTaxonDTO> {
}
