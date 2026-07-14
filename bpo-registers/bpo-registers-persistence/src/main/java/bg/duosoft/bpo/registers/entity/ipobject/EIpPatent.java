package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.automaticindexing.ReindexOnUpdate;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_patent", schema = "ip_objects")
@Indexed(index = "v002_eippatent")
public class EIpPatent implements BaseEntity<String>, IpObjectBase {
    @Id
    @Column(name = "patent_id")
    private String id;

    @Column(name = "drawings_count")
    private Integer drawingsCount;

    @Column(name = "claims_count")
    private Integer claimsCount;

    @Column(name = "description_pages_count")
    private Integer descriptionPagesCount;

    @Column(name = "drawing_publication")
    private Integer drawingPublication;

    @Column(name = "inventions_group_count")
    private Integer inventionsGroupCount;

    @Column(name = "renewal_fees_last_paid")
    private LocalDate renewalFeesLastPaid;

    @Column(name = "renewal_fees_paid_to")
    private LocalDate renewalFeesPaidTo;

    @Column(name = "last_paid_year")
    private Integer lastPaidYear;

    @Column(name = "not_in_force_date")
    private LocalDate notInForceDate;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patent_id", referencedColumnName = "id", updatable = false, insertable = false)
    @IndexedEmbedded
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
    private EIpObject ipObject;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "patent_id", referencedColumnName = "patent_id", updatable = false, insertable = false)
    private List<EIpPatentSummary> patentSummaries;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "patent")
    @JoinColumn(name = "patent_id", referencedColumnName = "patent_id", updatable = false, insertable = false)
    private EIpPatentPct pct;

    @Column(name = "main_abstract")
    @FullTextField(searchable = Searchable.YES, projectable = Projectable.YES, analyzer = "WordAnalyzer")
    @FullTextField(name = "mainAbstractCustom", searchable = Searchable.YES, projectable = Projectable.NO, analyzer = "FullTextAnalyzer")
    @KeywordField(name = "mainAbstractSort", searchable = Searchable.NO,  projectable = Projectable.YES, normalizer = "sortNormalizer")
    private String mainAbstract;

    @Column(name = "en_abstract")
    private String enAbstract;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "patent_id", referencedColumnName = "patent_id", updatable = false, insertable = false)
    @IndexedEmbedded
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
    private List<EIpPatentIpcClass> ipcClasses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "patent_id", referencedColumnName = "patent_id", updatable = false, insertable = false)
    @IndexedEmbedded
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
    private List<EIpPatentCpcClass> cpcClasses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "patent_id", referencedColumnName = "patent_id", updatable = false, insertable = false)
    private List<EIpPatentCitation> citations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpPatent eIpPatent = (EIpPatent) o;
        return Objects.equals(id, eIpPatent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}