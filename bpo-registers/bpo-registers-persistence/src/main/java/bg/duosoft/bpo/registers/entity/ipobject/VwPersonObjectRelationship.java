package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "vw_person_object_relationships", schema = "ip_objects")
public class VwPersonObjectRelationship implements BaseEntity<VwPersonObjectRelationshipId> {

    @EmbeddedId
    private VwPersonObjectRelationshipId id;

    @Column(name = "object_title")
    private String objectTitle;

    @Column(name = "object_title_en")
    private String objectTitleEn;

    @Column(name = "role_description")
    private String roleDescription;

    @Column(name = "role_description_en")
    private String roleDescriptionEn;

    @Column(name = "person_name")
    private String personName;

}
