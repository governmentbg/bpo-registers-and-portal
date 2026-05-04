package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "dsview_publication", schema = "nomenclatures")
@EqualsAndHashCode
public class EDsviewPublication implements BaseEntity<Integer> {
    @Id
    @Column(name = "publication_section")
    private Integer id;
    @Column(name = "publication_code_dsview")
    private String publicationCodeDsview;

}
