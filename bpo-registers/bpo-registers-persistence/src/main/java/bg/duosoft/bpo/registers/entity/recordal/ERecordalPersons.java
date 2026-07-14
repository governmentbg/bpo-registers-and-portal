package bg.duosoft.bpo.registers.entity.recordal;


import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPerson;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ERecordalPersonRole;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "recordal_persons", schema = "ip_object_recordals")
@EqualsAndHashCode
public class ERecordalPersons implements BaseEntity<ERecordalPersonsId> {
    @EmbeddedId
    private ERecordalPersonsId id;

    @Column(name = "person_order")
    private Integer personOrder;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumns({
            @JoinColumn(name = "person_id", referencedColumnName = "person_id", insertable = false, updatable = false)
    })
    private EIpPerson person;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "person_role", referencedColumnName = "code", insertable = false, updatable = false)
    private ERecordalPersonRole role;

}