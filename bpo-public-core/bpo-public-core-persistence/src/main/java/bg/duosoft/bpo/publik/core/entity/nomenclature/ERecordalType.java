package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 25.10.2021
 * Time: 12:07
 */
@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "recordal_type", schema = "nomenclatures")
public class ERecordalType implements BaseEntity<String> {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "description_en")
    private String descriptionEn;
}
