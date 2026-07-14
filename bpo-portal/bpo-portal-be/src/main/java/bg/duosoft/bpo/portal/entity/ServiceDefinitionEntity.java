package bg.duosoft.bpo.portal.entity;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "service_definition", schema = "portal")
public class ServiceDefinitionEntity implements BaseEntity<String> {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "label_bg")
    private String labelBg;

    @Column(name = "label_en")
    private String labelEn;

    @Column(name = "description_bg")
    private String descriptionBg;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column(name = "new_url")
    private String newUrl;

    @Column(name = "view_url")
    private String viewUrl;

    @Column(name = "service_order")
    private Integer serviceOrder;

    @Column(name = "panel_id")
    private String panelId;
}
