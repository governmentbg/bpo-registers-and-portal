package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EObjectSubtype;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EObjectType;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EStatusMap;
import bg.duosoft.bpo.registers.entity.recordal.ERecordal;
import bg.duosoft.bpo.registers.utils.search.bridge.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.search.engine.backend.types.ObjectStructure;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBinderRef;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBridgeRef;
import org.hibernate.search.mapper.pojo.extractor.mapping.annotation.ContainerExtract;
import org.hibernate.search.mapper.pojo.extractor.mapping.annotation.ContainerExtraction;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_object", schema = "ip_objects")
public class EIpObject implements BaseEntity<String> {
    @Id
    @Column(name = "id")
    @FullTextField(name = "objectId", searchable = Searchable.NO, projectable = Projectable.YES)
    @GenericField(name = "fileId", searchable = Searchable.YES, projectable = Projectable.YES, sortable = Sortable.YES, valueBinder = @ValueBinderRef(type = FileIdBinder.class))
    @GenericField(name = "fileIdText", searchable = Searchable.YES, projectable = Projectable.NO, valueBinder = @ValueBinderRef(type = FileIdAsTextBinder.class))
    private String id;

    @Column(name = "alternate_key")
    private String alternateKey;

    @ManyToOne(optional = false)
    @JoinColumn(name = "object_type", referencedColumnName = "code", updatable = false)
    @IndexedEmbedded
    private EObjectType objectType;

    @Column(name = "title")
    @FullTextField(name = "title", searchable = Searchable.YES, projectable = Projectable.YES, analyzer = "WordAnalyzer")
    @FullTextField(name = "titleCustom", searchable = Searchable.YES, projectable = Projectable.NO, analyzer = "FullTextAnalyzer")
    @KeywordField(name = "titleSort", sortable = Sortable.YES, searchable = Searchable.NO, projectable = Projectable.NO, indexNullAs = "", normalizer = "sortNormalizer")
    private String title;

    @Column(name = "title_en")
    private String titleEn;

    @Column(name = "notes")
    private String notes;

    @Column(name = "filing_date")
    @GenericField(searchable = Searchable.YES, projectable = Projectable.YES, sortable = Sortable.YES)
    private LocalDate filingDate;

    @Column(name = "status_date")
    @GenericField(searchable = Searchable.YES, projectable = Projectable.YES)
    private LocalDate statusDate;

    @Column(name = "entitlement_date")
    @GenericField(searchable = Searchable.YES, projectable = Projectable.YES)
    private LocalDate entitlementDate;

    @Column(name = "expiration_date")
    @GenericField(searchable = Searchable.YES, projectable = Projectable.YES)
    private LocalDate expirationDate;

    @Column(name = "registration_date")
    @GenericField(searchable = Searchable.YES, projectable = Projectable.YES, sortable = Sortable.YES)
    private LocalDate registrationDate;

    @Column(name = "date_updated")
    private LocalDate dateUpdated;

    @Column(name = "registration_number")
    @GenericField(name = "registrationNumberText", searchable = Searchable.YES, projectable = Projectable.YES)
    @GenericField(name = "registrationNumber", searchable = Searchable.YES, projectable = Projectable.YES, sortable = Sortable.YES, valueBridge = @ValueBridgeRef(type = RegistrationNumberBridge.class))
    private String registrationNumber;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumns({
            @JoinColumn(name = "object_subtype", referencedColumnName = "code", updatable = false)}
    )
    @IndexedEmbedded
    private EObjectSubtype objectSubType;


    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumns({
            @JoinColumn(name = "status", referencedColumnName = "code", updatable = false)}
    )
    @IndexedEmbedded
    private EStatusMap status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "object_id1", referencedColumnName = "id", updatable = false, insertable = false)
    private List<EIpObjectRelationship> directRelationships;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "object_id2", referencedColumnName = "id", updatable = false, insertable = false)
    private List<EIpObjectRelationship> inverseRelationships;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "object_id", referencedColumnName = "id", updatable = false, insertable = false)
    @IndexedEmbedded(structure = ObjectStructure.NESTED)
    private List<EIpObjectPriority> priorities;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "object_id", referencedColumnName = "id", updatable = false, insertable = false)
    @OrderBy("publicationDate ASC")
    @IndexedEmbedded(structure = ObjectStructure.NESTED)
    private List<EIpObjectPublication> publications;

    @Column(name = "published")
    private Integer published;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "object_id", referencedColumnName = "id", updatable = false, insertable = false)
    @IndexedEmbedded(structure = ObjectStructure.NESTED)
    @GenericField(name = "mainOwnerPersonName", sortable = Sortable.YES, searchable = Searchable.NO, projectable = Projectable.YES, valueBridge = @ValueBridgeRef(type = IpObjectMainOwnerBridge.class), extraction = @ContainerExtraction(extract = ContainerExtract.NO))
    private List<EIpPersonToIpObject> persons;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ip_object_id", referencedColumnName = "id", updatable = false, insertable = false)
    @OrderBy("filingDate ASC")
    private List<ERecordal> recordals;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "object_id", referencedColumnName = "id", updatable = false, insertable = false)
    private List<EIpObjectAttachment> attachments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpObject eIpObject = (EIpObject) o;
        return Objects.equals(id, eIpObject.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}