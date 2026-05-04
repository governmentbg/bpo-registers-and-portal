package bg.duosoft.bpo.registers.entity.legaldecision;

import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ELegalDecisionGroundType;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ELegalDecisionType;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EObjectType;
import bg.duosoft.bpo.publik.core.entity.common.EAttachment;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "document", schema = "legal_decisions")
@EqualsAndHashCode
public class ELegalDecisionDocument implements BaseEntity<String> {

    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "doc_type")
    private ELegalDecisionType documentType;

    @Column(name = "doc_date")
    private LocalDate documentDate;

    @Column(name = "object_id")
    private String objectId;

    @Column(name = "doc_num")
    private Integer documentNumber;


    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "document_ground_types", schema = "legal_decisions",
            joinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "legal_ground_type", referencedColumnName = "id")
    )
    private List<ELegalDecisionGroundType> legalGroundTypes;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "object_type")
    private EObjectType objectType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    private EAttachment attachment;

    @Column(name = "title")
    private String title;

}