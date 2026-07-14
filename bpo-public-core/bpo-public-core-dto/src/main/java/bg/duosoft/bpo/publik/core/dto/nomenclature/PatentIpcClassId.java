package bg.duosoft.bpo.publik.core.dto.nomenclature;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatentIpcClassId implements Serializable {
    private String editionCode;
    private String sectionCode;
    private String classCode;
    private String subclassCode;
    private String groupCode;
    private String subgroupCode;

    @Override
    public String toString() {
        String str = sectionCode + classCode + subclassCode + groupCode + subgroupCode + "-" + editionCode;
        return str.toLowerCase();
    }

    public String toStringFormatted() {
        String str = sectionCode + classCode + subclassCode + " " + groupCode + "/" + subgroupCode + " (" + editionCode + ")";
        return str.toUpperCase();
    }
}