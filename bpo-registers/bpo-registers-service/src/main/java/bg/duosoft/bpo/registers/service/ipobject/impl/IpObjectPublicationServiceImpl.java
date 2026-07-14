package bg.duosoft.bpo.registers.service.ipobject.impl;

import bg.duosoft.bpo.publik.core.dto.nomenclature.PublicationSectionDTO;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPublicationSection;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.PublicationSectionMapper;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectPublicationDTO;
import bg.duosoft.bpo.registers.entity.ipobject.EIpObjectPublication;
import bg.duosoft.bpo.registers.mapper.ipobject.IpObjectPublicationMapper;
import bg.duosoft.bpo.registers.repository.ipobject.IpObjectPublicationRepository;
import bg.duosoft.bpo.registers.service.ipobject.IpObjectPublicationService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 29.01.2025
 * Time: 14:40
 */
@RequiredArgsConstructor
@Service
public class IpObjectPublicationServiceImpl implements IpObjectPublicationService {
    private final IpObjectPublicationRepository ipObjectPublicationRepository;
    private final PublicationSectionMapper publicationSectionMapper;
    private final IpObjectPublicationMapper ipObjectPublicationMapper;

    public List<IpObjectPublicationDTO> getPublications(Integer year, String publicationNumber, List<String> objectTypes, List<String> publicationCodes) {
        List<EIpObjectPublication> res = ipObjectPublicationRepository.getObjectPublications(year, publicationNumber, objectTypes, publicationCodes);
        return ipObjectPublicationMapper.toDtoList(res);
    }

    public List<Integer> getObjectsPublicationYears(List<String> objectTypes) {
        return ipObjectPublicationRepository.getObjectPublicationYears(objectTypes);
    }
    @Override
    public List<String> getObjectsPublicationNumbers(Integer year, List<String> objectTypes) {
        return ipObjectPublicationRepository.getObjectPublicationNumbers(year, objectTypes);
    }

    @Override
    public List<PublicationSectionDTO> getObjectsPublicationSections(Integer year, String number, List<String> objectTypes) {
        List<EPublicationSection> publicationSectionList;
        if ((Objects.isNull(year) || year == 0) && StringUtils.isEmpty(number)) {
            publicationSectionList = ipObjectPublicationRepository.getObjectPublicationSections(objectTypes);
        } else if (StringUtils.isEmpty(number)) {
            publicationSectionList = ipObjectPublicationRepository.getObjectPublicationSections(year, objectTypes);
        } else {
            publicationSectionList = ipObjectPublicationRepository.getObjectPublicationSections(year, number, objectTypes);
        }
        return publicationSectionMapper.toDtoList(publicationSectionList);
    }
}
