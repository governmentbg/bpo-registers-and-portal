package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_mark_attachment_vienna_class", schema = "ip_objects")
public class EIpMarkAttachmentViennaClass implements BaseEntity<EIpMarkAttachmentViennaClassId> {
    @EmbeddedId
    @IndexedEmbedded
    private EIpMarkAttachmentViennaClassId id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpMarkAttachmentViennaClass that = (EIpMarkAttachmentViennaClass) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}