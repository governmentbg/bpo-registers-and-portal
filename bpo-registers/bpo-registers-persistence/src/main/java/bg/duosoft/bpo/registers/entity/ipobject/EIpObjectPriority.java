package bg.duosoft.bpo.registers.entity.ipobject;


import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ECountry;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPriorityType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_object_priority", schema = "ip_objects")
public class EIpObjectPriority implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "object_id")
    private String objectId;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "country")
    @IndexedEmbedded
    private ECountry country;

    @Column(name = "priority_number")
    @KeywordField(searchable = Searchable.YES,  projectable = Projectable.NO)
    private String priorityNumber;

    @Column(name = "priority_date")
    @GenericField(searchable = Searchable.YES,  projectable = Projectable.NO)
    private LocalDate priorityDate;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "status")
    @GenericField(searchable = Searchable.YES,  projectable = Projectable.NO)
    private Integer status;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "priority_type", referencedColumnName = "backoffice_code")
    private EPriorityType priorityType;

    @Column(name = "priority_order")
    private Integer priorityOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpObjectPriority that = (EIpObjectPriority) o;
        return Objects.equals(id, that.id)
                && Objects.equals(objectId, that.objectId)
                && Objects.equals(priorityNumber, that.priorityNumber)
                && Objects.equals(priorityDate, that.priorityDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, objectId, priorityNumber, priorityDate);
    }
}