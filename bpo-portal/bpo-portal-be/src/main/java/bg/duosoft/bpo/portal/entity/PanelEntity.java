package bg.duosoft.bpo.portal.entity;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "panel", schema = "portal")
public class PanelEntity implements BaseEntity<String> {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "label_bg")
    private String labelBg;

    @Column(name = "label_en")
    private String labelEn;

    @Column(name = "image")
    private String img;

    @Column(name = "description_bg")
    private String descriptionBg;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column(name = "panel_order")
    private Integer panelOrder;

    @Column(name = "parent_id")
    private String parentPanelId;

    @Column(name = "url")
    private String url;

    @Column(name = "open_in_new_tab")
    private Integer openInNewTab;

    @OneToMany(mappedBy = "parentPanelId")
    @OrderBy("panelOrder ASC")
    private List<PanelEntity> childrenPanels;
}
