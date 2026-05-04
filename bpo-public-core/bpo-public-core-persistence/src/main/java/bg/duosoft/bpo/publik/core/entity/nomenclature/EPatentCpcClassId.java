package bg.duosoft.bpo.publik.core.entity.nomenclature;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class EPatentCpcClassId implements Serializable {
    @Column(name = "edition_code")
    private String editionCode;

    @Column(name = "section_code")
    private String sectionCode;

    @Column(name = "class_code")
    private String classCode;

    @Column(name = "subclass_code")
    private String subclassCode;

    @Column(name = "group_code")
    private String groupCode;

    @Column(name = "subgroup_code")
    private String subgroupCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EPatentCpcClassId that = (EPatentCpcClassId) o;
        return Objects.equals(editionCode, that.editionCode)
                && Objects.equals(sectionCode, that.sectionCode)
                && Objects.equals(classCode, that.classCode)
                && Objects.equals(subclassCode, that.subclassCode)
                && Objects.equals(groupCode, that.groupCode)
                && Objects.equals(subgroupCode, that.subgroupCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(editionCode,
                sectionCode,
                classCode,
                subclassCode,
                groupCode,
                subgroupCode);
    }
}