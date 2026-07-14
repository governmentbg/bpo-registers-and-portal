package bg.duosoft.bpo.registers.service.ipobject.impl;

import bg.duosoft.bpo.registers.dto.ipobject.IpAgentHistoryDTO;
import bg.duosoft.bpo.registers.mapper.ipobject.IpAgentHistoryMapper;
import bg.duosoft.bpo.registers.repository.ipobject.IpAgentHistoryRepository;
import bg.duosoft.bpo.registers.service.ipobject.IpAgentHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IpAgentHistoryServiceImpl implements IpAgentHistoryService {
    private final IpAgentHistoryMapper ipAgentHistoryMapper;
    private final IpAgentHistoryRepository ipAgentHistoryRepository;

    @Override
    public List<IpAgentHistoryDTO> selectAllByPersonId(Integer personId) {
        return ipAgentHistoryMapper.toDtoList(ipAgentHistoryRepository.selectAllByPersonId(personId));
    }
}
