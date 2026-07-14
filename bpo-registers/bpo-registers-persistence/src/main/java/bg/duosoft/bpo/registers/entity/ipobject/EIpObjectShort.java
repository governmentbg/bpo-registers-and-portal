package bg.duosoft.bpo.registers.entity.ipobject;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ip_object", schema = "ip_objects")
public class EIpObjectShort {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "alternate_key")
    private String alternateKey;

    @Column(name = "object_type")
    private String objectType;

}
