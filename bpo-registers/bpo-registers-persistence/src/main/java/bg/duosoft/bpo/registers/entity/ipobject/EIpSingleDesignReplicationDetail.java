package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_single_design_replication_details", schema = "ip_objects")
public class EIpSingleDesignReplicationDetail implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "single_design_id")
    private String singleDesignId;

    @Column(name = "main_design_replication_detail_id")
    private Integer mainDesignReplicationDetailId;

    @Column(name = "operation_code")
    private String operationCode;

    @Column(name = "registration_number")
    private String registrationNumber;

}