package bg.duosoft.bpo.registers.mapper.ipobject.search;

import bg.duosoft.bpo.publik.core.dto.nomenclature.LocarnoClassDTO;
import bg.duosoft.bpo.registers.dto.filter.DesignFilterDetailsDTO;
import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class DesignFilterDetailsMapper extends PatentLikeFilterDetailsMapper<DesignFilterDetailsDTO> {

    @InheritConfiguration(name = "toIpObjectFilterInternal")
    @Mapping(target = "locarnoClassCodes", source = "locarnoClasses.locarnoClasses", qualifiedByName = "toLocarnoClassCodes")
    @Mapping(target = "locarnoClassCodeType", source = "locarnoClasses.locarnoClassCodeType")
    public abstract IpObjectFilter toIpObjectFilter(DesignFilterDetailsDTO designFilterDetailsDTO);

    @Named("toLocarnoClassCodes")
    public List<String> toLocarnoClassCodes(List<LocarnoClassDTO> locarnoClasses) {
        if (locarnoClasses == null) {
            return null;
        }
        return locarnoClasses.stream().map(v -> v.getId()).collect(Collectors.toList());
    }
}
