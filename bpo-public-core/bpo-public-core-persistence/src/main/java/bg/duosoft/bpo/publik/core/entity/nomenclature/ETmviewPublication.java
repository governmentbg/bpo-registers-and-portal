package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * User: ggeorgiev
 * Date: 07.03.2024
 * Time: 17:59
 */
@Entity
@Getter
@Setter
@Table(name = "tmview_publication", schema = "nomenclatures")
@EqualsAndHashCode
public class ETmviewPublication implements BaseEntity<Integer> {
    @Id
    @Column(name = "publication_section")
    private Integer id;
    @Column(name = "publication_code_tmview")
    private String publicationCodeTmview;

}
