package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPlantTaxon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.automaticindexing.ReindexOnUpdate;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_plant", schema = "ip_objects")
@Indexed(index = "v002_eipplant")
public class EIpPlant implements BaseEntity<String>, IpObjectBase {
    @Id
    @Column(name = "plant_id")
    private String id;

    @Column(name = "drawings_count")
    private Integer drawingsCount;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @Column(name = "main_abstract")
    @FullTextField(searchable = Searchable.YES, projectable = Projectable.YES, analyzer = "WordAnalyzer")
    @FullTextField(name = "mainAbstractCustom", searchable = Searchable.YES, projectable = Projectable.NO, analyzer = "FullTextAnalyzer")
    @KeywordField(name = "mainAbstractSort", searchable = Searchable.NO,  projectable = Projectable.YES, normalizer = "sortNormalizer")
    private String mainAbstract;

    @Column(name = "en_abstract")
    private String enAbstract;

    @Column(name = "features")
    private String features;

    @Column(name = "stability")
    private String stability;

    @Column(name = "testing")
    private String testing;

    @Column(name = "approved_title")
    private String approvedTitle;

    @Column(name = "approved_engtitle")
    private String approvedEnTitle;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plant_id", referencedColumnName = "id", updatable = false, insertable = false)
    @IndexedEmbedded
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
    private EIpObject ipObject;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "taxon_id", referencedColumnName = "id")
    @IndexedEmbedded
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
    private EPlantTaxon taxon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EIpPlant eIpPlant = (EIpPlant) o;
        return Objects.equals(id, eIpPlant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}