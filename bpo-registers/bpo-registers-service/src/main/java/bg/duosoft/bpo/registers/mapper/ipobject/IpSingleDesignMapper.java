package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.registers.entity.ipobject.EIpSingleDesign;
import bg.duosoft.bpo.registers.dto.ipobject.IpSingleDesignDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.ObjectSubtypeMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.StatusMapMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 17:15
 */
@Mapper(componentModel = "spring", uses = {
        ObjectSubtypeMapper.class,
        StatusMapMapper.class,
        IpSingleDesignDrawingMapper.class,
        IpSingleDesignLocarnoMapper.class
})
public abstract class IpSingleDesignMapper extends BaseObjectMapper<EIpSingleDesign, IpSingleDesignDTO> {


    @Mapping(target = "mainDesign", ignore = true)
    public abstract IpSingleDesignDTO toDto(EIpSingleDesign e);


    @InheritInverseConfiguration
    @Mapping(target = "mainDesign", ignore = true)
    public abstract EIpSingleDesign toEntity(IpSingleDesignDTO d);
}
