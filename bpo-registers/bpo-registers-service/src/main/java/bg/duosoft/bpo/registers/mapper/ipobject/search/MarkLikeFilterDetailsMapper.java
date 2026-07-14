package bg.duosoft.bpo.registers.mapper.ipobject.search;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.dto.filter.MarkLikeFilterDetailsDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.ViennaClassDTO;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.02.2024
 * Time: 11:52
 */
public abstract class MarkLikeFilterDetailsMapper<DTO extends MarkLikeFilterDetailsDTO> extends CommonIpObjectFilterMapper {

    @InheritConfiguration(name = "toIpObjectFilterConfig")
    @Mapping(target = "markKind", ignore = true)
    @Mapping(target = "objectSubType", source = "objectSubtype")
    @Mapping(target = "niceClassCodes", source = "niceClasses.niceClassCodes")
    @Mapping(target = "niceClassCodeType", source = "niceClasses.niceClassCodeType")
    @Mapping(target = "viennaClassCodes", source = "viennaClasses.viennaClasses", qualifiedByName = "toViennaClassCodes")
    @Mapping(target = "viennaClassCodeType", source = "viennaClasses.viennaClassCodeType")
    @Mapping(target = "niceClassSpecificationText", source = "niceClasses.specification.text")
    @Mapping(target = "niceClassSpecificationSearchType", source = "niceClasses.specification.searchType")
    protected abstract IpObjectFilter toIpObjectFilterInternal(DTO markLikeFilterDetailsDTO);

    @Named("toViennaClassCodes")
    public List<String> toViennaClassCodes(List<ViennaClassDTO> viennaClasses){
        if(viennaClasses == null){
            return null;
        }
        return viennaClasses.stream().map(v -> v.getId()).collect(Collectors.toList());
    }
}
