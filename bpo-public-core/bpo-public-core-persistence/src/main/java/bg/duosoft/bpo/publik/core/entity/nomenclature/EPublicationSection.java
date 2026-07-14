package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "publication_section", schema = "nomenclatures")
public class EPublicationSection implements BaseEntity<Integer> {
    @Id
    @Column(name = "idsection")
    @GenericField(searchable = Searchable.YES,  projectable = Projectable.NO)
    private Integer id;

    @Column(name = "nmsection")
    private String nmsection;

    @Column(name = "nmsection_en")
    private String nmsectionEn;

    @Column(name = "publ_code")
    private String publicationCode;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EPublicationSection that = (EPublicationSection) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}