package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "nice_class", schema = "nomenclatures")
public class ENiceClass implements BaseEntity<Integer> {
    @Id
    @Column(name = "code")
    private Integer id;
    @Column(name = "heading")
    private String heading;
    @Column(name = "alpha_list")
    private String alphaList;
}
