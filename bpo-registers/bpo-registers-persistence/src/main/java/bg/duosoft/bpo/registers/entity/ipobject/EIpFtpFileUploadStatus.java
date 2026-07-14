package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * User: ggeorgiev
 * Date: 17.02.2026
 * Time: 13:53
 */
@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_ftp_file_upload_status", schema = "ip_objects")
@AllArgsConstructor
@NoArgsConstructor
public class EIpFtpFileUploadStatus implements BaseEntity<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status")
    private Integer status;

    @Column(name = "error_count")
    private Integer errorCount;

    @Column(name = "date_sent")
    private LocalDateTime dateSent;

    @Column(name = "date_last_error")
    private LocalDateTime dateLastError;

    @Column(name = "consumer")
    private String consumer;

    @ManyToOne
    @JoinColumn(name = "ffu_id", referencedColumnName = "id")
    private EIpFtpFileUpload fileUpload;
}
