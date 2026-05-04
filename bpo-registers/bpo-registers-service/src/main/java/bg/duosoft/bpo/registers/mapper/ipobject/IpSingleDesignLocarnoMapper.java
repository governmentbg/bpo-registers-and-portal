package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.registers.entity.ipobject.EIpSingleDesignLocarno;
import bg.duosoft.bpo.registers.dto.ipobject.IpSingleDesignLocarnoDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 17:10
 */
@Mapper(componentModel = "spring", uses = {IpSingleDesignLocarnoIdMapper.class})
public abstract class IpSingleDesignLocarnoMapper extends BaseObjectMapper<EIpSingleDesignLocarno, IpSingleDesignLocarnoDTO> {

    public abstract IpSingleDesignLocarnoDTO toDto(EIpSingleDesignLocarno eIpSingleDesignLocarno);
}
