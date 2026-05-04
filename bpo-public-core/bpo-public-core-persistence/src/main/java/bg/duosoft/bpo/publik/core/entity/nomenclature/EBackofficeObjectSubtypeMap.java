package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * User: ggeorgiev
 * Date: 20.02.2024
 * Time: 13:49
 */
@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "backoffice_object_subtype_map", schema = "nomenclatures")
public class EBackofficeObjectSubtypeMap implements BaseEntity<EBackofficeObjectSubtypeMapId> {
    @EmbeddedId
    private EBackofficeObjectSubtypeMapId id;

    @Column(name = "object_subtype")
    private String objectSubtype;
}
