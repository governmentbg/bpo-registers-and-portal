package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * User: ggeorgiev
 * Date: 11.11.2024
 * Time: 12:00
 */
@Entity
@Table(name = "vw_agent", schema = "ip_objects")
@Getter
@Setter
public class VwAgent implements BaseEntity<Integer> {
    @Id
    @Column(name = "person_id")
    private Integer id;
    @Column(name = "agent_code")
    private String agentCode;
    @Column(name = "representative_type")
    private String representativeType;
    @Column(name = "agent_status")
    private Integer agentStatus;
    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", updatable = false, insertable = false)
    private EIpPerson person;
}
