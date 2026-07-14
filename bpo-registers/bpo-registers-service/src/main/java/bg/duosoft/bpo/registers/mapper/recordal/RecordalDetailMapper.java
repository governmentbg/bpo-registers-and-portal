package bg.duosoft.bpo.registers.mapper.recordal;

import bg.duosoft.bpo.registers.entity.recordal.ERecordalDetail;
import bg.duosoft.bpo.registers.dto.recordal.RecordalDetailDTO;
import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.04.2022
 * Time: 15:34
 */
@Mapper(componentModel = "spring", uses = {
        RecordalDetailTypeMapper.class
})
public abstract class RecordalDetailMapper extends BaseObjectMapper<ERecordalDetail, RecordalDetailDTO> {
}
