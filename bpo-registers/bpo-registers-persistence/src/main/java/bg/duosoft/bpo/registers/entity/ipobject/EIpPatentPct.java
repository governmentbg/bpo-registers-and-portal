package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ECountry;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_patent_pct", schema = "ip_objects")
public class EIpPatentPct implements BaseEntity<String> {
    @Id
    @Column(name = "patent_id")
    private String id;

    @Column(name = "pct_application_id")
    private String pctApplicationId;

    @Column(name = "pct_application_date")
    private LocalDate pctApplicationDate;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "pct_publication_country_code")
    private ECountry pctPublicationCountry;

    @Column(name = "pct_publication_id")
    private String pctPublicationId;

    @Column(name = "pct_publication_date")
    private LocalDate pctPublicationDate;

    @Column(name = "pct_phase")
    private Integer pctPhase;

    @OneToOne
    @JoinColumn(name = "patent_id", referencedColumnName = "patent_id")
    private EIpPatent patent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpPatentPct that = (EIpPatentPct) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}