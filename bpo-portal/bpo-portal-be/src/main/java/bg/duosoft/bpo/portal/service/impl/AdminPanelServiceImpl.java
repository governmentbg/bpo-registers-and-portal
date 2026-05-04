package bg.duosoft.bpo.portal.service.impl;

import bg.duosoft.bpo.portal.dto.AdminPanel;
import bg.duosoft.bpo.portal.mapper.AdminPanelMapper;
import bg.duosoft.bpo.portal.repository.AdminPanelRepository;
import bg.duosoft.bpo.portal.service.AdminPanelService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminPanelServiceImpl implements AdminPanelService {

    private final AdminPanelRepository adminPanelRepository;
    private final AdminPanelMapper adminPanelMapper;

    public List<AdminPanel> getAdminPanelData() {
        return this.adminPanelMapper
                .toDtoList(this.adminPanelRepository
                        .getAdminPanelData());
    }
}
