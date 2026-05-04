package bg.duosoft.bpo.publik.core.entity.nomenclature;


import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "backoffice_epopatent_status_map", schema = "nomenclatures")
public class EEpoPatentStatusMap implements BaseEntity<String> {

    @Id
    @Column(name = "backoffice_status_code")
    private String id;

    @Column(name = "ep_status_code")
    private String epStatusCode;

    @Column(name = "fep_inval_date_tag")
    private String fepInvalDateTag;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "backoffice_status_code", referencedColumnName = "code", updatable = false, insertable = false)
    private EStatusMap statusMap;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EEpoPatentStatusMap that = (EEpoPatentStatusMap) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
