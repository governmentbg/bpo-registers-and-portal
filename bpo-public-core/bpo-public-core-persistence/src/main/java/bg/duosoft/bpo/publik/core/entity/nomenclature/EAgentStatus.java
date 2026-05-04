package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "agent_status", schema = "nomenclatures")
public class EAgentStatus implements BaseEntity<Integer> {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_en")
    private String nameEn;
}
