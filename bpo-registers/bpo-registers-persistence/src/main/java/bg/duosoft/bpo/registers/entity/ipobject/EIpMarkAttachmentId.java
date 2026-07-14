package bg.duosoft.bpo.registers.entity.ipobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class EIpMarkAttachmentId implements Serializable {
    @Column(name = "mark_id")
    private String markId;
    @Column(name = "att_seq_nbr")
    private Integer attachmentSequenceNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpMarkAttachmentId that = (EIpMarkAttachmentId) o;
        return Objects.equals(markId, that.markId)
                && Objects.equals(attachmentSequenceNumber, that.attachmentSequenceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(markId, attachmentSequenceNumber);
    }
}