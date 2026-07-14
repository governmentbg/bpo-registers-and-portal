package bg.duosoft.bpo.registers.service.ipobject.impl;

import bg.duosoft.bpo.registers.dto.ipobject.VwObjectRelationshipDTO;
import bg.duosoft.bpo.registers.entity.ipobject.VwObjectRelationship;
import bg.duosoft.bpo.registers.mapper.ipobject.VwObjectRelationshipMapper;
import bg.duosoft.bpo.registers.repository.ipobject.VwObjectRelationshipRepository;
import bg.duosoft.bpo.registers.service.ipobject.VwObjectRelationshipsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VwObjectRelationshipsServiceImpl implements VwObjectRelationshipsService {

    private final VwObjectRelationshipMapper mapper;
    private final VwObjectRelationshipRepository repository;


    @Override
    public List<VwObjectRelationshipDTO> getObjectRelationshipsByObjectIdAndRelationshipType(String objectId, List<String> relationshipTypes) {
        List<VwObjectRelationship> rels = repository.selectRelationshipsByObjectIdAndRelationshipTypes(objectId, relationshipTypes);
        return mapper.toDtoList(rels);
    }
}
