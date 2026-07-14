package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.IntegerToBooleanMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.MarkKindMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpMarkDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpMark;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 12:47
 */
@Mapper(componentModel = "spring", uses = {
        MarkKindMapper.class,
        IpObjectMapper.class,
        IpMarkNiceClassMapper.class,
        IpMarkAttachmentMapper.class,
        IntegerToBooleanMapper.class
})
public abstract class IpMarkMapper extends IpObjectBaseMapper<EIpMark, IpMarkDTO> {

    @AfterMapping
    protected void afterMapping(EIpMark source, @MappingTarget IpMarkDTO target, @Context boolean forRegister) {

    }
}
