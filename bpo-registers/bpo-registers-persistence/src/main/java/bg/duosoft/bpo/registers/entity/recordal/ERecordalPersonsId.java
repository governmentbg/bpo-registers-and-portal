package bg.duosoft.bpo.registers.entity.recordal;

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
public class ERecordalPersonsId implements Serializable {

    @Column(name = "recordal_id")
    private String recordalId;

    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "person_role")
    private String personRole;

    @Override
    public int hashCode() {
        return Objects.hash(personRole, personId, recordalId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ERecordalPersonsId entity = (ERecordalPersonsId) o;
        return  Objects.equals(this.personRole, entity.personRole) &&
                Objects.equals(this.personId, entity.personId) &&
                Objects.equals(this.recordalId, entity.recordalId);
    }
}