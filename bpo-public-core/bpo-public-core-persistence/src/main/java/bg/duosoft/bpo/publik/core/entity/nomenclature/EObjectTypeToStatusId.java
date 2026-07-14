package bg.duosoft.bpo.publik.core.entity.nomenclature;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Embeddable
public class EObjectTypeToStatusId implements Serializable {
    @Column(name = "object_type")
    private String objectType;

    @Column(name = "status_id")
    private Integer statusId;
}
