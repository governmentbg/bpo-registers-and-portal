package bg.duosoft.bpo.registers.service.ipobject.impl;

import bg.duosoft.bpo.registers.dto.ipobject.VwPersonObjectRelationshipDTO;
import bg.duosoft.bpo.registers.mapper.ipobject.VwPersonObjectRelationshipMapper;
import bg.duosoft.bpo.registers.repository.ipobject.VwPersonObjectRelationshipRepository;
import bg.duosoft.bpo.registers.service.ipobject.VwPersonObjectRelationshipsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VwPersonObjectRelationshipsServiceImpl implements VwPersonObjectRelationshipsService {

    private final VwPersonObjectRelationshipMapper mapper;
    private final VwPersonObjectRelationshipRepository repository;


    @Override
    public List<VwPersonObjectRelationshipDTO> getRelationshipsByPersonNameExcludeObjectId(String objectId, String personName, Pageable pageable) {
        return mapper.toDtoList(repository.selectRelationshipsByPersonNameExcludeObjectId(objectId, personName, pageable));
    }

    @Override
    public List<VwPersonObjectRelationshipDTO> getRelationshipsByPersonName(String personName) {
        return mapper.toDtoList(repository.selectRelationshipsByPersonName(personName));
    }

    @Override
    public Integer getRelationshipsByPersonNameCount(String personName) {
        return repository.selectRelationshipsByPersonNameCount(personName);
    }
}
