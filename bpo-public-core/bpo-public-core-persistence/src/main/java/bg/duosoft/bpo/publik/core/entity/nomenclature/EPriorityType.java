package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "priority_type", schema = "nomenclatures")
@NoArgsConstructor
public class EPriorityType implements BaseEntity<String> {
    @Id
    @Column(name = "backoffice_code")
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "description_en")
    private String descriptionEn;

    public EPriorityType(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EPriorityType that = (EPriorityType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}