package bg.duosoft.bpo.registers.entity.ipobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class EIpPatentCpcClassId implements Serializable {

    @Column(name = "patent_id")
    private String patentId;

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
    public int hashCode() {
        return Objects.hash(editionCode, classCode, subclassCode, patentId, subgroupCode, sectionCode, groupCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EIpPatentCpcClassId entity = (EIpPatentCpcClassId) o;
        return Objects.equals(this.editionCode, entity.editionCode) &&
                Objects.equals(this.classCode, entity.classCode) &&
                Objects.equals(this.subclassCode, entity.subclassCode) &&
                Objects.equals(this.patentId, entity.patentId) &&
                Objects.equals(this.subgroupCode, entity.subgroupCode) &&
                Objects.equals(this.sectionCode, entity.sectionCode) &&
                Objects.equals(this.groupCode, entity.groupCode);
    }
}