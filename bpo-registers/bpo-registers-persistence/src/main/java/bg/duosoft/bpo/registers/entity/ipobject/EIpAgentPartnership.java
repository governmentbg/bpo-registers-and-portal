package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * User: ggeorgiev
 * Date: 19.06.2025
 * Time: 15:24
 */
@Entity
@Table(name = "ip_agent_partnership", schema = "ip_objects")
@Data
@Cacheable(false)
public class EIpAgentPartnership implements BaseEntity<EIpAgentPartnershipId> {
    @EmbeddedId
    private EIpAgentPartnershipId id;
}
