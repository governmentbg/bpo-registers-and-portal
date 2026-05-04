package bg.duosoft.bpo.registers.entity.ipobject;

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
@Table(name = "ip_replication_times", schema = "ip_objects")
public class EIpReplicationTime implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "date_start")
    private LocalDateTime dateStart;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "replicationTime")
    private List<EIpReplicationDetail> details;

    @Column(name = "replicator_type")
    private String replicatorType;

    @Column(name = "modified_rows")
    private Integer modifiedRows;

    @Column(name = "xml_generated")
    private Integer xmlGenerated;

    @Column(name = "indexed")
    private Integer indexed;

    @Column(name = "date_end")
    private LocalDateTime dateEnd;

    @Column(name = "exported")
    private Integer exported;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpReplicationTime that = (EIpReplicationTime) o;
        return Objects.equals(id, that.id)
                && Objects.equals(dateStart, that.dateStart)
                && Objects.equals(details, that.details)
                && Objects.equals(xmlGenerated, that.xmlGenerated)
                && Objects.equals(replicatorType, that.replicatorType)
                && Objects.equals(modifiedRows, that.modifiedRows)
                && Objects.equals(indexed, that.indexed)
                && Objects.equals(exported, that.exported)
                && Objects.equals(dateEnd, that.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateStart, xmlGenerated, details, xmlGenerated, replicatorType, modifiedRows, indexed, dateEnd, exported);
    }
}