package bg.duosoft.bpo.publik.core.entity.nomenclature;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 20.02.2024
 * Time: 13:49
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Embeddable
public class EBackofficeObjectSubtypeMapId implements Serializable {
    @Column(name = "backoffice_application_type")
    private String backofficeApplicationType;
    @Column(name = "backoffice_application_subtype")
    private String backofficeApplicationSubtype;
}
