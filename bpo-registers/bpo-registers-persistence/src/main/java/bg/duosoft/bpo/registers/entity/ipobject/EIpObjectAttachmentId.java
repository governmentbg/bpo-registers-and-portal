package bg.duosoft.bpo.registers.entity.ipobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class EIpObjectAttachmentId implements Serializable {

    @Column(name = "object_id")
    private String objectId;

    @Column(name = "seq_nbr")
    private Integer sequenceNumber;

    @Override
    public int hashCode() {
        return Objects.hash(objectId, sequenceNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpObjectAttachmentId entity = (EIpObjectAttachmentId) o;
        return Objects.equals(this.objectId, entity.objectId) &&
                Objects.equals(this.sequenceNumber, entity.sequenceNumber);
    }
}