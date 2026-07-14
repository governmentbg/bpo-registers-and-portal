package bg.duosoft.bpo.registers.entity.legaldecision;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "replication_times", schema = "legal_decisions")
public class ELegalDecisionReplicationTime implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date_start")
    private LocalDateTime dateStart;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "replicationTime")
    private List<ELegalDecisionReplicationDetail> details;

    @Column(name = "modified_rows")
    private Integer modifiedRows;

    @Column(name = "date_end")
    private LocalDateTime dateEnd;

}