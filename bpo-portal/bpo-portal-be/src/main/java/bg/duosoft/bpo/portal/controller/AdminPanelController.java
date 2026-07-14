package bg.duosoft.bpo.portal.controller;

import bg.duosoft.bpo.common.security.util.SecurityRole;
import bg.duosoft.bpo.common.web.controller.BaseAccessController;
import bg.duosoft.bpo.portal.dto.AdminPanel;
import bg.duosoft.bpo.portal.service.AdminPanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin-panel-data")
public class AdminPanelController extends BaseAccessController {

    private final AdminPanelService adminPanelService;

    @GetMapping
    public List<AdminPanel> getAdminPanelData() {
        return this.adminPanelService.getAdminPanelData();
    }

    @Override
    public String getEditRole() {
        return null;
    }

    @Override
    public String getAccessRole() {
        return SecurityRole.ADMIN_CONSOLE_ACCESS;
    }
}
