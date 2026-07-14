package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.common.EAttachment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_mark_attachment", schema = "ip_objects")
public class EIpMarkAttachment implements BaseEntity<EIpMarkAttachmentId> {
    @EmbeddedId
    private EIpMarkAttachmentId id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    private EAttachment attachment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "thumbnail_id", referencedColumnName = "id")
    private EAttachment thumbnail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tmview_picture_id", referencedColumnName = "id")
    private EAttachment tmviewPicture;

    @Column(name = "color_description")
    private String colorDescription;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumns({
            @JoinColumn(name = "mark_id",       referencedColumnName = "mark_id", updatable = false, insertable = false),
            @JoinColumn(name = "att_seq_nbr",   referencedColumnName = "att_seq_nbr", updatable = false, insertable = false)
    })
    @IndexedEmbedded
    private List<EIpMarkAttachmentViennaClass> viennaClasses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpMarkAttachment that = (EIpMarkAttachment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}