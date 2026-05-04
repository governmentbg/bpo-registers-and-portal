package bg.duosoft.bpo.registers.service.recordal.impl;

import bg.duosoft.bpo.registers.dto.recordal.RecordalDTO;
import bg.duosoft.bpo.registers.mapper.recordal.RecordalMapper;
import bg.duosoft.bpo.registers.repository.recordal.RecordalRepository;
import bg.duosoft.bpo.registers.service.recordal.RecordalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordalServiceImpl implements RecordalService {
    private final RecordalMapper recordalMapper;
    private final RecordalRepository recordalRepository;

    @Override
    public List<RecordalDTO> getRecordalsWithExcludedGroupsByObjectId(List<String> groupIds, String objectId) {
        return recordalMapper.toDtoList(recordalRepository.getRecordalsWithExcludedGroupsByObjectId(groupIds, objectId));
    }

    @Override
    public List<RecordalDTO> getRecordalsWithIncludedGroupsByObjectId(List<String> groupIds, String objectId) {
        return recordalMapper.toDtoList(recordalRepository.getRecordalsWithIncludedGroupsByObjectId(groupIds, objectId));
    }

}
