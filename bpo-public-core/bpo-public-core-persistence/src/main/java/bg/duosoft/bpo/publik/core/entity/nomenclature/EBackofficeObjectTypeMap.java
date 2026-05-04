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
@Table(name = "backoffice_object_type_map", schema = "nomenclatures")
public class EBackofficeObjectTypeMap implements BaseEntity<String> {
    @Id
    @Column(name = "backoffice_application_type")
    private String id;
    @Column(name = "object_type")
    private String objectType;
    @Column(name = "process_type")
    private String processType;
}
