package bg.duosoft.bpo.portal.service.impl;

import bg.duosoft.bpo.portal.dto.Breadcrumb;
import bg.duosoft.bpo.portal.repository.PanelRepository;
import bg.duosoft.bpo.portal.repository.ServiceDefinitionRepository;
import bg.duosoft.bpo.portal.service.BreadcrumbService;
import jakarta.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class BreadcrumbServiceImpl implements BreadcrumbService {

    private final PanelRepository panelRepository;
    private final ServiceDefinitionRepository serviceDefinitionRepository;

    @Override
    public Map<String, Breadcrumb> getDynamicBreadcrumbData() {
        Map<String, Breadcrumb> dynamicBreadcrumbData = new HashMap<>();

        appendBreadcrumbData(dynamicBreadcrumbData, panelRepository.getPanelBreadcrumbData());
        appendBreadcrumbData(dynamicBreadcrumbData,
                serviceDefinitionRepository.getServiceDefinitionBreadcrumbData());

        return dynamicBreadcrumbData;
    }

    private void appendBreadcrumbData(Map<String, Breadcrumb> dynamicBreadcrumbData,
            List<Object[]> fetchedBreadcrumbData) {
        dynamicBreadcrumbData.putAll(transformBreadcrumbData(fetchedBreadcrumbData));
    }

    public Map<String, Breadcrumb> transformBreadcrumbData(List<Object[]> breadcrumbData) {
        if (CollectionUtils.isEmpty(breadcrumbData)) {
            return Map.of();
        }

        return breadcrumbData.stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],
                        result -> (Breadcrumb) result[1]
                ));
    }

}
