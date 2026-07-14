package bg.duosoft.bpo.registers.mapper.ipobject;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpAgentHistoryDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpAgentHistory;
import bg.duosoft.bpo.registers.mapper.ipobject.history.agent.AgentHistoryMapperJaxbHelper;
import bg.duosoft.bpo.registers.mapper.ipobject.history.agent.AgentHistoryRecordMapper;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.AgentHistoryTypeMapper;
import bg.duosoft.bpo.registers.nonentity.history.agent.HistoryRecordDataType;
import bg.duosoft.bpo.registers.nonentity.history.agent.HistoryRecordType;
import bg.duosoft.bpo.registers.nonentity.history.agent.HistoryType;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", uses = {AgentHistoryTypeMapper.class})
public abstract class IpAgentHistoryMapper extends BaseObjectMapper<EIpAgentHistory, IpAgentHistoryDTO> {

    @Autowired
    private AgentHistoryRecordMapper agentHistoryRecordMapper;

    @AfterMapping
    protected void afterToDto(EIpAgentHistory source, @MappingTarget IpAgentHistoryDTO target) {
        String historyXmlData = source.getHistoryXmlData();
        HistoryType historyType = AgentHistoryMapperJaxbHelper.getXmlAsObject(historyXmlData);

        List<HistoryRecordType> historyRecord = historyType.getHistoryRecord();
        if (!CollectionUtils.isEmpty(historyRecord)) {
            HistoryRecordType historyRecordType = historyRecord.get(0);
            if (Objects.nonNull(historyRecordType)) {
                HistoryRecordDataType historyRecordData = historyRecordType.getHistoryRecordData();
                target.setHistoryRecord(agentHistoryRecordMapper.toDto(historyRecordData));
            }
        }
    }
}
