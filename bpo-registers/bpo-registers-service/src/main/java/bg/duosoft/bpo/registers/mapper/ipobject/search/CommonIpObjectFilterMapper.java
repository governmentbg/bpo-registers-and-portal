package bg.duosoft.bpo.registers.mapper.ipobject.search;

import bg.duosoft.bpo.common.dto.AutocompleteResultDTO;
import bg.duosoft.bpo.registers.dto.autocomplete.RepresentativeAutocompleteResultDTO;
import bg.duosoft.bpo.registers.dto.filter.CommonIpObjectFilterDetailsDTO;
import bg.duosoft.bpo.registers.dto.filter.IpObjectFilter;
import bg.duosoft.bpo.publik.core.dto.nomenclature.BpoOnlineStatusDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.RepresentativeTypeDTO;
import bg.duosoft.bpo.publik.core.service.nomenclature.StatusMapService;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.02.2024
 * Time: 13:47
 */
public abstract class CommonIpObjectFilterMapper {

    @Autowired
    private StatusMapService statusMapService;

    @Mapping(target = "filingNumberFrom", source = "filingNumber.from")
    @Mapping(target = "filingNumberTo", source = "filingNumber.to")
    @Mapping(target = "filingDateFrom", source = "filingDate.from")
    @Mapping(target = "filingDateTo", source = "filingDate.to")
    @Mapping(target = "registrationNumberFrom", source = "registrationNumber.from")
    @Mapping(target = "registrationNumberTo", source = "registrationNumber.to")
    @Mapping(target = "expirationDateFrom", source = "expirationDate.from")
    @Mapping(target = "expirationDateTo", source = "expirationDate.to")
    @Mapping(target = "statusCodes", source = "status", qualifiedByName = "statusMapping")
    @Mapping(target = "representativeTypeCodes", source = "representativeTypes", qualifiedByName = "representativeTypeMapping")
    @Mapping(target = "representativeCodes", source = "representatives", qualifiedByName = "representativeMapping")
    @Mapping(target = "publicationYear", source = "publications.publicationYear")
    @Mapping(target = "publicationNumber", source = "publications.publicationNumber")
    @Mapping(target = "publicationSection", source = "publications.publicationSection")
    @Mapping(target = "publicationDateFrom", source = "publications.publicationDate.from")
    @Mapping(target = "publicationDateTo", source = "publications.publicationDate.to")
    @Mapping(target = "name", source = "objectName.text")
    @Mapping(target = "nameSearchType", source = "objectName.searchType")
    @Mapping(target = "applicantOwnerCountry", source = "applicantOwnerCountry.id")
    @Mapping(target = "priorityDateFrom", source = "priority.priorityDate.from")
    @Mapping(target = "priorityDateTo", source = "priority.priorityDate.to")
    @Mapping(target = "priorityCountry", source = "priority.priorityCountry.id")
    @Mapping(target = "priorityNumber", source = "priority.priorityNumber")
    protected abstract IpObjectFilter toIpObjectFilterConfig(CommonIpObjectFilterDetailsDTO dto);

    @Named("statusMapping")
    public List<String> statusMapping(BpoOnlineStatusDTO status) {
        if (Objects.nonNull(status) && StringUtils.hasText(status.getBpoOnlineStatus())) {
            List<String> statusIds = statusMapService.getStatusCodesByBpoOnlineName(status.getBpoOnlineStatus());
            return statusIds;
        } else {
            return null;
        }
    }

    @Named("representativeTypeMapping")
    public List<String> representativeTypeMapping(List<RepresentativeTypeDTO> representativeTypes) {
        if (CollectionUtils.isEmpty(representativeTypes)){
            return null;
        }
        List<String> representativeTypeCodes = representativeTypes.stream().map(RepresentativeTypeDTO::getId).collect(Collectors.toList());
        return representativeTypeCodes;
    }

    @Named("representativeMapping")
    public List<String> representativeMapping(List<RepresentativeAutocompleteResultDTO> representatives) {
        if (CollectionUtils.isEmpty(representatives)){
            return null;
        }
        List<String> representativesCodes = representatives.stream().map(rep -> rep.getAgentCode() != null? rep.getAgentCode(): rep.getId().toString()).collect(Collectors.toList());
        return representativesCodes;
    }

    public String autocompleteToIdMapping(AutocompleteResultDTO auto) {
        if (auto == null) {
            return null;
        }
        return auto.getId();
    }


}
