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
@Table(name = "single_design_type", schema = "nomenclatures")
public class ESingleDesignType implements BaseEntity<String> {
    @Id
    @Column(name = "code")
    @KeywordField(searchable = Searchable.YES,  projectable = Projectable.YES)
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "description_en")
    private String descriptionEn;
}