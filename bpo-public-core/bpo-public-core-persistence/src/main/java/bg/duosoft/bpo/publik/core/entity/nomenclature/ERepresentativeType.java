package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "representative_type", schema = "nomenclatures")
@Cacheable(value = false)
@NoArgsConstructor
public class ERepresentativeType implements BaseEntity<String> {

    @Id
    @Column(name = "representative_type")
    @KeywordField(name = "code",  searchable = Searchable.YES, projectable = Projectable.YES)
    private String id;

    public ERepresentativeType(String id) {
        this.id = id;
    }

    @Column(name = "description")
    private String description;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column(name = "ipas_partnership_type")
    private String ipasPartnershipType;
    @Column(name = "ipas_representative_type")
    private String ipasRepresentativeType;
    @Column(name = "euipo_representative_kind_code")
    private String euipoRepresentativeKindCode;
    @Column(name = "has_agent_code")
    private Integer hasAgentCode;
    @Column(name = "agent_code_from")
    private Integer agentCodeFrom;
    @Column(name = "agent_code_to")
    private Integer agentCodeTo;
    @Column(name = "st36_name")
    private String st36Name;
    @Column(name = "st36_name_en")
    private String st36NameEn;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ERepresentativeType representativeType = (ERepresentativeType) o;
        return Objects.equals(this.id, representativeType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
