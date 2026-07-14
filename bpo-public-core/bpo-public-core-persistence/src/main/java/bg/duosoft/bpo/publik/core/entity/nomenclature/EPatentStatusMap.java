package bg.duosoft.bpo.publik.core.entity.nomenclature;


import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "backoffice_patent_status_map", schema = "nomenclatures")
public class EPatentStatusMap implements BaseEntity<String> {

    @Id
    @Column(name = "backoffice_status_code")
    private String id;

    @Column(name = "fo_status_code")
    private String foStatusCode;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "backoffice_status_code", referencedColumnName = "code", updatable = false, insertable = false)
    private EStatusMap statusMap;
}
