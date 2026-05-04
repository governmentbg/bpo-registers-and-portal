package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "country", schema = "nomenclatures")
@Cacheable(value = false)
@NoArgsConstructor
public class ECountry implements BaseEntity<String> {

    @Id
    @Column(name = "code")
    @KeywordField(name = "countryCode", searchable = Searchable.YES,  projectable = Projectable.YES)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_en")
    private String nameEn;

    public ECountry(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ECountry eCountry = (ECountry) o;
        return Objects.equals(id, eCountry.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
