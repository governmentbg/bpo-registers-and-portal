package bg.duosoft.bpo.registers.service.ipobject;

import bg.duosoft.bpo.registers.dto.filter.PersonFilter;
import bg.duosoft.bpo.registers.dto.ipobject.IpPersonDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 06.11.2024
 * Time: 17:00
 */
public interface IpPersonService {
    public Page<IpPersonDTO> findPersons(PersonFilter filter);
    public IpPersonDTO getPerson(int id);
    public List<IpPersonDTO> getMatchingPersons(IpPersonDTO person, int maxCount, boolean strongMatch);
}
