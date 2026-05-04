package bg.duosoft.bpo.registers.entity.recordal;

import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ERecordalDetailType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 25.10.2021
 * Time: 13:01
 */
@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "details", schema = "ip_object_recordals")
public class ERecordalDetail implements BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recordal_id", referencedColumnName = "id")
    private ERecordal recordal;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "detail_type_id")
    private ERecordalDetailType type;

    @Column(name = "description")
    private String description;

    @Column(name = "order_by")
    private Integer orderBy;

}
