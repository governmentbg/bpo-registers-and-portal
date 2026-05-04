package bg.duosoft.bpo.registers.service.recordal;

import bg.duosoft.bpo.registers.dto.recordal.RecordalDTO;
import java.util.List;

public interface RecordalService {
    List<RecordalDTO> getRecordalsWithExcludedGroupsByObjectId(List<String> groupIds, String objectId);
    List<RecordalDTO> getRecordalsWithIncludedGroupsByObjectId(List<String> groupIds, String objectId);
}
