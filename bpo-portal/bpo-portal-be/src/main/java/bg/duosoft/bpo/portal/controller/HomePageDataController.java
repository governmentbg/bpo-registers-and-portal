package bg.duosoft.bpo.portal.controller;

import bg.duosoft.bpo.portal.dto.HomePageData;
import bg.duosoft.bpo.portal.service.HomePageDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/home-page-data")
public class HomePageDataController {

    private final HomePageDataService homePageDataService;

    @GetMapping
    public HomePageData getHomePageData() {
        return homePageDataService.getHomePageData();
    }

}
