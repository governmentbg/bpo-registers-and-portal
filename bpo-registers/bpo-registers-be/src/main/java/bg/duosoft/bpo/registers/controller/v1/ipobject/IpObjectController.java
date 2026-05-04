package bg.duosoft.bpo.registers.controller.v1.ipobject;


import bg.duosoft.bpo.common.dto.AutocompleteResultDTO;
import bg.duosoft.bpo.common.dto.filter.AutocompleteFilterDTO;
import bg.duosoft.bpo.common.util.exception.ResourceNotFoundException;
import bg.duosoft.bpo.publik.core.dto.nomenclature.BpoOnlineStatusDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.PublicationSectionDTO;
import bg.duosoft.bpo.registers.dto.autocomplete.RepresentativeAutocompleteResultDTO;
import bg.duosoft.bpo.registers.dto.filter.*;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectShortDTO;
import bg.duosoft.bpo.registers.mapper.ipobject.search.CombinedFilterDetailsMapper;
import bg.duosoft.bpo.registers.service.ipobject.IpObjectAutocompleteService;
import bg.duosoft.bpo.registers.service.ipobject.IpObjectPublicationService;
import bg.duosoft.bpo.registers.service.ipobject.IpObjectSearchService;
import bg.duosoft.bpo.registers.service.ipobject.IpObjectService;
import bg.duosoft.bpo.registers.service.ipobject.impl.search.util.IpObjectSearchSortUtil;
import bg.duosoft.bpo.registers.util.swagger.Tags;
import bg.duosoft.bpo.registers.util.swagger.annotation.IpObjectGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@IpObjectGroup
@RestController
@Api(tags = Tags.IP_OBJECTS)
@RequestMapping(value = {"/api/v1/ip-objects"})
@RequiredArgsConstructor
public class IpObjectController {
    private final IpObjectSearchService ipObjectSearchService;
    private final IpObjectAutocompleteService ipObjectAutocompleteService;
    private final IpObjectService ipObjectService;
    private final CombinedFilterDetailsMapper combinedFilterDetailsMapper;
    private final IpObjectPublicationService ipObjectPublicationService;

    @ApiOperation(value = "Filter ip objects")
    @PostMapping
    public Page<IpObjectSearchResult> search(@RequestBody CommonIpObjectFilterDetailsDTO filter) {
        IpObjectFilter ipObjectFilter = combinedFilterDetailsMapper.toIpObjectFilter(filter);
        Page<IpObjectSearchResult> search = ipObjectSearchService.search(ipObjectFilter);
        return search;
    }

    @GetMapping("/autocomplete/registrationNumber")
    public List<AutocompleteResultDTO> autocompleteRegistrationNumbers(AutocompleteFilterDTO autocompleteViewFilterDTO, @RequestParam List<String> objectTypes) {
        ObjectAutocompleteFilter filter = new ObjectAutocompleteFilter(objectTypes, null, autocompleteViewFilterDTO.getName());
        prepareFilter(filter, autocompleteViewFilterDTO);
        Page<IpObjectSearchResult> res = ipObjectAutocompleteService.search(filter);
        return res == null ? null : res.stream().map(r -> new AutocompleteResultDTO("" + r.getRegistrationNbr(), r.getTitle())).collect(Collectors.toList());
    }

    @GetMapping("/autocomplete/filingNumber")
    public List<AutocompleteResultDTO> autocompleteFilingNumbers(AutocompleteFilterDTO autocompleteViewFilterDTO, @RequestParam List<String> objectTypes) {
        ObjectAutocompleteFilter filter = new ObjectAutocompleteFilter(objectTypes, autocompleteViewFilterDTO.getName(), null);
        prepareFilter(filter, autocompleteViewFilterDTO);
        Page<IpObjectSearchResult> res = ipObjectAutocompleteService.search(filter);
        return res == null ? null : res.stream().map(r -> new AutocompleteResultDTO("" + r.getFilingNumber(), r.getTitle())).collect(Collectors.toList());
    }

    @GetMapping("/autocomplete/representative")
    public List<RepresentativeAutocompleteResultDTO> autocompleteRepresentatives(RepresentativeAutocompleteFilterDetailsDTO filter) {
        if (!StringUtils.hasText(filter.getName())) {
            return new ArrayList<>();
        }
        List<RepresentativeAutocompleteResultDTO> res = ipObjectService.autocompleteObjectsRepresentatives(filter);
        return res;
    }

    @GetMapping("/publication/numbers")
    public List<String> getPublicationNumbers(@RequestParam Integer year, @RequestParam List<String> objectTypes) {
        return ipObjectPublicationService.getObjectsPublicationNumbers(year, objectTypes);
    }

    @GetMapping("/publication/sections")
    public List<PublicationSectionDTO> getPublicationSections(@RequestParam(required = false) Integer year, @RequestParam String number, @RequestParam List<String> objectTypes) {
        return ipObjectPublicationService.getObjectsPublicationSections(year, number, objectTypes);
    }

    @GetMapping("/statuses")
    public List<BpoOnlineStatusDTO> getStatuses(@RequestParam List<String> objectTypes) {
        return ipObjectService.getObjectsBpoOnlineStatuses(objectTypes);
    }

    @GetMapping("/by-alternate-key")
    public IpObjectShortDTO getObjectByAlternateKey(@RequestParam String alternateKey) {
        IpObjectShortDTO ipObject = this.ipObjectService.getObjectByAlternateKey(alternateKey);
        if (Objects.isNull(ipObject)) {
            throw new ResourceNotFoundException();
        }
        
        return ipObject;
    }

    private void prepareFilter(ObjectAutocompleteFilter filter, AutocompleteFilterDTO autocompleteViewFilterDTO) {
        filter.setPage(autocompleteViewFilterDTO.getPage());
        filter.setPageSize(autocompleteViewFilterDTO.getPageSize());
        filter.setOrderBy(IpObjectSearchSortUtil.SORT_COLUMN_FILING_DATE);
        filter.setOrder("ASC");
    }
}
