package bg.duosoft.bpo.registers.service.ipobject;

import bg.duosoft.bpo.registers.dto.ipobject.VwPersonObjectRelationshipDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VwPersonObjectRelationshipsService {
    List<VwPersonObjectRelationshipDTO> getRelationshipsByPersonNameExcludeObjectId(String objectId, String personName, Pageable pageable);

    List<VwPersonObjectRelationshipDTO> getRelationshipsByPersonName(String personName);

    Integer getRelationshipsByPersonNameCount(String personName);

}
