package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpSingleDesignDrawingId;
import bg.duosoft.bpo.registers.entity.ipobject.EIpSingleDesignDrawingId;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 17:12
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class IpSingleDesignDrawingIdMapper extends BaseObjectMapper<EIpSingleDesignDrawingId, IpSingleDesignDrawingId> {

}
