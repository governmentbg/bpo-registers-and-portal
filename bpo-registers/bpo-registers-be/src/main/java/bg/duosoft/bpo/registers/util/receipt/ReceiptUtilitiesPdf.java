package bg.duosoft.bpo.registers.util.receipt;

import bg.duosoft.bpo.common.security.util.SecurityUtils;
import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.*;
import bg.duosoft.bpo.publik.core.enums.RecordalGroupType;
import bg.duosoft.bpo.publik.core.enums.RepresentativeType;
import bg.duosoft.bpo.publik.core.service.nomenclature.*;
import bg.duosoft.bpo.receiptservice.util.ReceiptUtils;
import bg.duosoft.bpo.registers.dto.filter.*;
import bg.duosoft.bpo.registers.dto.ipobject.IpAgentHistoryDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpPersonDTO;
import bg.duosoft.bpo.registers.dto.ipobject.VwObjectRelationshipDTO;
import bg.duosoft.bpo.registers.dto.legaldecision.LegalDecisionDocumentDTO;
import bg.duosoft.bpo.registers.dto.recordal.RecordalDTO;
import bg.duosoft.bpo.registers.mapper.ipobject.search.*;
import bg.duosoft.bpo.registers.service.file.ThumbnailService;
import bg.duosoft.bpo.registers.service.ipobject.*;
import bg.duosoft.bpo.registers.service.legaldecision.LegalDecisionDocumentService;
import bg.duosoft.bpo.registers.service.recordal.RecordalService;
import bg.duosoft.bpo.registers.util.RegisterTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static bg.duosoft.bpo.registers.util.receipt.CommonReceiptUtils.isPrimaryLang;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceiptUtilitiesPdf {

    private static final Integer RESULT_COUNT_AUTHENTICATED = 2_000;
    private static final Integer RESULT_COUNT_UNAUTHENTICATED = 2_000;
    private static final String REGISTER_TYPE_PARAM_NAME = "registerType";
    private static final String LANGUAGE_PARAM_NAME = "lang";
    private final IpMarkService ipMarkService;
    private final IpPatentService ipPatentService;
    private final IpDesignService ipDesignService;
    private final IpPlantService ipPlantService;
    private final VwObjectRelationshipsService relationshipsService;
    private final RecordalService recordalService;
    private final IpAgentService ipAgentService;
    private final IpAgentHistoryService ipAgentHistoryService;
    private final IpObjectSearchService ipObjectSearchService;
    private final MarkFilterDetailsMapper markFilterDetailsMapper;
    private final GeoIndicationFilterDetailsMapper geoIndicationFilterDetailsMapper;
    private final PatentFilterDetailsMapper patentFilterDetailsMapper;
    private final EuPatentFilterDetailsMapper euPatentFilterDetailsMapper;
    private final UtilityModelFilterDetailsMapper utilityModelFilterDetailsMapper;
    private final PlantBreedFilterDetailsMapper plantBreedFilterDetailsMapper;
    private final SpcFilterDetailsMapper spcFilterDetailsMapper;
    private final DesignFilterDetailsMapper designFilterDetailsMapper;
    private final CombinedFilterDetailsMapper combinedFilterDetailsMapper;
    private final ThumbnailService thumbnailService;
    private final MarkKindService markKindService;
    private final ObjectSubtypeService objectSubtypeService;
    private final PublicationSectionService publicationSectionService;
    private final CountryService countryService;
    private final AgentSpecialityService agentSpecialityService;
    private final AgentStatusService agentStatusService;
    private final ObjectTypeService objectTypeService;
    private final LegalDecisionDocumentService legalDecisionDocumentService;

    public ReceiptParamsPdf populateViewReceiptParams(String registerType, String id, String lang) {
        CommonReceiptUtils.validateLanguage(lang);

        String objectParamName = "object";
        String agentParamName = "agent";

        String templateName;
        String fileName;
        Map<String, Object> receiptData = new HashMap<>();

        registerType = registerType.toUpperCase();
        String formattedId = id.replaceAll("_", "/");
        IpPersonDTO agent = null;

        switch (registerType) {
            case RegisterTypes.MARK -> {
                templateName = TemplateNames.VIEW_MARK.getTemplatePath();
                fileName = ViewReceiptNames.MARK.generateFileNamePdf(id);
                receiptData.put(objectParamName, ipMarkService.getById(formattedId, true));
            }
            case RegisterTypes.GEO_INDICATION -> {
                templateName = TemplateNames.VIEW_GEO_INDICATION.getTemplatePath();
                fileName = ViewReceiptNames.GEO_INDICATION.generateFileNamePdf(id);
                receiptData.put(objectParamName, ipMarkService.getById(formattedId, true));
            }
            case RegisterTypes.PATENT -> {
                templateName = TemplateNames.VIEW_PATENT.getTemplatePath();
                fileName = ViewReceiptNames.PATENT.generateFileNamePdf(id);
                receiptData.put(objectParamName, ipPatentService.getById(formattedId, true));
            }
            case RegisterTypes.UTILITY_MODEL -> {
                templateName = TemplateNames.VIEW_UTILITY_MODEL.getTemplatePath();
                fileName = ViewReceiptNames.UTILITY_MODEL.generateFileNamePdf(id);
                receiptData.put(objectParamName, ipPatentService.getById(formattedId, true));
            }
            case RegisterTypes.EU_PATENT -> {
                templateName = TemplateNames.VIEW_EU_PATENT.getTemplatePath();
                fileName = ViewReceiptNames.EU_PATENT.generateFileNamePdf(id);
                receiptData.put(objectParamName, ipPatentService.getById(formattedId, true));
            }
            case RegisterTypes.PLANT_BREED -> {
                templateName = TemplateNames.VIEW_PLANT_BREED.getTemplatePath();
                fileName = ViewReceiptNames.PLANT_BREED.generateFileNamePdf(id);
                receiptData.put(objectParamName, ipPlantService.getById(formattedId, true));
            }
            case RegisterTypes.SPC -> {
                templateName = TemplateNames.VIEW_SPC.getTemplatePath();
                fileName = ViewReceiptNames.SPC.generateFileNamePdf(id);
                receiptData.put(objectParamName, ipPatentService.getById(formattedId, true));
            }
            case RegisterTypes.DESIGN -> {
                templateName = TemplateNames.VIEW_DESIGN.getTemplatePath();
                fileName = ViewReceiptNames.DESIGN.generateFileNamePdf(id.replace("Д", "D"));
                receiptData.put(objectParamName, ipDesignService.getById(formattedId, true));
            }
            case RegisterTypes.AGENT -> {
                templateName = TemplateNames.VIEW_AGENT.getTemplatePath();
                fileName = ViewReceiptNames.AGENT.generateFileNamePdf(id);
                agent = ipAgentService.getByAgentCodeAndTypeIn(id, List.of("AG"));
                receiptData.put(agentParamName, agent);
            }
            case RegisterTypes.PARTNERSHIP -> {
                templateName = TemplateNames.VIEW_PARTNERSHIP.getTemplatePath();
                fileName = ViewReceiptNames.PARTNERSHIP.generateFileNamePdf(id);
                agent = ipAgentService.getByAgentCodeAndTypeIn(id, List.of("PP", "PC"));
                receiptData.put(agentParamName, agent);
            }
            default -> throw new IllegalArgumentException("Unrecognized register type!");
        }

        receiptData.put(LANGUAGE_PARAM_NAME, lang);
        receiptData.put(REGISTER_TYPE_PARAM_NAME, registerType);

        if (registerType.equalsIgnoreCase(RegisterTypes.AGENT) || registerType.equalsIgnoreCase(RegisterTypes.PARTNERSHIP)) {
            if (Objects.nonNull(agent)) {
                List<IpPersonDTO> agentRelations = ipAgentService.getAgentRelationsByAgentId(agent.getId(), agent.getAgent().getRepresentativeType().getId()).stream().sorted(Comparator.comparing(o -> o.getAgent().getAgentCode())).toList();
                receiptData.put("agentRelations", agentRelations);
                List<IpAgentHistoryDTO> agentHistory = ipAgentHistoryService.selectAllByPersonId(agent.getId()).stream().sorted(Comparator.comparing(IpAgentHistoryDTO::getHistoryTimestamp).reversed()).toList();
                receiptData.put("agentHistory", agentHistory);
            }
        } else {
            List<VwObjectRelationshipDTO> objectRelationshipsByObjectId = relationshipsService.getObjectRelationshipsByObjectIdAndRelationshipType(formattedId, null);
            receiptData.put("objectRelationships", objectRelationshipsByObjectId);
        }

        if (registerType.equalsIgnoreCase(RegisterTypes.MARK) || registerType.equalsIgnoreCase(RegisterTypes.PATENT) || registerType.equalsIgnoreCase(RegisterTypes.DESIGN) ||
                registerType.equalsIgnoreCase(RegisterTypes.EU_PATENT) || registerType.equalsIgnoreCase(RegisterTypes.SPC) || registerType.equalsIgnoreCase(RegisterTypes.PLANT_BREED)
                || registerType.equalsIgnoreCase(RegisterTypes.UTILITY_MODEL) || registerType.equalsIgnoreCase(RegisterTypes.GEO_INDICATION)) {
            List<RecordalDTO> cancellations = recordalService.getRecordalsWithIncludedGroupsByObjectId(List.of(RecordalGroupType.CANCELLATION.groupTypeId()), formattedId);
            receiptData.put("cancellations", cancellations);

            List<RecordalDTO> oppositions = recordalService.getRecordalsWithIncludedGroupsByObjectId(List.of(RecordalGroupType.OPPOSITION.groupTypeId()), formattedId);
            receiptData.put("oppositions", oppositions);

            List<RecordalDTO> recordals = recordalService.getRecordalsWithExcludedGroupsByObjectId(List.of(RecordalGroupType.OPPOSITION.groupTypeId(),
                    RecordalGroupType.CANCELLATION.groupTypeId()), formattedId);
            receiptData.put("recordals", recordals);
        }

        receiptData.put("decisions",legalDecisionDocumentService.getAllByObjectId(formattedId));

        return new ReceiptParamsPdf(receiptData, templateName, fileName);
    }

    public ReceiptParamsPdf populateSearchReceiptParams(CommonIpObjectFilterDetailsDTO filter, String lang) {
        CommonReceiptUtils.validateLanguage(lang);
        String templateName;
        String fileName;
        Map<String, Object> receiptData = new HashMap<>();
        IpObjectFilter searchRequiredFilter;

        fillApplicantCountry(receiptData, filter.getApplicantOwnerCountry());
        fillPriorityCountry(receiptData, filter.getPriority());

        if (filter instanceof MarkFilterDetailsDTO markFilter) {
            templateName = TemplateNames.SEARCH_MARK.getTemplatePath();
            fileName = SearchReceiptNames.MARK.generateFileNamePdf();
            searchRequiredFilter = markFilterDetailsMapper.toIpObjectFilter(markFilter);
            receiptData.put(REGISTER_TYPE_PARAM_NAME, RegisterTypes.MARK);
            MarkKindDTO markKind = markFilter.getMarkKind();
            if (Objects.nonNull(markKind) && Objects.nonNull(markKind.getId()) && (!StringUtils.hasText(markKind.getDescription()) || !StringUtils.hasText(markKind.getDescriptionEn()))) {
                MarkKindDTO mk = markKindService.getById(markKind.getId());
                markFilter.setMarkKind(mk);
            }
            fillObjectSubtypes(receiptData, markFilter.getObjectSubtype());
        } else if (filter instanceof GeoIndicationFilterDetailsDTO geoFilter) {
            templateName = TemplateNames.SEARCH_GEO_INDICATION.getTemplatePath();
            fileName = SearchReceiptNames.GEO_INDICATION.generateFileNamePdf();
            searchRequiredFilter = geoIndicationFilterDetailsMapper.toIpObjectFilter(geoFilter);
            receiptData.put(REGISTER_TYPE_PARAM_NAME, RegisterTypes.GEO_INDICATION);
            fillObjectSubtypes(receiptData, geoFilter.getObjectSubtype());
        } else if (filter instanceof PatentFilterDetailsDTO patentFilter) {
            templateName = TemplateNames.SEARCH_PATENT.getTemplatePath();
            fileName = SearchReceiptNames.PATENT.generateFileNamePdf();
            searchRequiredFilter = patentFilterDetailsMapper.toIpObjectFilter(patentFilter);
            receiptData.put(REGISTER_TYPE_PARAM_NAME, RegisterTypes.PATENT);
        } else if (filter instanceof EuPatentFilterDetailsDTO euPatentFilter) {
            templateName = TemplateNames.SEARCH_EU_PATENT.getTemplatePath();
            fileName = SearchReceiptNames.EU_PATENT.generateFileNamePdf();
            searchRequiredFilter = euPatentFilterDetailsMapper.toIpObjectFilter(euPatentFilter);
            receiptData.put(REGISTER_TYPE_PARAM_NAME, RegisterTypes.EU_PATENT);
        } else if (filter instanceof UtilityModelFilterDetailsDTO umFilter) {
            templateName = TemplateNames.SEARCH_UTILITY_MODEL.getTemplatePath();
            fileName = SearchReceiptNames.UTILITY_MODEL.generateFileNamePdf();
            searchRequiredFilter = utilityModelFilterDetailsMapper.toIpObjectFilter(umFilter);
            receiptData.put(REGISTER_TYPE_PARAM_NAME, RegisterTypes.UTILITY_MODEL);
        } else if (filter instanceof PlantBreedFilterDetailsDTO plantBreedFilter) {
            templateName = TemplateNames.SEARCH_PLANT_BREED.getTemplatePath();
            fileName = SearchReceiptNames.PLANT_BREED.generateFileNamePdf();
            searchRequiredFilter = plantBreedFilterDetailsMapper.toIpObjectFilter(plantBreedFilter);
            receiptData.put(REGISTER_TYPE_PARAM_NAME, RegisterTypes.PLANT_BREED);
            fillObjectSubtypes(receiptData, plantBreedFilter.getObjectSubtype());
        } else if (filter instanceof SpcFilterDetailsDTO spcFilter) {
            templateName = TemplateNames.SEARCH_SPC.getTemplatePath();
            fileName = SearchReceiptNames.SPC.generateFileNamePdf();
            searchRequiredFilter = spcFilterDetailsMapper.toIpObjectFilter(spcFilter);
            receiptData.put(REGISTER_TYPE_PARAM_NAME, RegisterTypes.SPC);
        } else if (filter instanceof DesignFilterDetailsDTO designFilter) {
            templateName = TemplateNames.SEARCH_DESIGN.getTemplatePath();
            fileName = SearchReceiptNames.DESIGN.generateFileNamePdf();
            searchRequiredFilter = designFilterDetailsMapper.toIpObjectFilter(designFilter);
            receiptData.put(REGISTER_TYPE_PARAM_NAME, RegisterTypes.DESIGN);
        } else {
            templateName = TemplateNames.SEARCH_COMBINED.getTemplatePath();
            fileName = SearchReceiptNames.COMBINED.generateFileNamePdf();
            searchRequiredFilter = combinedFilterDetailsMapper.toIpObjectFilter(filter);
            receiptData.put(REGISTER_TYPE_PARAM_NAME, RegisterTypes.COMBINED);
            fillObjectRange(receiptData, filter.getObjectRange(), lang);
        }

        if (Objects.nonNull(searchRequiredFilter)) {
            searchRequiredFilter.setPage(0);
            Integer maxResultsCount = SecurityUtils.isUserAuthenticated() ? RESULT_COUNT_AUTHENTICATED : RESULT_COUNT_UNAUTHENTICATED;
            searchRequiredFilter.setPageSize(maxResultsCount);
            receiptData.put("maxResultsCount", maxResultsCount);
            Page<IpObjectSearchResult> search = ipObjectSearchService.search(searchRequiredFilter);
            List<IpObjectSearchResult> results = search.stream().toList();
            receiptData.put("results", results);
            receiptData.put("filter", filter);
            List<IpObjectSearchResult> resultsWithImages = results.stream().filter(IpObjectSearchResult::getHasImg).toList();
            if (!CollectionUtils.isEmpty(resultsWithImages)) {
                ArrayList<String> images = new ArrayList<>();
                for (IpObjectSearchResult r : resultsWithImages) {
                    try {
                        FileStoreEntryBaseDTO file = thumbnailService.getThumbnailByObjectIdAndType(r.getObjectId(), r.getObjectType());
                        images.add(ReceiptUtils.generateImageUrlByByteArray(file.getContent()));
                    } catch (Exception e) {
                        log.error("Could not generateImageUrlByByteArray for " + r.getObjectId());
                    }
                }
                receiptData.put("images", images);
            }

            if (Objects.nonNull(filter.getPublications()) && Objects.nonNull(filter.getPublications().getPublicationSection())) {
                PublicationSectionDTO ps = publicationSectionService.getById(filter.getPublications().getPublicationSection());
                receiptData.put("publicationSection", ps);
            }
        }

        receiptData.put(LANGUAGE_PARAM_NAME, lang);
        return new ReceiptParamsPdf(receiptData, templateName, fileName);
    }

    private void fillObjectSubtypes(Map<String, Object> receiptData, List<String> objectSubtype) {
        if (!CollectionUtils.isEmpty(objectSubtype)) {
            ArrayList<ObjectSubtypeDTO> objectSubtypesList = new ArrayList<>();
            for (String s : objectSubtype) {
                ObjectSubtypeDTO ost = objectSubtypeService.getById(s);
                objectSubtypesList.add(ost);
            }
            receiptData.put("objectSubtypes", objectSubtypesList);
        }
    }

    private void fillObjectRange(Map<String, Object> receiptData, List<String> objectRange, String lang) {
        if (!CollectionUtils.isEmpty(objectRange)) {
            ArrayList<ObjectTypeDTO> objectTypesList = new ArrayList<>();
            for (String s : objectRange) {
                ObjectTypeDTO objectType = objectTypeService.getById(s);
                if (Objects.nonNull(objectType)) {
                    objectTypesList.add(objectType);
                }
            }
            receiptData.put("objectRange", objectTypesList.stream()
                    .map(x -> isPrimaryLang(lang) ? x.getDescription() : x.getDescriptionEn())
                    .collect(Collectors.joining(", ")));
        }
    }

    private void fillPriorityCountry(Map<String, Object> receiptData, PriorityFilterDetailsDTO priority) {
        if (Objects.nonNull(priority) && Objects.nonNull(priority.getPriorityCountry()) && StringUtils.hasText(priority.getPriorityCountry().getId())) {
            CountryDTO priorityCountry = countryService.getById(priority.getPriorityCountry().getId());
            receiptData.put("priorityCountry", priorityCountry);
        }
    }

    private void fillApplicantCountry(Map<String, Object> receiptData, CountryDTO country) {
        if (Objects.nonNull(country) && StringUtils.hasText(country.getId())) {
            CountryDTO applicantCountry = countryService.getById(country.getId());
            receiptData.put("applicantOwnerCountry", applicantCountry);
        }
    }

    public ReceiptParamsPdf populateAgentSearchReceiptParams(AgentFilter filter, String lang) {
        CommonReceiptUtils.validateLanguage(lang);

        String templateName;
        String fileName;
        Map<String, Object> receiptData = new HashMap<>();

        boolean isAgentSearch = filter.getRepresentativeTypeList().contains(RepresentativeType.AGENT);
        templateName = isAgentSearch ? TemplateNames.SEARCH_AGENT.getTemplatePath() : TemplateNames.SEARCH_PARTNERSHIP.getTemplatePath();
        fileName = isAgentSearch ? SearchReceiptNames.AGENT.generateFileNamePdf() : SearchReceiptNames.PARTNERSHIP.generateFileNamePdf();
        filter.setPage(0);
        filter.setLanguage(lang);
        Integer maxResultsCount = SecurityUtils.isUserAuthenticated() ? RESULT_COUNT_AUTHENTICATED : RESULT_COUNT_UNAUTHENTICATED;
        filter.setPageSize(maxResultsCount);
        receiptData.put("maxResultsCount", maxResultsCount);
        Page<IpPersonDTO> agentResults = ipAgentService.filterAgents(filter);
        receiptData.put("results", agentResults.stream().toList());
        receiptData.put("filter", filter);
        receiptData.put(REGISTER_TYPE_PARAM_NAME, isAgentSearch ? RegisterTypes.AGENT : RegisterTypes.PARTNERSHIP);
        receiptData.put(LANGUAGE_PARAM_NAME, lang);

        if (StringUtils.hasText(filter.getIpoArea())) {
            AgentSpecialityDTO ipoArea = agentSpecialityService.getById(filter.getIpoArea());
            receiptData.put("ipoArea", ipoArea);
        }
        if (Objects.nonNull(filter.getStatus())) {
            AgentStatusDTO status = agentStatusService.getById(filter.getStatus());
            receiptData.put("status", status);
        }

        return new ReceiptParamsPdf(receiptData, templateName, fileName);
    }

    public ReceiptParamsPdf populateDecisionSearchReceiptParams(LegalDecisionFilter filter, String lang) {
        CommonReceiptUtils.validateLanguage(lang);

        String templateName;
        String fileName;
        Map<String, Object> receiptData = new HashMap<>();

        templateName = TemplateNames.SEARCH_LEGAL_DECISION.getTemplatePath();
        fileName = SearchReceiptNames.LEGAL_DECISION.generateFileNamePdf();
        filter.setPage(0);
        Integer maxResultsCount = SecurityUtils.isUserAuthenticated() ? RESULT_COUNT_AUTHENTICATED : RESULT_COUNT_UNAUTHENTICATED;
        filter.setPageSize(maxResultsCount);
        receiptData.put("maxResultsCount", maxResultsCount);
        Page<LegalDecisionDocumentDTO> decisionResults = legalDecisionDocumentService.filterDocuments(filter);
        receiptData.put("results", decisionResults.stream().toList());
        receiptData.put("filter", filter);
        receiptData.put(REGISTER_TYPE_PARAM_NAME, RegisterTypes.DECISIONS);
        receiptData.put(LANGUAGE_PARAM_NAME, lang);

        if (StringUtils.hasText(filter.getObjectType())) {
            ObjectTypeDTO objectType = objectTypeService.getById(filter.getObjectType());
            receiptData.put("objectType", objectType);
        }

        return new ReceiptParamsPdf(receiptData, templateName, fileName);
    }

    @Getter
    @AllArgsConstructor
    public static class ReceiptParamsPdf {
        private Map<String, Object> receiptData;
        private String templateName;
        private String fileName;
    }
}
