package bg.duosoft.bpo.registers.repository.ipobject;


import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.EIpObject;
import bg.duosoft.bpo.registers.entity.ipobject.EIpObjectShort;
import bg.duosoft.bpo.registers.nonentity.autocomplete.RepresentativeAutocomplete;
import bg.duosoft.bpo.registers.repository.ipobject.custom.IpObjectRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IpObjectRepository extends BaseRepository<String, EIpObject>, IpObjectRepositoryCustom {

    @Query(value = "SELECT COUNT(*) FROM ip_objects.ip_object e WHERE e.id = ?1", nativeQuery = true)
    Integer countById(String id);

    @Query("""
            SELECT DISTINCT o.status.bpoOnlineStatus, o.status.bpoOnlineStatusEn FROM EIpObject o
            WHERE o.objectType.id in (:objectTypes) ORDER BY o.status.bpoOnlineStatus ASC""")
    List<String[]> getObjectStatuses(@Param("objectTypes") List<String> objectTypes);

    @Query("""
            SELECT DISTINCT 
                new bg.duosoft.bpo.registers.nonentity.autocomplete.RepresentativeAutocomplete(
                p.person.id, p.person.name, a.agentCode, 
                p.representativeType.id, 
                p.representativeType.description, p.representativeType.descriptionEn )
            FROM EIpObject o JOIN o.persons p LEFT JOIN p.person.agent a 
            WHERE (a is null or a.agentStatus.id = 1) and o.objectType.id in (:objectTypes) and lower(p.person.name) like :name and p.role.id = 'REPRESENTATIVE' ORDER BY p.person.name ASC""")
    List<RepresentativeAutocomplete> autocompleteRepresentatives(@Param("name") String name, @Param("objectTypes") List<String> objectTypes, Pageable pageable);

    @Query("""
            SELECT ipo FROM EIpObjectShort ipo WHERE ipo.id=:key OR ipo.alternateKey=:key""")
    EIpObjectShort getObjectByAlternateKey(@Param("key") String alternateKey);

    @Query("""
            SELECT ipo FROM EIpObjectShort ipo WHERE ipo.id=:id""")
    EIpObjectShort getObjectById(@Param("id") String id);



}
