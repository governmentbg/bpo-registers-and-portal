package bg.duosoft.bpo.registers.service.ipobject;

import bg.duosoft.bpo.publik.core.dto.nomenclature.PublicationSectionDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectPublicationDTO;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 29.01.2025
 * Time: 14:41
 */
public interface IpObjectPublicationService {
    List<String> getObjectsPublicationNumbers(Integer year, List<String> objectTypes);

    public List<Integer> getObjectsPublicationYears(List<String> objectTypes);

    List<PublicationSectionDTO> getObjectsPublicationSections(Integer year, String number, List<String> objectTypes);

    List<IpObjectPublicationDTO> getPublications(Integer year, String publicationNumber, List<String> objectTypes, List<String> publicationCodes);
}
