package bg.duosoft.bpo.publik.core.repository.nomenclature;

import bg.duosoft.bpo.publik.core.entity.nomenclature.EAgentSpeciality;
import bg.duosoft.bpo.common.repository.BaseRepository;

public interface AgentSpecialityRepository extends BaseRepository<String, EAgentSpeciality> {
    public EAgentSpeciality getByIpasCode(Integer ipasCode);
}

