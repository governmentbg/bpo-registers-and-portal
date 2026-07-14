package bg.duosoft.bpo.publik.core.entity.common;


import bg.duosoft.bpo.common.entity.BaseEntity;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EAttachmentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "attachment", schema = "common")
//TO DO
//@EntityListeners({MinIOListener.class})
public class EAttachment implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "file_size")
    private Integer fileSize;

    @Column(name = "bucket_name")
    private String bucketName;

    @Column(name = "file_location")
    private String fileLocation;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "attachment_type_code", referencedColumnName = "code")
    private EAttachmentType attachmentType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EAttachment that = (EAttachment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
