package bg.duosoft.bpo.portal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class FeedbackAttachmentEntityId implements Serializable {
    @Column(name = "feedback_id")
    private Integer feedbackId;

    @Column(name = "attachment_id")
    private Integer attachmentId;
}
