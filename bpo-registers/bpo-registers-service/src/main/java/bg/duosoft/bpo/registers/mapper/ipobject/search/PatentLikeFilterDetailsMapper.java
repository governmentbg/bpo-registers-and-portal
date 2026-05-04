package bg.duosoft.bpo.registers.mapper.ipobject.search;

import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentCpcClassDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentIpcClassDTO;
import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.dto.filter.PatentLikeFilterDetailsDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.02.2024
 * Time: 16:45
 */
public abstract class PatentLikeFilterDetailsMapper<DTO extends PatentLikeFilterDetailsDTO> extends CommonIpObjectFilterMapper {

    @InheritConfiguration(name = "toIpObjectFilterConfig")
    @Mapping(target = "abstractText", source = "abstractDetails.text")
    @Mapping(target = "abstractSearchType", source = "abstractDetails.searchType")
    @Mapping(target = "inventorCountry", source = "inventorCountry.id")
    @Mapping(target = "ipcClassOperatorType", source = "ipcClasses.ipcClassOperatorType")
    @Mapping(target = "ipcClasses", source = "ipcClasses.ipcClasses", qualifiedByName = "ipcMapping")
    @Mapping(target = "cpcClassOperatorType", source = "cpcClasses.cpcClassOperatorType")
    @Mapping(target = "cpcClasses", source = "cpcClasses.cpcClasses", qualifiedByName = "cpcMapping")
    protected abstract IpObjectFilter toIpObjectFilterInternal(DTO patentLikeFilterDetailsDTO);

    @Named("ipcMapping")
    public List<String> ipcMapping(List<PatentIpcClassDTO> ipcs) {
        if (CollectionUtils.isEmpty(ipcs)) {
            return null;
        }
        return ipcs.stream().map(x -> x.getId().toString()).toList();
    }

    @Named("cpcMapping")
    public List<String> cpcMapping(List<PatentCpcClassDTO> cpcs) {
        if (CollectionUtils.isEmpty(cpcs)) {
            return null;
        }
        return cpcs.stream().map(x -> x.getId().toString()).toList();
    }
}
