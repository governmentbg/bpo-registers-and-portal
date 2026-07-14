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
public class EIpPersonToIpObjectId implements Serializable {

    @Column(name = "object_id")
    private String objectId;

    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "person_role")
    private String personRole;

    @Override
    public int hashCode() {
        return Objects.hash(personRole, personId, objectId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpPersonToIpObjectId entity = (EIpPersonToIpObjectId) o;
        return  Objects.equals(this.personRole, entity.personRole) &&
                Objects.equals(this.personId, entity.personId) &&
                Objects.equals(this.objectId, entity.objectId);
    }
}