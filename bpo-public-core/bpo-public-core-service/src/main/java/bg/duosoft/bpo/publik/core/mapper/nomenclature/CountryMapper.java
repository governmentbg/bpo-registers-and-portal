package bg.duosoft.bpo.publik.core.mapper.nomenclature;

//import bg.duosoft.bpo.registers.entity.nomenclature.ECountry;
//import bg.duosoft.bpo.registers.dto.nomenclature.CountryDTO;
//import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.dto.nomenclature.CountryDTO;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ECountry;
import org.mapstruct.Mapper;


/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 13:13
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class CountryMapper extends BaseObjectMapper<ECountry, CountryDTO> {
}
