package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.registers.utils.search.bridge.PatentIpcClassBridge;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_patent_ipc_classes", schema = "ip_objects")
public class EIpPatentIpcClass implements BaseEntity<EIpPatentIpcClassId> {
    @EmbeddedId
    @KeywordField(name = "classId", searchable = Searchable.YES,  projectable = Projectable.YES, valueBridge = @ValueBridgeRef(type = PatentIpcClassBridge.class), normalizer = "sortNormalizer")
    private EIpPatentIpcClassId id;

    @Column(name = "class_order")
    private Integer classOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpPatentIpcClass that = (EIpPatentIpcClass) o;
        return Objects.equals(id, that.id) && Objects.equals(classOrder, that.classOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classOrder);
    }
}