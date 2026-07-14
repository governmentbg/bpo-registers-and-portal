package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.common.EAttachment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_object_attachment", schema = "ip_objects")
public class EIpObjectAttachment implements BaseEntity<EIpObjectAttachmentId> {
    @EmbeddedId
    private EIpObjectAttachmentId id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attachment_id")
    private EAttachment attachment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpObjectAttachment that = (EIpObjectAttachment) o;
        return Objects.equals(id, that.id) && Objects.equals(attachment, attachment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attachment);
    }
}