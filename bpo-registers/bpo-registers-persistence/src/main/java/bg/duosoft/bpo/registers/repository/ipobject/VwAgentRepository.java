package bg.duosoft.bpo.registers.repository.ipobject;

import bg.duosoft.bpo.common.repository.BaseRepository;
import bg.duosoft.bpo.registers.entity.ipobject.VwAgent;
import bg.duosoft.bpo.registers.repository.ipobject.custom.VwAgentRepositoryCustom;

/**
 * User: ggeorgiev
 * Date: 11.11.2024
 * Time: 13:34
 */
public interface VwAgentRepository extends BaseRepository<Integer, VwAgent>, VwAgentRepositoryCustom {
    VwAgent findByAgentCode(String agentCode);
}
