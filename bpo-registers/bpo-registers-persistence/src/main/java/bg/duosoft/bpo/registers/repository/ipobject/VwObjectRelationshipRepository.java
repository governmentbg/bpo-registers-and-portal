package bg.duosoft.bpo.registers.repository.ipobject;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.VwObjectRelationship;
import bg.duosoft.bpo.registers.entity.ipobject.VwObjectRelationshipId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VwObjectRelationshipRepository extends BaseRepository<VwObjectRelationshipId, VwObjectRelationship> {
    @Query("select x from VwObjectRelationship x where x.id.objectId = :objectId and ( :relationshipTypes is null or x.id.relationshipType in :relationshipTypes)")
    List<VwObjectRelationship> selectRelationshipsByObjectIdAndRelationshipTypes(@Param("objectId") String objectId, @Param("relationshipTypes") List<String> relationshipTypes);
}
