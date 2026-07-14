package bg.duosoft.bpo.registers.service.seniority.impl;

import bg.duosoft.bpo.registers.dto.seniority.SeniorityBgDTO;
import bg.duosoft.bpo.registers.mapper.seniority.SeniorityBgMapper;
import bg.duosoft.bpo.registers.repository.seniority.SeniorityBgRepository;
import bg.duosoft.bpo.registers.service.seniority.SeniorityBgService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 09.02.2026
 * Time: 17:44
 */
@Service
@RequiredArgsConstructor
public class SeniorityBgServiceImpl implements SeniorityBgService {
    private final SeniorityBgMapper mapper;
    private final SeniorityBgRepository repository;
    @Override
    public List<SeniorityBgDTO> getSeniorityBgs(String objectId) {
        return mapper.toDtoList(repository.getESeniorityBgByObjectId(objectId));
    }
    public boolean hasSeniorityBg(String objectId) {
        return repository.getSeniorityCount(objectId) > 0;
    }
}
