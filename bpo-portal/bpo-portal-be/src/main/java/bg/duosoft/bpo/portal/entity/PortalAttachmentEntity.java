package bg.duosoft.bpo.portal.entity;

import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.common.EAttachment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "portal_attachments", schema = "portal", catalog = "bpo_registers")
public class PortalAttachmentEntity implements BaseEntity<String> {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "label_bg")
    private String labelBg;

    @Column(name = "label_en")
    private String labelEn;

    @Column(name = "image")
    private String image;

    @Column(name = "description_bg")
    private String descriptionBg;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column(name = "position")
    private Integer position;

    @Column(name = "visible_flag", nullable = false)
    private Integer visibleFlag;

    @OneToOne
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    private EAttachment attachment;
}
