package bg.duosoft.bpo.publik.core.service.nomenclature;

import bg.duosoft.bpo.publik.core.dto.nomenclature.BackofficeStatusDTO;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 12.02.2024
 * Time: 17:20
 */
public interface StatusMapService {

    List<String> getStatusCodesByBpoOnlineName(String bpoOnlineName);
    String getStatusNameByBackofficeCode(String backofficeStatusId);
    Map<String, String> getBpoOnlineStatusNamesMap();
    Map<String, List<BackofficeStatusDTO>> getBOStatusesByObjectTypes(List<String> objectTypes);
    List<BackofficeStatusDTO> getBOUserdocStatuses();
}
