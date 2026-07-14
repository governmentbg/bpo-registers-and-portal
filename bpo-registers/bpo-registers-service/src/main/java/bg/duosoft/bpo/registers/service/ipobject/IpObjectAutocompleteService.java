package bg.duosoft.bpo.registers.service.ipobject;

import bg.duosoft.bpo.registers.dto.filter.IpObjectSearchResult;
import bg.duosoft.bpo.registers.dto.filter.ObjectAutocompleteFilter;
import org.springframework.data.domain.Page;

public interface IpObjectAutocompleteService {
     Page<IpObjectSearchResult> search(ObjectAutocompleteFilter filter);
}
