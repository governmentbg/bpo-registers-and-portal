package bg.duosoft.bpo.registers.service.seniority;

import bg.duosoft.bpo.registers.dto.seniority.SeniorityBgDTO;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 09.02.2026
 * Time: 17:43
 */
public interface SeniorityBgService {
    public boolean hasSeniorityBg(String objectId);
    public List<SeniorityBgDTO> getSeniorityBgs(String objectId);
}
