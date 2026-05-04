package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.registers.dto.ipobject.IpPlantDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPlant;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.PlantTaxonMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 17:19
 */
@Mapper(componentModel = "spring", uses = {
        IpObjectMapper.class,
        PlantTaxonMapper.class,
})
public abstract class IpPlantMapper extends IpObjectBaseMapper<EIpPlant, IpPlantDTO> {

}
