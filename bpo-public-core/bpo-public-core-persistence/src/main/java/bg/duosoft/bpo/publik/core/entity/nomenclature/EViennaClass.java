package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 13.02.2024
 * Time: 13:26
 */
@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "vienna_class", schema = "nomenclatures")
public class EViennaClass implements BaseEntity<String> {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "title_category_flag")
    private Integer titleCategoryFlag;

}
