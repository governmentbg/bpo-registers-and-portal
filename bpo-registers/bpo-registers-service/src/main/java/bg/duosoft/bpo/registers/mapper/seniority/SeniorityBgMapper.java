package bg.duosoft.bpo.registers.mapper.seniority;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.seniority.SeniorityBgDTO;
import bg.duosoft.bpo.registers.entity.seniority.ESeniorityBg;
import org.mapstruct.Mapper;

/**
 * User: ggeorgiev
 * Date: 09.02.2026
 * Time: 17:42
 */
@Mapper
public abstract class SeniorityBgMapper extends BaseObjectMapper<ESeniorityBg, SeniorityBgDTO> {
}
