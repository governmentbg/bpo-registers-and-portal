package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "attachment_category", schema = "nomenclatures")
@NoArgsConstructor
@EqualsAndHashCode
public class EAttachmentCategory implements BaseEntity<String> {

    public EAttachmentCategory(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "code")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_en")
    private String nameEn;

}