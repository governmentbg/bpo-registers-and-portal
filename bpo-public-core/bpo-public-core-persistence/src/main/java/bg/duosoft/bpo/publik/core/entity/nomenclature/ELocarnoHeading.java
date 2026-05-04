package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 14.11.2024
 * Time: 12:02
 */
@Table(name = "locarno_heading", schema = "nomenclatures")
@Data
@Entity
public class ELocarnoHeading implements BaseEntity<String> {
    @Id
    @Column(name = "id", length = 2)
    private String id;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "locarnoHeading", cascade = CascadeType.ALL)
    private List<ELocarnoClass> classes;
}
