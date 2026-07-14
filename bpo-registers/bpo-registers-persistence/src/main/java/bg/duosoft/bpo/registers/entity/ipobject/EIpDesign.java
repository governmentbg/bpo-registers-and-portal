package bg.duosoft.bpo.registers.entity.ipobject;


import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.mapper.pojo.automaticindexing.ReindexOnUpdate;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexingDependency;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_design", schema = "ip_objects")
@Indexed(index = "v002_eipdesign")
public class EIpDesign implements BaseEntity<String>, IpObjectBase {

    @Id
    @Column(name = "design_id")
    private String id;

    @Column(name = "application_comment")
    private String applicationComment;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @Column(name = "deferment_expiration_date")
    private LocalDate defermentExpirationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "design_id", referencedColumnName = "id", updatable = false, insertable = false)
    @IndexedEmbedded
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
    private EIpObject ipObject;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "main_design_id", referencedColumnName = "design_id", updatable = false, insertable = false)
    @IndexedEmbedded
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
    private List<EIpSingleDesign> singleDesigns;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EIpDesign eIpDesign = (EIpDesign) o;
        return Objects.equals(id, eIpDesign.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
