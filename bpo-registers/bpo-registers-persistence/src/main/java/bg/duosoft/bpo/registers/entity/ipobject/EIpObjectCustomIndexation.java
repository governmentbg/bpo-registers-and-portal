package bg.duosoft.bpo.registers.entity.ipobject;


import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_object_custom_indexation", schema = "ip_objects")
@NoArgsConstructor
@AllArgsConstructor
public class EIpObjectCustomIndexation implements BaseEntity<String> {
    @Id
    @Column(name = "object_id")
    private String id;

    @Column(name = "replicator_type")
    private String replicatorType;

    @Column(name = "reason")
    private String reason;

    @Column(name = "date")
    private LocalDateTime date;
}
