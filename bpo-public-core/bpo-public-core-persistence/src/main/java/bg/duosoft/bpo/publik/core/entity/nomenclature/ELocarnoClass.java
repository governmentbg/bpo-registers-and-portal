package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

/**
 * User: ggeorgiev
 * Date: 14.11.2024
 * Time: 12:04
 */
@Table(name = "locarno_class", schema = "nomenclatures")
@Data
@Entity
public class ELocarnoClass implements BaseEntity<String> {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "heading_id")
    private String headingId;
    @Column(name = "class_id")
    private String classId;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id", updatable = false, insertable = false)
    private ELocarnoHeading locarnoHeading;

}
