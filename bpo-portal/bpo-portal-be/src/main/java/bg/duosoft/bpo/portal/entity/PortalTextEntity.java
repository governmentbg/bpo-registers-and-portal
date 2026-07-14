package bg.duosoft.bpo.portal.entity;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "portal_text", schema = "portal")
public class PortalTextEntity implements BaseEntity<String> {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "description_bg")
    private String descriptionBg;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column(name = "dynamic_content")
    private Integer dynamicContent;

    @Column(name = "active_from")
    private LocalDateTime activeFrom;

    @Column(name = "active_to")
    private LocalDateTime activeTo;
}
