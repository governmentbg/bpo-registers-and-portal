package bg.duosoft.bpo.registers.mapper.ipobject.search;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.dto.filter.PlantBreedFilterDetailsDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PlantBreedFilterDetailsMapper extends PatentLikeFilterDetailsMapper<PlantBreedFilterDetailsDTO> {

    @InheritConfiguration(name = "toIpObjectFilterInternal")
    @Mapping(target = "objectSubType", source = "objectSubtype")
    public abstract IpObjectFilter toIpObjectFilter(PlantBreedFilterDetailsDTO plantBreedFilterDetailsDTO);
}
