package bg.duosoft.bpo.registers.service.ipobject;

import bg.duosoft.bpo.registers.dto.autocomplete.RepresentativeAutocompleteResultDTO;
import bg.duosoft.bpo.registers.dto.filter.RepresentativeAutocompleteFilterDetailsDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.BpoOnlineStatusDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PublicationSectionDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectShortDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 12.02.2024
 * Time: 12:04
 */
public interface IpObjectService {

    List<BpoOnlineStatusDTO> getObjectsBpoOnlineStatuses(List<String> objectTypes);

    List<RepresentativeAutocompleteResultDTO> autocompleteObjectsRepresentatives(RepresentativeAutocompleteFilterDetailsDTO filter);

    IpObjectShortDTO getObjectByAlternateKey(String alternateKey);

    IpObjectShortDTO getObjectById(String id);
}
