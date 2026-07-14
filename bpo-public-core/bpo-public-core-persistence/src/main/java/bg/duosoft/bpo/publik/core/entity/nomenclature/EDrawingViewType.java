package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "drawing_view_type", schema = "nomenclatures")
@Cacheable(value = false)
public class EDrawingViewType implements BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "view_type_name")
    private String viewTypeName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDrawingViewType that = (EDrawingViewType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
