package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "legal_decision_ground_type", schema = "nomenclatures")
@NoArgsConstructor
public class ELegalDecisionGroundType implements BaseEntity<Integer> {
    public ELegalDecisionGroundType(Integer id) {
        this.id = id;
    }
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_en")
    private String nameEn;

}
