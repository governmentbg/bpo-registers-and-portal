package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 28.03.2022
 * Time: 16:06
 */
@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "agent_speciality", schema = "nomenclatures")
@NoArgsConstructor
public class EAgentSpeciality implements BaseEntity<String> {
    @Id
    @Column(name = "code")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "short_name_en")
    private String shortNameEn;

    @Column(name = "ipas_code")
    private Integer ipasCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EAgentSpeciality that = (EAgentSpeciality) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
