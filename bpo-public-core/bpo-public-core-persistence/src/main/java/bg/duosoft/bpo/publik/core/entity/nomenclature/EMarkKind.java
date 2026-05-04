package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

@Getter
@Setter
@Entity
@Cacheable(value = false)
@Table(name = "mark_kind", schema = "nomenclatures")
public class EMarkKind implements BaseEntity<String> {
    @Id
    @Column(name = "backoffice_code")
    @KeywordField(name = "code", searchable = Searchable.YES,  projectable = Projectable.YES)
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column(name = "tmview_mark_feature")
    private String tmviewMarkFeature;

}