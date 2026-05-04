package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.ERelationshipType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_object_relationship", schema = "ip_objects")
public class EIpObjectRelationship implements BaseEntity<EIpObjectRelationshipId> {

    @EmbeddedId
    private EIpObjectRelationshipId id;

    @Column(name = "filing_number")
    private String filingNumber;

    @Column(name = "filing_date")
    private LocalDate filingDate;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "registration_country")
    private String registrationCountry;

    @Column(name = "cancellation_date")
    private LocalDate cancellationDate;

    @Column(name = "priority_date")
    private LocalDate priorityDate;

    @Column(name = "serve_message_date")
    private LocalDate serveMessageDate;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumns({
            @JoinColumn(name = "relationship_typ", referencedColumnName = "relationship_typ", insertable = false, updatable = false),
            @JoinColumn(name = "application_typ", referencedColumnName = "application_typ", insertable = false, updatable = false)}
    )
    private ERelationshipType relationshipType;
    @Column(name = "rel_scope")
    private Integer relScope;
    @Column(name = "rel_text")
    private String relText;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EIpObjectRelationship that = (EIpObjectRelationship) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
