package bg.duosoft.bpo.registers.repository.ipobject;


import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.EIpAgent;
import bg.duosoft.bpo.registers.repository.ipobject.custom.IpAgentRepositoryCustom;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IpAgentRepository extends BaseRepository<Integer, EIpAgent>, IpAgentRepositoryCustom {

    @Query(value = "select distinct CASE WHEN :language = 'bg' THEN pa.city_name\n" +
            "            WHEN :language = 'en' THEN ag.city_name_en END city\n" +
            "from ip_objects.ip_person_addresses pa\n" +
            "         inner join ip_objects.ip_agent_addresses ag on pa.person_id = ag.person_id\n" +
            "where lower(pa.city_name) like lower(:cityName) or lower(ag.city_name_en) like lower(:cityName)", nativeQuery = true)
    List<String> filterAgentCities(String language, String cityName);

    @Query(value = "select distinct CASE WHEN :language = 'bg' THEN ipa.speciality\n" +
            "            WHEN :language = 'en' THEN ipa.speciality_en END speciality\n" +
            "from ip_objects.ip_agent ipa\n" +
            "where lower(ipa.speciality) like lower(:speciality) or lower(ipa.speciality_en) like lower(:speciality)\n" +
            "order by speciality ASC", nativeQuery = true)
    List<String> filterAgentSpecialities(String language, String speciality);
}
