package bg.duosoft.bpo.registers.entity.legaldecision;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "replication_details", schema = "legal_decisions")
public class ELegalDecisionReplicationDetail implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "document_id")
    private String documentId;

    @Column(name = "rte_id")
    private Integer replicationTimeId;

    @Column(name = "operation_code")
    private String operationCode;

    @ManyToOne
    @MapsId(value = "id")
    @JoinColumn(name = "rte_id", referencedColumnName = "id")
    private ELegalDecisionReplicationTime replicationTime;
}