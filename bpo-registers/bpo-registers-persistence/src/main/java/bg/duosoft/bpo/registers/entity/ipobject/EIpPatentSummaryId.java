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
public class EIpPatentSummaryId implements Serializable {
    @Column(name = "patent_id")
    private String patentId;
    @Column(name = "language_code")
    private String languageCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpPatentSummaryId that = (EIpPatentSummaryId) o;
        return Objects.equals(patentId, that.patentId)
                && Objects.equals(languageCode, that.languageCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patentId, languageCode);
    }
}