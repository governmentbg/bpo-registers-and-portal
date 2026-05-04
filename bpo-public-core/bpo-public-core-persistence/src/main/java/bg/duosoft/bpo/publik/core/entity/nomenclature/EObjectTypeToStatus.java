package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "object_type_to_status", schema = "nomenclatures")
public class EObjectTypeToStatus implements BaseEntity<EObjectTypeToStatusId> {

    @EmbeddedId
    private EObjectTypeToStatusId id;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumns({
            @JoinColumn(name = "object_type", referencedColumnName = "code", updatable = false, insertable = false)}
    )
    private EObjectType objectType;


    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumns({
            @JoinColumn(name = "status_code", referencedColumnName = "code", updatable = false, insertable = false)}
    )
    private EStatusMap statusMap;

}
