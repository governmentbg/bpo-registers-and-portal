package bg.duosoft.bpo.registers.entity.ipobject;


import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ECountry;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_person", schema = "ip_objects")
@EqualsAndHashCode
public class EIpPerson implements BaseEntity<Integer> {
    @Id
    @Column(name = "person_id")
    private Integer id;

    @Column(name = "name")
    @FullTextField(name = "name", searchable = Searchable.YES, projectable = Projectable.YES, analyzer = "WordAnalyzer")
    @FullTextField(name = "personNameCustom", searchable = Searchable.YES, projectable = Projectable.NO, analyzer = "FullTextAnalyzer")
    @KeywordField(name = "personNameSort", searchable = Searchable.NO,  projectable = Projectable.YES, normalizer = "sortNormalizer")
    @FullTextField(name = "personNameExact", searchable = Searchable.YES,  projectable = Projectable.NO)
    private String name;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "nationality_country_code")
    @IndexedEmbedded
    private ECountry nationalityCountryCode;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "portal_user_number")
    private Integer portalUserNumber;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private EIpPersonAddress address;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private EIpAgent agent;

    @Column(name = "legal_type")
    private Integer legalType;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "last_name")
    private String lastName;


}