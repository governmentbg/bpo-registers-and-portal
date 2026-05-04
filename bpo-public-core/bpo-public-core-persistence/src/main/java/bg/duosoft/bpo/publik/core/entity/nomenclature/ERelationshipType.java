package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "relationship_type", schema = "nomenclatures")
public class ERelationshipType implements BaseEntity<ERelationshipTypeId> {
    @EmbeddedId
    private ERelationshipTypeId id;

    @Column(name = "relationship_name")
    private String relationshipName;

    @Column(name = "relationship_name_en")
    private String relationshipNameEn;

    @Column(name = "direct_relationship_name")
    private String directRelationshipName;

    @Column(name = "direct_relationship_name_en")
    private String directRelationshipNameEn;

    @Column(name = "inverse_relationship_name")
    private String inverseRelationshipName;

    @Column(name = "inverse_relationship_name_en")
    private String inverseRelationshipNameEn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ERelationshipType that = (ERelationshipType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
