package bg.duosoft.bpo.registers.entity.ipobject;


import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EMarkKind;
import bg.duosoft.bpo.registers.utils.search.bridge.MarkImageMapperBridge;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.search.engine.backend.types.ObjectStructure;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.automaticindexing.ReindexOnUpdate;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBridgeRef;
import org.hibernate.search.mapper.pojo.extractor.mapping.annotation.ContainerExtract;
import org.hibernate.search.mapper.pojo.extractor.mapping.annotation.ContainerExtraction;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexingDependency;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_mark", schema = "ip_objects")
@Indexed(index = "v002_eipmark")
public class EIpMark implements BaseEntity<String>, IpObjectBase {
    @Id
    @Column(name = "mark_id")
    private String id;


    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "mark_kind")
    @IndexedEmbedded
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
    private EMarkKind markKind;

    @Column(name = "mark_description")
    private String markDescription;

    @Column(name = "mark_transliteration")
    private String markTransliteration;

    @Column(name = "mark_translation")
    private String markTranslation;

    @Column(name = "disclaimer")
    private String disclaimer;

    @Column(name = "status_changed_date")
    private LocalDate statusChangedDate;

    @Column(name = "opposition_date")
    private LocalDate oppositionDate;

    @Column(name = "opposition_end_date")
    private LocalDate oppositionEndDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mark_id", referencedColumnName = "id", updatable = false, insertable = false)
    @IndexedEmbedded
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
    private EIpObject ipObject;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "mark_id", referencedColumnName = "mark_id", updatable = false, insertable = false)
    @IndexedEmbedded
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
    private List<EIpMarkNiceClass> niceClasses;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "mark_id", referencedColumnName = "mark_id", updatable = false, insertable = false)
    @GenericField(name = "img", searchable = Searchable.NO,  projectable = Projectable.YES,
            valueBridge = @ValueBridgeRef(type = MarkImageMapperBridge.class),
            extraction = @ContainerExtraction(extract = ContainerExtract.NO) )
    @IndexedEmbedded
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
    private List<EIpMarkAttachment> markAttachments;
    @Column(name = "st13")
    private String st13;

    @Column(name = "acquired_distinctiveness")
    private Integer acquiredDistinctiveness;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpMark eIpMark = (EIpMark) o;
        return Objects.equals(id, eIpMark.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}