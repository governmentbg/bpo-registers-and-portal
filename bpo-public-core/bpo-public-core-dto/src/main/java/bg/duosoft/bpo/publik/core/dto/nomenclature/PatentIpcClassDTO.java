package bg.duosoft.bpo.publik.core.dto.nomenclature;

import bg.duosoft.bpo.common.dto.BaseDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PatentIpcClassDTO implements BaseDTO<PatentIpcClassId> {
    private PatentIpcClassId id;
    private String name;
    private String nameEn;
}