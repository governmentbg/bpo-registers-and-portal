package bg.duosoft.bpo.registers.entity.ipobject;


import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EObjectSubtype;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ESingleDesignType;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EStatusMap;
import bg.duosoft.bpo.registers.utils.search.bridge.FileIdBridge;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.automaticindexing.ReindexOnUpdate;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;

import java.util.List;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_single_design", schema = "ip_objects")
public class EIpSingleDesign implements BaseEntity<String> {

    @Id
    @Column(name = "single_design_id")
    @KeywordField(name = "id", projectable = Projectable.YES, searchable = Searchable.YES)
    @GenericField(name = "fileId", searchable = Searchable.YES,  projectable = Projectable.YES, valueBridge = @ValueBridgeRef(type = FileIdBridge.class) )
    private String id;

    @Column(name = "name")
    @FullTextField(name = "name", searchable = Searchable.YES, projectable = Projectable.NO, analyzer = "WordAnalyzer")
    @FullTextField(name = "nameCustom", searchable = Searchable.YES, projectable = Projectable.NO, analyzer = "FullTextAnalyzer")
    private String name;

    @Column(name = "name_en")
    @FullTextField(name = "nameEn", searchable = Searchable.YES, projectable = Projectable.NO, analyzer = "WordAnalyzer")
    @FullTextField(name = "nameEnCustom", searchable = Searchable.YES, projectable = Projectable.NO, analyzer = "FullTextAnalyzer")
    private String nameEn;

    @Column(name = "verbal_element")
    @FullTextField(name = "verbalElement", searchable = Searchable.YES, projectable = Projectable.YES, analyzer = "WordAnalyzer")
    @FullTextField(name = "verbalElementCustom", searchable = Searchable.YES, projectable = Projectable.NO, analyzer = "FullTextAnalyzer")
    private String verbalElement;

    @Column(name = "verbal_element_en")
    @FullTextField(name = "verbalElementEn", searchable = Searchable.YES, projectable = Projectable.YES, analyzer = "WordAnalyzer")
    @FullTextField(name = "verbalElementEnCustom", searchable = Searchable.YES, projectable = Projectable.NO, analyzer = "FullTextAnalyzer")
    private String verbalElementEn;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "single_design_type", referencedColumnName = "code", updatable = false)
    @IndexedEmbedded
    private ESingleDesignType designType;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumns({
            @JoinColumn(name = "status", referencedColumnName = "code", updatable = false)}
    )
    private EStatusMap status;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "single_design_id", referencedColumnName = "single_design_id", updatable = false, insertable = false)
    private List<EIpSingleDesignDrawing> drawings;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "single_design_id", referencedColumnName = "single_design_id", updatable = false, insertable = false)
    @IndexedEmbedded
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
    private List<EIpSingleDesignLocarno> locarnos;

    @Column(name = "published")
    private Integer published;
    @Column(name = "alternate_key")
    private String alternateKey;
    @Column(name = "st13")
    private String st13;
    @Column(name = "design_number")
    private Integer designNumber;


    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumns({
            @JoinColumn(name = "main_design_id", referencedColumnName = "design_id", updatable = false)}
    )
    private EIpDesign mainDesign;

}
