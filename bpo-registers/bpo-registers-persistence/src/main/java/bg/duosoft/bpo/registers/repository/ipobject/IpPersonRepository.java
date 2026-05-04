package bg.duosoft.bpo.registers.repository.ipobject;


import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPerson;
import bg.duosoft.bpo.registers.repository.ipobject.custom.IpPersonRepositoryCustom;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IpPersonRepository extends BaseRepository<Integer, EIpPerson>, IpPersonRepositoryCustom {

    @Query(value = "select p.* from ip_objects.ip_person p\n" +
            "inner join ip_objects.ip_agent ia on p.person_id = ia.person_id\n" +
            "where ia.agent_code = :agentCode and ia.representative_type in (:representativeTypes)", nativeQuery = true)
    EIpPerson getAgentByAgentCodeAndTypeIn(String agentCode, List<String> representativeTypes);

    @Query(value = "select p.* from ip_objects.ip_agent_partnership iap\n" +
            "join ip_objects.ip_person p on p.person_id = iap.idagent\n" +
            "where iap.idpartnership = :agentId", nativeQuery = true)
    List<EIpPerson> getPartnershipAgents(Integer agentId);

    @Query(value = "select p.* from  ip_objects.ip_agent_partnership iap\n" +
            "join ip_objects.ip_person p on p.person_id = iap.idpartnership\n" +
            "where iap.idagent = :agentId", nativeQuery = true)
    List<EIpPerson> getAgentPartnerships(Integer agentId);

    @Query(value = "select p.* from ip_objects.ip_person p\n" +
            "inner join ip_objects.ip_agent ia on p.person_id = ia.person_id\n" +
            "where lower(ia.agent_code) like lower(concat('%', :nameOrAgentCode, '%'))\n" +
            "or lower(p.name) like lower(concat('%', :nameOrAgentCode, '%'))", nativeQuery = true)
    List<EIpPerson> filterByNameOrAgentCode(String nameOrAgentCode);

    List<EIpPerson> findByIdIn(Long[] ids);
}
