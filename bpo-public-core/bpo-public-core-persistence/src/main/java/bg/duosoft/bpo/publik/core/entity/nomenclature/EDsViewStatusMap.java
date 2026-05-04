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
@Table(name = "backoffice_dsview_status_map", schema = "nomenclatures")
public class EDsViewStatusMap implements BaseEntity<String> {


    @Id
    @Column(name = "backoffice_status_code")
    private String id;


    @Column(name = "dsview_list_code")
    private String dsviewListCode;

    @Column(name = "dsview_detailed_code")
    private String dsviewDetailedCode;

    @Column(name = "dsview_detailed_code_bg")
    private String dsviewDetailedCodeBg;


    @Column(name = "deferred_publication")
    private Integer deferredPublication;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "backoffice_status_code", referencedColumnName = "code", updatable = false, insertable = false)
    private EStatusMap statusMap;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDsViewStatusMap that = (EDsViewStatusMap) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
