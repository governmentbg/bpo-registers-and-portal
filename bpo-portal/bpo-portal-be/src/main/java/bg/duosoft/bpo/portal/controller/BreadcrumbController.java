package bg.duosoft.bpo.portal.controller;

import bg.duosoft.bpo.portal.dto.Breadcrumb;
import bg.duosoft.bpo.portal.service.BreadcrumbService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/breadcrumbs")
public class BreadcrumbController {
    private final BreadcrumbService breadcrumbService;

    @GetMapping
    public Map<String, Breadcrumb> getDynamicBreadcrumbData() {
        return breadcrumbService.getDynamicBreadcrumbData();
    }
}
