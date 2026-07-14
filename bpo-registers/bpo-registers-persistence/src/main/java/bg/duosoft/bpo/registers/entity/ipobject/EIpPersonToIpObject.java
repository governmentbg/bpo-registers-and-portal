package bg.duosoft.bpo.registers.entity.ipobject;


import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPersonRole;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ERepresentativeType;
import bg.duosoft.bpo.registers.utils.search.bridge.RepresentativeBinder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.TypeBinderRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.TypeBinding;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_person_to_ip_object", schema = "ip_objects")
@TypeBinding(binder = @TypeBinderRef(type = RepresentativeBinder.class))
public class EIpPersonToIpObject implements BaseEntity<EIpPersonToIpObjectId> {
    @EmbeddedId
//    @GenericField(name = "id", searchable = Searchable.YES,  projectable = Projectable.YES, valueBinder = @ValueBridgeRef(type = PersonIdBridge.class) )
    private EIpPersonToIpObjectId id;

    @Column(name = "person_order")
    private Integer personOrder;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumns({
            @JoinColumn(name = "person_id", referencedColumnName = "person_id", insertable = false, updatable = false)
    })
    @IndexedEmbedded
    private EIpPerson person;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "person_role", referencedColumnName = "code", insertable = false, updatable = false)
    @IndexedEmbedded
    private EPersonRole role;

    @ManyToOne
    @JoinColumn(name = "representative_type", referencedColumnName = "representative_type")
    private ERepresentativeType representativeType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpPersonToIpObject that = (EIpPersonToIpObject) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}