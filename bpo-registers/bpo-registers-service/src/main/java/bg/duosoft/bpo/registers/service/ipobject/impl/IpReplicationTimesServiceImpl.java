package bg.duosoft.bpo.registers.service.ipobject.impl;

import bg.duosoft.bpo.registers.dto.ipobject.IpReplicationTimeDTO;
import bg.duosoft.bpo.registers.mapper.ipobject.IpReplicationTimeMapper;
import bg.duosoft.bpo.registers.repository.ipobject.IpReplicationTimeRepository;
import bg.duosoft.bpo.registers.service.ipobject.IpReplicationTimesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IpReplicationTimesServiceImpl implements IpReplicationTimesService {

    private final IpReplicationTimeRepository repository;
    private final IpReplicationTimeMapper mapper;


    @Override
    public List<IpReplicationTimeDTO> getNonExportedMarkReplicationTimes() {
        return mapper.toDtoList(repository.getNonExportedMarkReplicationTimes());
    }

    @Override
    public void saveAll(List<IpReplicationTimeDTO> entries) {
        repository.saveAll(mapper.toEntityList(entries));
    }

    @Override
    public void setExportedTrueForAll() {
        repository.setExportedTrueForAll();
    }
}
