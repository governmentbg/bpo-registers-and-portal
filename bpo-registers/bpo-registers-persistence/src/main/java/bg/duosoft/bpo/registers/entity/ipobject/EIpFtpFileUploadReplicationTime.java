package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User: ggeorgiev
 * Date: 08.02.2022
 * Time: 13:07
 */
@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_ftp_file_upload_to_replication_time", schema = "ip_objects")
@AllArgsConstructor
@NoArgsConstructor
public class EIpFtpFileUploadReplicationTime implements BaseEntity<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rte_id")
    private Integer rteId;

    @ManyToOne
    @JoinColumn(name = "ffu_id", referencedColumnName = "id")
    private EIpFtpFileUpload fileUpload;
}
