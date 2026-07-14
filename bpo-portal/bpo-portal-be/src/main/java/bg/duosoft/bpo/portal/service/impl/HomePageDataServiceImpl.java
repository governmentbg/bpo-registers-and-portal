package bg.duosoft.bpo.portal.service.impl;

import bg.duosoft.bpo.portal.dto.HomePageData;
import bg.duosoft.bpo.portal.mapper.HomePageDataMapper;
import bg.duosoft.bpo.portal.repository.PanelRepository;
import bg.duosoft.bpo.portal.repository.PortalTextRepository;
import bg.duosoft.bpo.portal.service.HomePageDataService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class HomePageDataServiceImpl implements HomePageDataService {

    private final static String HOME_PAGE_ID = "homePage";

    private final HomePageDataMapper homePageDataMapper;
    private final PanelRepository panelRepository;
    private final PortalTextRepository portalTextRepository;

    @Override
    public HomePageData getHomePageData() {
        return homePageDataMapper.toDto(
                portalTextRepository
                        .findById(HOME_PAGE_ID)
                        .orElse(null),
                panelRepository
                        .getAllByParentPanelIdNull()
        );
    }
}
