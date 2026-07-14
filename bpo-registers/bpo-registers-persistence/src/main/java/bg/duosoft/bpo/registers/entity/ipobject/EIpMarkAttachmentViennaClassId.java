package bg.duosoft.bpo.registers.entity.ipobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class EIpMarkAttachmentViennaClassId implements Serializable {

    @Column(name = "mark_id")
    private String markId;

    @Column(name = "att_seq_nbr")
    private Integer attachmentSequenceNumber;

    @Column(name = "category_id")
    @KeywordField(name = "categoryId",  searchable = Searchable.YES, projectable = Projectable.YES)
    private String categoryId;

    @Override
    public int hashCode() {
        return Objects.hash(markId, categoryId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpMarkAttachmentViennaClassId entity = (EIpMarkAttachmentViennaClassId) o;
        return Objects.equals(this.markId, entity.markId) &&
                Objects.equals(this.categoryId, entity.categoryId);
    }
}