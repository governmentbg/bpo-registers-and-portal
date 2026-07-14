package bg.duosoft.bpo.registers.repository.ipobject;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.VwPersonObjectRelationship;
import bg.duosoft.bpo.registers.entity.ipobject.VwPersonObjectRelationshipId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VwPersonObjectRelationshipRepository extends BaseRepository<VwPersonObjectRelationshipId, VwPersonObjectRelationship> {
    @Query("select x from VwPersonObjectRelationship x where x.id.objectId <> :objectId and x.personName like concat('%', :personName, '%') order by x.id.objectId desc")
    List<VwPersonObjectRelationship> selectRelationshipsByPersonNameExcludeObjectId(@Param("objectId") String objectId, @Param("personName") String personName, Pageable pageable);

    @Query("select x from VwPersonObjectRelationship x where x.personName like concat('%', :personName, '%') order by x.id.objectId desc")
    List<VwPersonObjectRelationship> selectRelationshipsByPersonName(@Param("personName") String personName);

    @Query("select count(x) from VwPersonObjectRelationship x where x.personName like concat('%', :personName, '%')")
    Integer selectRelationshipsByPersonNameCount(@Param("personName") String personName);
}
