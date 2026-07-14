package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "patent_ipc_class", schema = "nomenclatures")
public class EPatentIpcClass implements BaseEntity<EPatentIpcClassId> {
    @EmbeddedId
    private EPatentIpcClassId id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_en")
    private String nameEn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EPatentIpcClass that = (EPatentIpcClass) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, this.name) &&
                Objects.equals(nameEn, this.nameEn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nameEn);
    }
}