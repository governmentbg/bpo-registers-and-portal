package bg.duosoft.bpo.registers.dto.filter;

import bg.duosoft.bpo.common.dto.filter.SearchOperatorType;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PatentIpcClassDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpcFilterDetailsDTO {

    private List<PatentIpcClassDTO> ipcClasses;
    private SearchOperatorType ipcClassOperatorType;
}
