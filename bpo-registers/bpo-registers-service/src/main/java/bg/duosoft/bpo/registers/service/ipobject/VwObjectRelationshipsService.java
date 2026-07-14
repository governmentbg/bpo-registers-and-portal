package bg.duosoft.bpo.registers.service.ipobject;

import bg.duosoft.bpo.registers.dto.ipobject.VwObjectRelationshipDTO;

import java.util.List;

public interface VwObjectRelationshipsService {
    /**
     * @param objectId
     * @param relationshipTypes - might be null - then searches of all relationship types
     * @return
     */
    List<VwObjectRelationshipDTO> getObjectRelationshipsByObjectIdAndRelationshipType(String objectId, List<String> relationshipTypes);

}
