package bg.duosoft.bpo.registers.entity.ipobject;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 08.02.2022
 * Time: 11:52
 */
@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "ip_ftp_file_upload", schema = "ip_objects")
@AllArgsConstructor
@NoArgsConstructor
public class EIpFtpFileUpload implements BaseEntity<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "replicator_type")
    private String replicatorType;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "fileUpload")
    private List<EIpFtpFileUploadReplicationTime> replicationTimes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "fileUpload")
    private List<EIpFtpFileUploadStatus> status;
}
