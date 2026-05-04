package bg.duosoft.bpo.publik.core.entity.common;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

/**
 * User: ggeorgiev
 * Date: 25.03.2022
 * Time: 11:49
 */
@Table(name = "attachment_deletes", schema = "common")
@Data
@Entity
public class EAttachmentDeletes implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "bucket_name")
    private String bucketName;

    @Column(name = "file_location")
    private String fileLocation;

    @Column(name = "status")
    private Integer status;

    @Column(name = "attachment_id")
    private Integer attachmentId;
}
