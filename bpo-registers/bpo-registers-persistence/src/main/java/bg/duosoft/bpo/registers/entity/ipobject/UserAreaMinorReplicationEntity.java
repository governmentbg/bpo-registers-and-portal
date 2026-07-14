package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * User: ggeorgiev
 * Date: 07.05.2025
 * Time: 11:46
 */
@Entity
@Table(name = "userarea_minor_replications", schema = "ip_objects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAreaMinorReplicationEntity implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "date_start")
    private LocalDateTime dateStart;
    @Column(name = "modified_rows")
    private Integer modifiedRows;
}
