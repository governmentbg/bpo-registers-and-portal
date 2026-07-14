package bg.duosoft.bpo.registers.service.ipobject.impl;

import bg.duosoft.bpo.publik.core.dto.nomenclature.BpoOnlineStatusDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PublicationSectionDTO;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EPublicationSection;
import bg.duosoft.bpo.publik.core.mapper.nomenclature.PublicationSectionMapper;
import bg.duosoft.bpo.registers.dto.autocomplete.RepresentativeAutocompleteResultDTO;
import bg.duosoft.bpo.registers.dto.filter.RepresentativeAutocompleteFilterDetailsDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectShortDTO;
import bg.duosoft.bpo.registers.mapper.ipobject.IpObjectShortMapper;
import bg.duosoft.bpo.registers.mapper.ipobject.RepresentativeAutocompleteMapper;
import bg.duosoft.bpo.registers.nonentity.autocomplete.RepresentativeAutocomplete;
import bg.duosoft.bpo.registers.repository.ipobject.IpObjectPublicationRepository;
import bg.duosoft.bpo.registers.repository.ipobject.IpObjectRepository;
import bg.duosoft.bpo.registers.service.ipobject.IpObjectService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 12.02.2024
 * Time: 13:17
 */
@Service
@RequiredArgsConstructor
public class IpObjectServiceImpl implements IpObjectService {

    private final IpObjectRepository ipObjectRepository;
    private final IpObjectShortMapper ipObjectShortMapper;

    private final RepresentativeAutocompleteMapper representativeAutocompleteMapper;



    @Override
    public List<BpoOnlineStatusDTO> getObjectsBpoOnlineStatuses(List<String> objectTypes) {
        List<String[]> statusMapList = ipObjectRepository.getObjectStatuses(objectTypes);
        if (statusMapList != null) {
            return statusMapList.stream().map(array -> new BpoOnlineStatusDTO(array[0], array[1])).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<RepresentativeAutocompleteResultDTO> autocompleteObjectsRepresentatives(RepresentativeAutocompleteFilterDetailsDTO filter) {
        List<RepresentativeAutocomplete> list = ipObjectRepository.autocompleteRepresentatives("%" + filter.getName().toLowerCase() + "%", filter.getObjectTypes(), PageRequest.of(filter.getPage(), filter.getPageSize()));
        if (list != null) {
            return representativeAutocompleteMapper.toDtoList(list);
        }
        return new ArrayList<>();
    }

    @Override
    public IpObjectShortDTO getObjectByAlternateKey(String alternateKey) {
        return this.ipObjectShortMapper
                .toDto(this.ipObjectRepository
                        .getObjectByAlternateKey(alternateKey));
    }

    @Override
    public IpObjectShortDTO getObjectById(String id) {
        return this.ipObjectShortMapper
                .toDto(this.ipObjectRepository
                        .getObjectById(id));
    }
}
