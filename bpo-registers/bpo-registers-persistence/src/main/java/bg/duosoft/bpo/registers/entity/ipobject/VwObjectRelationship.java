package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "vw_object_relationships", schema = "ip_objects")
public class VwObjectRelationship implements BaseEntity<VwObjectRelationshipId> {

    @EmbeddedId
    private VwObjectRelationshipId id;

    @Column(name = "relationship_name")
    private String relationshipName;

    @Column(name = "relationship_name_en")
    private String relationshipNameEn;

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

    @Column(name = "related_object_id")
    private String relatedObjectId;
}
