package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.registers.utils.search.bridge.TrimLongSpecificationBridge;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_mark_nice_class", schema = "ip_objects")
public class EIpMarkNiceClass implements BaseEntity<EIpMarkNiceClassId> {
    @EmbeddedId
    @IndexedEmbedded
    private EIpMarkNiceClassId id;

    @Column(name = "specification")
    @FullTextField(name = "specification", searchable = Searchable.YES, projectable = Projectable.NO, analyzer = "WordAnalyzer")
    @FullTextField(name = "specificationCustom", searchable = Searchable.YES, projectable = Projectable.NO, analyzer = "SemicolonAnalyzer", valueBridge = @ValueBridgeRef(type = TrimLongSpecificationBridge.class))
    private String specification;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpMarkNiceClass that = (EIpMarkNiceClass) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}