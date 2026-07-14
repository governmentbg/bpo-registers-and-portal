package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EAgentHistoryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_agent_history", schema = "ip_objects")
public class EIpAgentHistory implements BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "person_id")
    private Integer personId;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "history_type")
    private EAgentHistoryType historyType;

    @Column(name = "history_timestamp")
    private LocalDateTime historyTimestamp;

    @Column(name = "history_xml_data")
    private String historyXmlData;

    @Column(name = "initiating_doc")
    private String initiatingDoc;

    @Column(name = "bo_history_id")
    private String boHistoryId;
}
