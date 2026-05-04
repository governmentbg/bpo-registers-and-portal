package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 05.05.2022
 * Time: 11:40
 */
@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "plant_taxon", schema = "nomenclatures")
public class EPlantTaxon implements BaseEntity<Integer> {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "taxon_code")
    private String taxonCode;

    @Column(name = "common_classify_bg")
    @FullTextField(searchable = Searchable.YES, projectable = Projectable.YES, analyzer = "FullTextAnalyzer")
    private String commonClassifyBg;

    @Column(name = "common_classify_en")
    private String commonClassifyEn;

    @Column(name = "latin_classify")
    @FullTextField(searchable = Searchable.YES, projectable = Projectable.YES, analyzer = "FullTextAnalyzer")
    private String latinClassify;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EPlantTaxon eIpPlant = (EPlantTaxon) o;
        return Objects.equals(id, eIpPlant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
