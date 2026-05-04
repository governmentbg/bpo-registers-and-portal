package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "person_role", schema = "nomenclatures")
public class EPersonRole implements BaseEntity<String> {

    @Column(name = "description")
    private String description;

    @Column(name = "description_en")
    private String descriptionEn;

    @Id
    @Column(name = "code")
    @KeywordField(name = "code", searchable = Searchable.YES,  projectable = Projectable.NO)
    private String id;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EPersonRole that = (EPersonRole) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}