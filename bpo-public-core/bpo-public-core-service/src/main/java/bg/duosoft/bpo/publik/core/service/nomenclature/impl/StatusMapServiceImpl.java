package bg.duosoft.bpo.publik.core.service.nomenclature.impl;

import bg.duosoft.bpo.publik.core.dto.nomenclature.BackofficeStatusDTO;
import bg.duosoft.bpo.publik.core.repository.nomenclature.StatusMapRepository;
import bg.duosoft.bpo.publik.core.service.nomenclature.StatusMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 12.02.2024
 * Time: 17:24
 */
@Service
@RequiredArgsConstructor
public class StatusMapServiceImpl implements StatusMapService {

    private final StatusMapRepository statusMapRepository;

    @Override
    public List<String> getStatusCodesByBpoOnlineName(String bpoOnlineName) {
        return statusMapRepository.getStatusCodesByBpoOnlineName(bpoOnlineName);
    }

    @Cacheable(cacheNames = "StatusMapServiceImpl", key = "'status-name-by-id' + #backofficeStatusCode")
    public String getStatusNameByBackofficeCode(String backofficeStatusCode) {
        return statusMapRepository.getBpoOnlineStatusNameByBackofficeCode(backofficeStatusCode);
    }

    @Cacheable(cacheNames = "StatusMapServiceImpl", key = "'all-status-names'")
    public Map<String, String> getBpoOnlineStatusNamesMap() {
        return statusMapRepository.findAll().stream().collect(Collectors.toMap(r -> r.getId(), r -> r.getBpoOnlineStatus()));
    }

    @Override
    public Map<String, List<BackofficeStatusDTO>> getBOStatusesByObjectTypes(List<String> objectTypes) {
        List<Object[]> statusesByObjectTypes = statusMapRepository.getBOStatusesByObjectTypes(objectTypes);

        return statusesByObjectTypes.stream()
                .collect(Collectors.groupingBy(
                        row -> (String) row[0],
                        Collectors.mapping(
                                row -> new BackofficeStatusDTO(
                                        (String) row[1],
                                        (String) row[2]
                                ),
                                Collectors.toList()
                        )
                ));
    }

    @Override
    @Cacheable(cacheNames = "StatusMapServiceImpl", key = "'userdoc-bo-statuses'")
    public List<BackofficeStatusDTO> getBOUserdocStatuses() {
        List<Object[]> boUserdocProcesses = statusMapRepository.getBOUserdocStatuses();
        return boUserdocProcesses.stream()
                .map(row -> new BackofficeStatusDTO(
                        (String) row[0],
                        (String) row[1]
                ))
                .toList();
    }
}
