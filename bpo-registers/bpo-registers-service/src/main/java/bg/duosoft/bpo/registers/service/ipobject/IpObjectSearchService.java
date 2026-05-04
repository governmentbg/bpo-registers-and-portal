package bg.duosoft.bpo.registers.service.ipobject;

import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.registers.dto.filter.IpObjectSearchResult;
import org.springframework.data.domain.Page;

public interface IpObjectSearchService {
     Page<IpObjectSearchResult> search(IpObjectFilter filter);
}
