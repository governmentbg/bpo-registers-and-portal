package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPublicationSection;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@EqualsAndHashCode
@Table(name = "ip_object_publication", schema = "ip_objects")
public class EIpObjectPublication implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "object_id")
    private String objectId;

    @Column(name = "publication_number")
    @KeywordField(searchable = Searchable.YES, projectable = Projectable.NO)
    private String publicationNumber;

    @Column(name = "publication_year")
    @GenericField(searchable = Searchable.YES, projectable = Projectable.NO)
    private Integer publicationYear;

    @ManyToOne
    @JoinColumn(name = "publication_section")
    @IndexedEmbedded
    private EPublicationSection publicationSection;

    @Column(name = "seq_nbr")
    private Integer seqNbr;

    @Column(name = "publication_date")
    @GenericField(searchable = Searchable.YES, projectable = Projectable.YES)
    private LocalDate publicationDate;

    @Column(name = "journal_nbr")
    private Integer journalNbr;

    @Column(name = "element_nbr")
    private Integer elementNbr;

    @Column(name = "journal_struct_node_nbr")
    private Integer journalStructNodeNbr;

    @Column(name = "is_public")
    private Boolean isPublic;

}