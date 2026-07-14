package bg.duosoft.bpo.publik.core.entity.nomenclature;

import bg.duosoft.bpo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Cacheable(value = false)
@Table(name = "attachment_type", schema = "nomenclatures")
@NoArgsConstructor
@EqualsAndHashCode
public class EAttachmentType implements BaseEntity<String> {

    public EAttachmentType(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "code")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "backoffice_code")
    private String backofficeCode;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name="attachment_type_attachment_category",
            schema = "nomenclatures",
            joinColumns=@JoinColumn(name="attachment_type"),
            inverseJoinColumns=@JoinColumn(name="attachment_category")
    )
    private List<EAttachmentCategory> categories;

}