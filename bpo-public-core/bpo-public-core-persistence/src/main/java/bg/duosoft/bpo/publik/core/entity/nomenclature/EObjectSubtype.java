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

@Getter
@Setter
@Entity
@Cacheable(value = false)
@Table(name = "object_subtype", schema = "nomenclatures")
public class EObjectSubtype implements BaseEntity<String> {
    @Id
    @Column(name = "code")
    @KeywordField(searchable = Searchable.YES,  projectable = Projectable.YES)
    private String id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "object_type", referencedColumnName = "code", insertable = false, updatable = false)
    private EObjectType objectType;



    @Column(name = "description")
    private String description;

    @Column(name = "description_en")
    private String descriptionEn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EObjectSubtype objectSubType = (EObjectSubtype) o;
        return Objects.equals(id, objectSubType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}