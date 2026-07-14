package bg.duosoft.bpo.portal.entity;


import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.common.EAttachment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "feedback_attachments", schema = "portal")
public class FeedbackAttachmentEntity implements BaseEntity<FeedbackAttachmentEntityId> {

    @EmbeddedId
    private FeedbackAttachmentEntityId id;

    @MapsId("attachmentId")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    private EAttachment attachment;

    @MapsId("feedbackId")
    @ManyToOne
    @JoinColumn(name = "feedback_id", referencedColumnName = "id")
    private FeedbackEntity feedback;
}
