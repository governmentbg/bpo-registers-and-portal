package bg.duosoft.bpo.registers.entity.ipobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class EIpMarkNiceClassId implements Serializable {

    @Column(name = "mark_id")
    private String markId;

    @Column(name = "class_id")
    @GenericField(searchable = Searchable.YES,  projectable = Projectable.YES)
    private Integer classId;

    @Override
    public int hashCode() {
        return Objects.hash(classId, markId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpMarkNiceClassId entity = (EIpMarkNiceClassId) o;
        return Objects.equals(this.classId, entity.classId) &&
                Objects.equals(this.markId, entity.markId);
    }
}