package bg.duosoft.bpo.registers.entity.ipobject;

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
@Table(name = "ip_replication_details", schema = "ip_objects")
public class EIpReplicationDetail implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "object_id")
    private String objectId;

    @Column(name = "replication_time_id")
    private Integer replicationTimeId;


    @Column(name = "operation_code")
    private String operationCode;

    @ManyToOne
    @MapsId(value = "id")
    @JoinColumn(name = "replication_time_id", referencedColumnName = "id")
    private EIpReplicationTime replicationTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpReplicationDetail that = (EIpReplicationDetail) o;
        return Objects.equals(id, that.id)
                && Objects.equals(objectId, that.objectId)
                && Objects.equals(replicationTimeId, that.replicationTimeId)
                && Objects.equals(operationCode, that.operationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, objectId, replicationTimeId, operationCode);
    }
}