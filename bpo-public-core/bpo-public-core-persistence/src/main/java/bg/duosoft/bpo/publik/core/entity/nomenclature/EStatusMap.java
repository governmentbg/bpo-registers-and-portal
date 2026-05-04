package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "backoffice_status_map", schema = "nomenclatures")
public class EStatusMap implements BaseEntity<String> {

    @Id
    @Column(name = "code")
    @GenericField(name = "statusId", searchable = Searchable.YES, projectable = Projectable.YES, sortable = Sortable.YES)
    private String id;

    @Column(name = "backoffice_status_name")
    private String backofficeStatusName;

    @Column(name = "for_deletion")
    private Integer forDeletion;

    @Column(name = "bpo_online_status")
    private String bpoOnlineStatus;

    @Column(name = "bpo_online_status_en")
    private String bpoOnlineStatusEn;

    @Column(name = "show_expiration_date")
    private Integer showExpirationDate;

    @Column(name = "process_type")
    private String processType;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "statusMap")
    private ETmViewStatusMap tmViewStatusMap;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "statusMap")
    private EEpoPatentStatusMap epoPatentStatusMap;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "statusMap")
    private EDsViewStatusMap dsViewStatusMap;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "statusMap")
    private EPatentStatusMap patentStatusMap;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EStatusMap status = (EStatusMap) o;
        return Objects.equals(id, status.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
