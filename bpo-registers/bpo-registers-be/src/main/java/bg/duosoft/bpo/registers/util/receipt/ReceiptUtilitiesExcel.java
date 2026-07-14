package bg.duosoft.bpo.registers.util.receipt;

import bg.duosoft.bpo.common.security.util.SecurityUtils;
import bg.duosoft.bpo.excelgenerator.ExcelGenerator;
import bg.duosoft.bpo.excelgenerator.data.BorderStyle;
import bg.duosoft.bpo.excelgenerator.data.CellConfiguration;
import bg.duosoft.bpo.excelgenerator.data.ExcelHeaderColumn;
import bg.duosoft.bpo.excelgenerator.data.ExcelSheet;
import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import bg.duosoft.bpo.publik.core.dto.nomenclature.*;
import bg.duosoft.bpo.publik.core.enums.RepresentativeType;
import bg.duosoft.bpo.publik.core.service.nomenclature.*;
import bg.duosoft.bpo.registers.dto.filter.*;
import bg.duosoft.bpo.registers.dto.ipobject.IpPersonDTO;
import bg.duosoft.bpo.registers.dto.ipobject.VwPersonObjectRelationshipDTO;
import bg.duosoft.bpo.registers.dto.legaldecision.LegalDecisionDocumentDTO;
import bg.duosoft.bpo.registers.mapper.ipobject.search.*;
import bg.duosoft.bpo.registers.service.file.ThumbnailService;
import bg.duosoft.bpo.registers.service.ipobject.IpAgentService;
import bg.duosoft.bpo.registers.service.ipobject.IpObjectSearchService;
import bg.duosoft.bpo.registers.service.ipobject.VwPersonObjectRelationshipsService;
import bg.duosoft.bpo.registers.service.legaldecision.LegalDecisionDocumentService;
import bg.duosoft.bpo.registers.util.RegisterTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static bg.duosoft.bpo.registers.util.receipt.CommonReceiptUtils.isPrimaryLang;
import static bg.duosoft.bpo.registers.util.receipt.CommonReceiptUtils.isSecondaryLang;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceiptUtilitiesExcel {

    private static final Integer RESULT_COUNT_AUTHENTICATED = 2_000;
    private static final Integer RESULT_COUNT_UNAUTHENTICATED = 2_000;
    private static final Integer CELL_WIDTH_XS = 3_000;
    private static final Integer CELL_WIDTH_S = 5_000;
    private static final Integer CELL_WIDTH_M = 10_000;
    private static final Integer CELL_WIDTH_L = 15_000;
    private static final Integer CELL_WIDTH_XL = 20_000;
    private static final Integer CELL_WIDTH_XXL = 25_000;

    private final IpObjectSearchService ipObjectSearchService;
    private final MarkFilterDetailsMapper markFilterDetailsMapper;
    private final GeoIndicationFilterDetailsMapper geoIndicationFilterDetailsMapper;
    private final PatentFilterDetailsMapper patentFilterDetailsMapper;
    private final DesignFilterDetailsMapper designFilterDetailsMapper;
    private final EuPatentFilterDetailsMapper euPatentFilterDetailsMapper;
    private final UtilityModelFilterDetailsMapper utilityModelFilterDetailsMapper;
    private final PlantBreedFilterDetailsMapper plantBreedFilterDetailsMapper;
    private final SpcFilterDetailsMapper spcFilterDetailsMapper;
    private final CombinedFilterDetailsMapper combinedFilterDetailsMapper;
    private final MarkKindService markKindService;
    private final ObjectTypeService objectTypeService;
    private final ObjectSubtypeService objectSubtypeService;
    private final PublicationSectionService publicationSectionService;
    private final CountryService countryService;
    private final IpAgentService ipAgentService;
    private final AgentSpecialityService agentSpecialityService;
    private final AgentStatusService agentStatusService;
    private final MessageSource messageSource;
    private final ThumbnailService thumbnailService;
    private final VwPersonObjectRelationshipsService personObjectRelationshipsService;
    private final LegalDecisionDocumentService legalDecisionDocumentService;


    public byte[] getReceiptBytes(ReceiptUtilitiesExcel.ReceiptParamsExcel receiptParamsExcel, Locale locale) throws IOException {
        String resultsMsg = messageSource.getMessage("l.results", null, "", locale);
        String filtersMsg = messageSource.getMessage("l.filters", null, "", locale);

        ExcelGenerator excelGenerator = new ExcelGenerator();
        ExcelSheet sheet = ExcelSheet.builder()
                .data(receiptParamsExcel.getResultRows())
                .label(resultsMsg)
                .headerCellConfiguration(CellConfiguration.builder().bold(true).border(BorderStyle.MEDIUM).build())
                .dataCellConfiguration(CellConfiguration.builder().border(BorderStyle.THIN).build())
                .headers(receiptParamsExcel.getResultHeaderColumns()).build();
        excelGenerator.addSheetData(sheet);

        sheet = ExcelSheet.builder()
                .data(receiptParamsExcel.getFilterRows())
                .label(filtersMsg)
                .uniqueStylePerCell(true)
                .headerCellConfiguration(CellConfiguration.builder().bold(true).border(BorderStyle.MEDIUM).build())
                .dataCellConfiguration(CellConfiguration.builder().border(BorderStyle.THIN).wordWrap(true).build())
                .headers(receiptParamsExcel.getFilterHeaders()).build();

        excelGenerator.addSheetData(sheet);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        excelGenerator.generate(bos);
        return bos.toByteArray();
    }

    public ReceiptParamsExcel populatePersonToObjectsReceiptParams(String lang, String personName) {
        CommonReceiptUtils.validateLanguage(lang);
        Locale locale = Locale.of(lang);
        String fileName = SearchReceiptNames.PERSON_OBJECT_RELATIONS.generateFileNameXlsx();
        ArrayList<ExcelHeaderColumn> resultHeaderColumns = new ArrayList<>();
        List<List<Object>> resultRows = new ArrayList<>();
        ArrayList<List<Object>> filterRows = new ArrayList<>();

        filterRows.add(Arrays.asList(getMessage("l.personName", locale), personName));

        resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.object", locale)).resultColIndex(0).build());
        resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_XL).headerLabel(getMessage("l.title", locale)).resultColIndex(1).build());
        resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.personRole", locale)).resultColIndex(2).build());

        List<VwPersonObjectRelationshipDTO> records = personObjectRelationshipsService.getRelationshipsByPersonName(personName);

        if (Objects.nonNull(records)) {
            for (VwPersonObjectRelationshipDTO r : records) {
                ArrayList<Object> rowData = new ArrayList<>();
                rowData.add(r.getId().getObjectId()); //index 0
                rowData.add(r.getObjectTitle()); //index 1
                rowData.add(isPrimaryLang(lang) ? r.getRoleDescription() : r.getRoleDescriptionEn()); //index 2
                resultRows.add(rowData);
            }
        }

        return new ReceiptParamsExcel(generateFilterHeaderColumns(locale), filterRows, resultHeaderColumns, resultRows, fileName);
    }

    public ReceiptParamsExcel populateSearchReceiptParams(CommonIpObjectFilterDetailsDTO filter, String lang, String registerType) {
        CommonReceiptUtils.validateLanguage(lang);
        Locale locale = Locale.of(lang);
        String fileName;
        IpObjectFilter searchRequiredFilter;
        List<List<Object>> filterRows = new ArrayList<>();
        ArrayList<ExcelHeaderColumn> resultHeaderColumns = new ArrayList<>();
        List<List<Object>> resultRows = new ArrayList<>();

        fillCommonFilters(filterRows, filter, locale, registerType);

        resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_S).headerLabel(getMessage("l.objectId", locale)).resultColIndex(0).build());
        resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_S).headerLabel(getMessage("l.filingDate", locale)).resultColIndex(4).build());
        resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.title." + registerType, locale)).resultColIndex(5).dataCellConfiguration(CellConfiguration.builder().wordWrap(true).build()).build());
        resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_S).headerLabel(getMessage(isMarkLike(registerType) || registerType.equalsIgnoreCase(RegisterTypes.COMBINED) ? "l.registrationNumber" : "l.protectionNumber." + registerType, locale)).resultColIndex(7).build());
        resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.status", locale)).resultColIndex(8).dataCellConfiguration(CellConfiguration.builder().wordWrap(true).build()).build());
        resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.object.mainOwner." + registerType, locale)).resultColIndex(9).build());

        if (filter instanceof MarkFilterDetailsDTO markFilter) {
            fileName = SearchReceiptNames.MARK.generateFileNameXlsx();
            searchRequiredFilter = markFilterDetailsMapper.toIpObjectFilter(markFilter);
            fillMarkLikeFilters(filterRows, markFilter, locale, registerType);
            fillMarkFilters(filterRows, markFilter, locale, registerType);

            resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_M).headerLabel(getMessage("l.niceClasses.h", locale)).resultColIndex(11).build());
            resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_S).headerLabel(getMessage("l.image", locale)).resultColIndex(12).build());

        } else if (filter instanceof GeoIndicationFilterDetailsDTO geoFilter) {
            fileName = SearchReceiptNames.GEO_INDICATION.generateFileNameXlsx();
            searchRequiredFilter = geoIndicationFilterDetailsMapper.toIpObjectFilter(geoFilter);
            fillMarkLikeFilters(filterRows, geoFilter, locale, registerType);
        } else if (filter instanceof PatentFilterDetailsDTO patentFilter) {
            fileName = SearchReceiptNames.PATENT.generateFileNameXlsx();
            searchRequiredFilter = patentFilterDetailsMapper.toIpObjectFilter(patentFilter);
            fillPatentLikeFilters(filterRows, patentFilter, locale, registerType);
        } else if (filter instanceof DesignFilterDetailsDTO designFilter) {
            fileName = SearchReceiptNames.DESIGN.generateFileNameXlsx();
            searchRequiredFilter = designFilterDetailsMapper.toIpObjectFilter(designFilter);
            fillPatentLikeFilters(filterRows, designFilter, locale, registerType);
            fillDesignFilters(filterRows, designFilter, locale);
        } else if (filter instanceof EuPatentFilterDetailsDTO euPatentFilter) {
            fileName = SearchReceiptNames.EU_PATENT.generateFileNameXlsx();
            searchRequiredFilter = euPatentFilterDetailsMapper.toIpObjectFilter(euPatentFilter);
            fillPatentLikeFilters(filterRows, euPatentFilter, locale, registerType);
        } else if (filter instanceof UtilityModelFilterDetailsDTO umFilter) {
            fileName = SearchReceiptNames.UTILITY_MODEL.generateFileNameXlsx();
            searchRequiredFilter = utilityModelFilterDetailsMapper.toIpObjectFilter(umFilter);
            fillPatentLikeFilters(filterRows, umFilter, locale, registerType);
        } else if (filter instanceof PlantBreedFilterDetailsDTO plantBreedFilter) {
            fileName = SearchReceiptNames.PLANT_BREED.generateFileNameXlsx();
            searchRequiredFilter = plantBreedFilterDetailsMapper.toIpObjectFilter(plantBreedFilter);
            fillPatentLikeFilters(filterRows, plantBreedFilter, locale, registerType);
            fillPlantBreedFilters(filterRows, plantBreedFilter, locale);
        } else if (filter instanceof SpcFilterDetailsDTO spcFilter) {
            fileName = SearchReceiptNames.SPC.generateFileNameXlsx();
            searchRequiredFilter = spcFilterDetailsMapper.toIpObjectFilter(spcFilter);
            fillPatentLikeFilters(filterRows, spcFilter, locale, registerType);
        } else {
            fileName = SearchReceiptNames.COMBINED.generateFileNameXlsx();
            searchRequiredFilter = combinedFilterDetailsMapper.toIpObjectFilter(filter);
            fillCombinedFilters(filterRows, filter, locale);
        }

        if (Objects.nonNull(searchRequiredFilter)) {
            searchRequiredFilter.setPage(0);
            Integer maxResultsCount = SecurityUtils.isUserAuthenticated() ? RESULT_COUNT_AUTHENTICATED : RESULT_COUNT_UNAUTHENTICATED;
            searchRequiredFilter.setPageSize(maxResultsCount);
            filterRows.add(Arrays.asList(getMessage("l.maxResults", locale), maxResultsCount));
            Page<IpObjectSearchResult> search = ipObjectSearchService.search(searchRequiredFilter);
            List<IpObjectSearchResult> results = search.stream().toList();
            for (IpObjectSearchResult r : results) {
                ArrayList<Object> rowData = new ArrayList<>();
                rowData.add(r.getObjectId()); //index 0
                rowData.add(r.getObjectType()); //index 1
                rowData.add(r.getObjectSubtype()); //index 2
                rowData.add(r.getFilingNumber()); //index 3
                rowData.add(r.getFilingDate()); //index 4
                rowData.add(r.getTitle()); //index 5
                rowData.add(r.getRegistrationDate()); //index 6
                rowData.add(r.getRegistrationNbr()); //index 7
                rowData.add(r.getStatusName()); //index 8
                rowData.add(r.getMainOwner()); //index 9
                rowData.add(r.getMarkKind()); //index 10
                rowData.add(r.getNiceClasses().stream().map(x -> x + "").collect(Collectors.joining(", "))); //index 11
                if (r.getHasImg()) {
                    try {
                        FileStoreEntryBaseDTO file = thumbnailService.getThumbnailByObjectIdAndType(r.getObjectId(), r.getObjectType());
                        rowData.add(file.getContent()); //index 12
                    } catch (Exception e) {
                        log.error("Could not fetch file for " + r.getObjectId());
                        rowData.add(null);
                    }
                } else {
                    rowData.add(null);
                }
                resultRows.add(rowData);
            }
        }

        return new ReceiptParamsExcel(generateFilterHeaderColumns(locale), filterRows, resultHeaderColumns, resultRows, fileName);
    }

    private void fillCommonFilters(List<List<Object>> filterRows, CommonIpObjectFilterDetailsDTO filter, Locale locale, String registerType) {
        if (Objects.nonNull(filter.getObjectName()) && StringUtils.hasText(filter.getObjectName().getText())) {
            filterRows.add(Arrays.asList(getMessage("l.name." + registerType, locale), filter.getObjectName().getText()));
            if (Objects.nonNull(filter.getObjectName().getSearchType()))
                filterRows.add(Arrays.asList(getMessage("l.searchType", locale), getMessage("l." + filter.getObjectName().getSearchType().name(), locale)));
        }
        if (Objects.nonNull(filter.getFilingNumber())) {
            if (Objects.nonNull(filter.getFilingNumber().getFrom()) && StringUtils.hasText(filter.getFilingNumber().getFrom().getName()))
                filterRows.add(Arrays.asList(getMessage("l.filingNumberFrom." + registerType, locale), filter.getFilingNumber().getFrom().getName()));
            if (Objects.nonNull(filter.getFilingNumber().getTo()) && StringUtils.hasText(filter.getFilingNumber().getTo().getName()))
                filterRows.add(Arrays.asList(getMessage("l.filingNumberTo." + registerType, locale), filter.getFilingNumber().getTo().getName()));
        }
        if (Objects.nonNull(filter.getFilingDate())) {
            if (Objects.nonNull(filter.getFilingDate().getFrom()))
                filterRows.add(Arrays.asList(getMessage("l.filingDateFrom." + registerType, locale), filter.getFilingDate().getFrom()));
            if (Objects.nonNull(filter.getFilingDate().getTo()))
                filterRows.add(Arrays.asList(getMessage("l.filingDateTo." + registerType, locale), filter.getFilingDate().getTo()));
        }
        if (Objects.nonNull(filter.getRegistrationNumber())) {
            if (Objects.nonNull(filter.getRegistrationNumber().getFrom()) && StringUtils.hasText(filter.getRegistrationNumber().getFrom().getName()))
                filterRows.add(Arrays.asList(getMessage("l.registrationNumberFrom." + registerType, locale), filter.getRegistrationNumber().getFrom().getName()));
            if (Objects.nonNull(filter.getRegistrationNumber().getTo()) && StringUtils.hasText(filter.getRegistrationNumber().getTo().getName()))
                filterRows.add(Arrays.asList(getMessage("l.registrationNumberTo." + registerType, locale), filter.getRegistrationNumber().getTo().getName()));
        }
        if (Objects.nonNull(filter.getExpirationDate())) {
            if (Objects.nonNull(filter.getExpirationDate().getFrom()))
                filterRows.add(Arrays.asList(getMessage("l.expirationDateFrom." + registerType, locale), filter.getExpirationDate().getFrom()));
            if (Objects.nonNull(filter.getExpirationDate().getTo()))
                filterRows.add(Arrays.asList(getMessage("l.expirationDateTo." + registerType, locale), filter.getExpirationDate().getTo()));
        }
        if (Objects.nonNull(filter.getStatus())) {
            if (isPrimaryLang(locale.getLanguage())) {
                if (StringUtils.hasText(filter.getStatus().getBpoOnlineStatus()))
                    filterRows.add(Arrays.asList(getMessage("l.status", locale), filter.getStatus().getBpoOnlineStatus()));
            } else {
                if (StringUtils.hasText(filter.getStatus().getBpoOnlineStatusEn()))
                    filterRows.add(Arrays.asList(getMessage("l.status", locale), filter.getStatus().getBpoOnlineStatusEn()));
            }
        }
        if (Objects.nonNull(filter.getPublications())) {
            if (StringUtils.hasText(filter.getPublications().getPublicationYear()))
                filterRows.add(Arrays.asList(getMessage("l.publicationYear." + registerType, locale), filter.getPublications().getPublicationYear()));
            if (StringUtils.hasText(filter.getPublications().getPublicationNumber()))
                filterRows.add(Arrays.asList(getMessage("l.publicationNumber." + registerType, locale), filter.getPublications().getPublicationNumber()));

            if (Objects.nonNull(filter.getPublications().getPublicationSection())) {
                PublicationSectionDTO ps = publicationSectionService.getById(filter.getPublications().getPublicationSection());
                if (isPrimaryLang(locale)) {
                    if (Objects.nonNull(ps) && StringUtils.hasText(ps.getNmsection()))
                        filterRows.add(Arrays.asList(getMessage("l.publicationSection." + registerType, locale), ps.getNmsection()));
                } else {
                    if (Objects.nonNull(ps) && StringUtils.hasText(ps.getNmsectionEn()))
                        filterRows.add(Arrays.asList(getMessage("l.publicationSection." + registerType, locale), ps.getNmsectionEn()));
                }
            }
            if (Objects.nonNull(filter.getPublications().getPublicationDate())) {
                if (Objects.nonNull(filter.getPublications().getPublicationDate().getFrom()))
                    filterRows.add(Arrays.asList(getMessage("l.publicationDateFrom." + registerType, locale), filter.getPublications().getPublicationDate().getFrom()));
                if (Objects.nonNull(filter.getPublications().getPublicationDate().getTo()))
                    filterRows.add(Arrays.asList(getMessage("l.publicationDateTo." + registerType, locale), filter.getPublications().getPublicationDate().getTo()));
            }
        }
        if (!CollectionUtils.isEmpty(filter.getRepresentativeTypes())) {
            filterRows.add(Arrays.asList(getMessage("l.representativeTypes", locale), filter.getRepresentativeTypes().stream().map(RepresentativeTypeDTO::getDescription).collect(Collectors.joining(", "))));
        }
        if (StringUtils.hasText(filter.getRepresentativeName())) {
            filterRows.add(Arrays.asList(getMessage("l.representativeName." + registerType, locale), filter.getRepresentativeName()));
            if (Objects.nonNull(filter.getRepresentativeNameSearchType()))
                filterRows.add(Arrays.asList(getMessage("l.searchType", locale), getMessage("l." + filter.getRepresentativeNameSearchType().name(), locale)));
        }
        if (!CollectionUtils.isEmpty(filter.getRepresentatives())) {
            filterRows.add(Arrays.asList(getMessage("l.representatives." + registerType, locale), filter.getRepresentatives().stream().map(x -> x.getName() + " (" + x.getAgentCode() + ")").collect(Collectors.joining(", "))));
        }
        if (StringUtils.hasText(filter.getApplicantOwner())) {
            filterRows.add(Arrays.asList(getMessage("l.applicantOwner." + registerType, locale), filter.getApplicantOwner()));
            if (Objects.nonNull(filter.getApplicantOwnerPersonSearchType()))
                filterRows.add(Arrays.asList(getMessage("l.searchType", locale), getMessage("l." + filter.getApplicantOwnerPersonSearchType().name(), locale)));
        }
        if (Objects.nonNull(filter.getApplicantOwnerCountry()) && StringUtils.hasText(filter.getApplicantOwnerCountry().getId())) {
            CountryDTO applicantCountry = countryService.getById(filter.getApplicantOwnerCountry().getId());
            if (Objects.nonNull(applicantCountry)) {
                filterRows.add(Arrays.asList(getMessage("l.applicantOwnerCountry", locale),
                        isSecondaryLang(locale) ? applicantCountry.getNameEn() : applicantCountry.getName()));
            }
        }
        if (Objects.nonNull(filter.getPriority())) {
            if (Objects.nonNull(filter.getPriority().getPriorityDate())) {
                if (Objects.nonNull(filter.getPriority().getPriorityDate().getFrom()))
                    filterRows.add(Arrays.asList(getMessage("l.priorityDateFrom." + registerType, locale), filter.getPriority().getPriorityDate().getFrom()));
                if (Objects.nonNull(filter.getPriority().getPriorityDate().getTo()))
                    filterRows.add(Arrays.asList(getMessage("l.priorityDateTo." + registerType, locale), filter.getPriority().getPriorityDate().getTo()));
            }
            if (Objects.nonNull(filter.getPriority().getPriorityCountry()) && StringUtils.hasText(filter.getPriority().getPriorityCountry().getId())) {
                CountryDTO priorityCountry = countryService.getById(filter.getPriority().getPriorityCountry().getId());
                if (Objects.nonNull(priorityCountry)) {
                    filterRows.add(Arrays.asList(getMessage("l.priorityCountry." + registerType, locale),
                            isSecondaryLang(locale) ? priorityCountry.getNameEn() : priorityCountry.getName()));
                }
            }
            if (StringUtils.hasText(filter.getPriority().getPriorityNumber())) {
                filterRows.add(Arrays.asList(getMessage("l.priorityNumber." + registerType, locale), filter.getPriority().getPriorityNumber()));
            }
        }
    }

    private void fillMarkLikeFilters(List<List<Object>> filterRows, MarkLikeFilterDetailsDTO filter, Locale locale, String registerType) {
        if (!CollectionUtils.isEmpty(filter.getObjectSubtype())) {
            ArrayList<String> objectSubtypesList = new ArrayList<>();
            for (String s : filter.getObjectSubtype()) {
                ObjectSubtypeDTO ost = objectSubtypeService.getById(s);
                objectSubtypesList.add(isSecondaryLang(locale) ? ost.getDescriptionEn() : ost.getDescription());
            }
            filterRows.add(Arrays.asList(getMessage("l.markType." + registerType, locale), String.join(", ", objectSubtypesList)));
        }
        if (Objects.nonNull(filter.getNiceClasses())) {
            if (!CollectionUtils.isEmpty(filter.getNiceClasses().getNiceClassCodes())) {
                filterRows.add(Arrays.asList(getMessage("l.niceClasses", locale), String.join(", ", filter.getNiceClasses().getNiceClassCodes())));
                if (Objects.nonNull(filter.getNiceClasses().getNiceClassCodeType()))
                    filterRows.add(Arrays.asList(getMessage("l.searchType", locale), getMessage("l." + filter.getNiceClasses().getNiceClassCodeType().name(), locale)));
            }
            if (Objects.nonNull(filter.getNiceClasses().getSpecification()) && StringUtils.hasText(filter.getNiceClasses().getSpecification().getText())) {
                filterRows.add(Arrays.asList(getMessage("l.specification", locale), filter.getNiceClasses().getSpecification().getText()));
                if (Objects.nonNull(filter.getNiceClasses().getSpecification().getSearchType()))
                    filterRows.add(Arrays.asList(getMessage("l.searchType", locale), getMessage("l." + filter.getNiceClasses().getSpecification().getSearchType().name(), locale)));
            }
        }
        if (Objects.nonNull(filter.getViennaClasses()) && !CollectionUtils.isEmpty(filter.getViennaClasses().getViennaClasses())) {
            filterRows.add(Arrays.asList(getMessage("l.viennaClasses", locale), filter.getViennaClasses().getViennaClasses().stream().map(x -> x.getId() + " - " + x.getName()).collect(Collectors.joining(", "))));
            if (Objects.nonNull(filter.getViennaClasses().getViennaClassCodeType()))
                filterRows.add(Arrays.asList(getMessage("l.searchType", locale), getMessage("l." + filter.getViennaClasses().getViennaClassCodeType().name(), locale)));
        }
    }

    private void fillMarkFilters(List<List<Object>> filterRows, MarkFilterDetailsDTO markFilter, Locale locale, String registerType) {
        if (Objects.nonNull(markFilter.getMarkKind()) && Objects.nonNull(markFilter.getMarkKind().getId())) {
            MarkKindDTO mk = markKindService.getById((markFilter.getMarkKind().getId()));
            if (isPrimaryLang(locale)) {
                filterRows.add(Arrays.asList(getMessage("l.markKind." + registerType, locale), mk.getDescription()));
            } else {
                filterRows.add(Arrays.asList(getMessage("l.markKind." + registerType, locale), mk.getDescriptionEn()));
            }
        }
    }

    private void fillPatentLikeFilters(List<List<Object>> filterRows, PatentLikeFilterDetailsDTO filter, Locale locale, String registerType) {
        if (Objects.nonNull(filter.getAbstractDetails())) {
            if (StringUtils.hasText(filter.getAbstractDetails().getText())) {
                filterRows.add(Arrays.asList(getMessage("l.abstract." + registerType, locale), filter.getAbstractDetails().getText()));
                if (Objects.nonNull(filter.getAbstractDetails().getSearchType()))
                    filterRows.add(Arrays.asList(getMessage("l.searchType", locale), getMessage("l." + filter.getAbstractDetails().getSearchType().name(), locale)));
            }
        }
        if (Objects.nonNull(filter.getIpcClasses()) && !CollectionUtils.isEmpty(filter.getIpcClasses().getIpcClasses())) {
            filterRows.add(Arrays.asList(getMessage("l.ipcClasses", locale), filter.getIpcClasses().getIpcClasses().stream().map(x -> x.getId().toStringFormatted()).collect(Collectors.joining(", "))));
            if (Objects.nonNull(filter.getIpcClasses().getIpcClassOperatorType()))
                filterRows.add(Arrays.asList(getMessage("l.searchType", locale), getMessage("l." + filter.getIpcClasses().getIpcClassOperatorType().name(), locale)));
        }
        if (StringUtils.hasText(filter.getIpcCode())) {
            filterRows.add(Arrays.asList(getMessage("l.ipcCode", locale), filter.getIpcCode()));
        }
        if (Objects.nonNull(filter.getCpcClasses()) && !CollectionUtils.isEmpty(filter.getCpcClasses().getCpcClasses())) {
            filterRows.add(Arrays.asList(getMessage("l.cpcClasses", locale), filter.getCpcClasses().getCpcClasses().stream().map(x -> x.getId().toStringFormatted()).collect(Collectors.joining(", "))));
            if (Objects.nonNull(filter.getCpcClasses().getCpcClassOperatorType()))
                filterRows.add(Arrays.asList(getMessage("l.searchType", locale), getMessage("l." + filter.getCpcClasses().getCpcClassOperatorType().name(), locale)));
        }
        if (StringUtils.hasText(filter.getCpcCode())) {
            filterRows.add(Arrays.asList(getMessage("l.cpcCode", locale), filter.getCpcCode()));
        }
        if (StringUtils.hasText(filter.getInventor())) {
            filterRows.add(Arrays.asList(getMessage("l.inventor." + registerType, locale), filter.getInventor()));
            if (Objects.nonNull(filter.getInventor()))
                filterRows.add(Arrays.asList(getMessage("l.searchType", locale), getMessage("l." + filter.getInventorPersonSearchType().name(), locale)));
        }
    }

    private void fillPlantBreedFilters(List<List<Object>> filterRows, PlantBreedFilterDetailsDTO filter, Locale locale) {
        if (!CollectionUtils.isEmpty(filter.getObjectSubtype())) {
            ArrayList<String> objectSubtypesList = new ArrayList<>();
            for (String s : filter.getObjectSubtype()) {
                ObjectSubtypeDTO ost = objectSubtypeService.getById(s);
                objectSubtypesList.add(isSecondaryLang(locale) ? ost.getDescriptionEn() : ost.getDescription());
            }
            filterRows.add(Arrays.asList(getMessage("l.markType." + RegisterTypes.PLANT_BREED, locale), String.join(", ", objectSubtypesList)));
        }
        if (StringUtils.hasText(filter.getBgClassification())) {
            filterRows.add(Arrays.asList(getMessage("l.bgClassification", locale), filter.getBgClassification()));
        }
        if (StringUtils.hasText(filter.getLatinClassification())) {
            filterRows.add(Arrays.asList(getMessage("l.latinClassification", locale), filter.getLatinClassification()));
        }
    }

    private void fillDesignFilters(List<List<Object>> filterRows, DesignFilterDetailsDTO filter, Locale locale) {
        if (StringUtils.hasText(filter.getSingleDesignName())) {
            filterRows.add(Arrays.asList(getMessage("l.single.design.name", locale), filter.getSingleDesignName()));
            if (Objects.nonNull(filter.getSingleDesignNameSearchType()))
                filterRows.add(Arrays.asList(getMessage("l.searchType", locale), getMessage("l." + filter.getSingleDesignNameSearchType().name(), locale)));
        }
        if (StringUtils.hasText(filter.getSingleDesignVerbalElement())) {
            filterRows.add(Arrays.asList(getMessage("l.single.design.verbal.element", locale), filter.getSingleDesignVerbalElement()));
            if (Objects.nonNull(filter.getSingleDesignVerbalElementSearchType()))
                filterRows.add(Arrays.asList(getMessage("l.searchType", locale), getMessage("l." + filter.getSingleDesignVerbalElementSearchType().name(), locale)));
        }
    }

    private void fillCombinedFilters(List<List<Object>> filterRows, CommonIpObjectFilterDetailsDTO filter, Locale locale) {
        if (!CollectionUtils.isEmpty(filter.getObjectRange())) {
            ArrayList<String> objectTypesList = new ArrayList<>();
            for (String s : filter.getObjectRange()) {
                ObjectTypeDTO ot = objectTypeService.getById(s);
                objectTypesList.add(isSecondaryLang(locale) ? ot.getDescriptionEn() : ot.getDescription());
            }
            filterRows.add(Arrays.asList(getMessage("l.object.range", locale), String.join(", ", objectTypesList)));
        }
    }

    public ReceiptParamsExcel populateAgentSearchReceiptParams(AgentFilter filter, String lang) {
        CommonReceiptUtils.validateLanguage(lang);
        Locale locale = Locale.of(lang);
        String fileName;
        List<List<Object>> filterRows = new ArrayList<>();
        ArrayList<ExcelHeaderColumn> resultHeaderColumns = new ArrayList<>();
        List<List<Object>> resultRows = new ArrayList<>();
        boolean isAgentSearch = filter.getRepresentativeTypeList().contains(RepresentativeType.AGENT);
        fileName = isAgentSearch ? SearchReceiptNames.AGENT.generateFileNameXlsx() : SearchReceiptNames.PARTNERSHIP.generateFileNameXlsx();

        if (StringUtils.hasText(filter.getName())) {
            filterRows.add(Arrays.asList(getMessage(isAgentSearch ? "l.agentName" : "l.name", locale), filter.getName()));
        }
        if (StringUtils.hasText(filter.getIpoArea())) {
            AgentSpecialityDTO ipoArea = agentSpecialityService.getById(filter.getIpoArea());
            if (Objects.nonNull(ipoArea))
                filterRows.add(Arrays.asList(getMessage("l.ipoArea", locale), isPrimaryLang(locale) ? ipoArea.getName() : ipoArea.getNameEn()));
        }
        if (StringUtils.hasText(filter.getAgentCode())) {
            filterRows.add(Arrays.asList(getMessage("l.agentCode", locale), filter.getAgentCode()));
        }
        if (Objects.nonNull(filter.getStatus())) {
            AgentStatusDTO status = agentStatusService.getById(filter.getStatus());
            if (Objects.nonNull(status))
                filterRows.add(Arrays.asList(getMessage("l.status", locale), isPrimaryLang(locale) ? status.getName() : status.getNameEn()));
        }
        if (isAgentSearch && StringUtils.hasText(filter.getAgentSpeciality())) {
            filterRows.add(Arrays.asList(getMessage("l.agentSpeciality", locale), filter.getAgentSpeciality()));
        }
        if (StringUtils.hasText(filter.getCity())) {
            filterRows.add(Arrays.asList(getMessage("l.city", locale), filter.getCity()));
        }

        filter.setLanguage(lang);
        filter.setPage(0);
        Integer maxResultsCount = SecurityUtils.isUserAuthenticated() ? RESULT_COUNT_AUTHENTICATED : RESULT_COUNT_UNAUTHENTICATED;
        filter.setPageSize(maxResultsCount);
        filterRows.add(Arrays.asList(getMessage("l.maxResults", locale), maxResultsCount));
        Page<IpPersonDTO> agentResults = ipAgentService.filterAgents(filter);
        List<IpPersonDTO> results = agentResults.stream().toList();
        if (isAgentSearch) {
            resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.agentName", locale)).resultColIndex(0).build());
            resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_M).headerLabel(getMessage("l.agentCode", locale)).resultColIndex(1).build());
            resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.agentSpeciality", locale)).resultColIndex(2).build());
            resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.ipoArea", locale)).resultColIndex(3).build());
            resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.agentStatus", locale)).resultColIndex(4).build());
            for (IpPersonDTO r : results) {
                ArrayList<Object> rowData = new ArrayList<>();
                rowData.add(isPrimaryLang(locale) ? r.getName() : r.getAgent().getNameEn());
                rowData.add(r.getAgent().getAgentCode());
                rowData.add(isPrimaryLang(locale) ? r.getAgent().getSpeciality() : r.getAgent().getSpecialityEn());
                rowData.add(isPrimaryLang(locale) ? r.getAgent().getAgentSpeciality().getName() : r.getAgent().getAgentSpeciality().getNameEn());
                rowData.add(isPrimaryLang(locale) ? r.getAgent().getAgentStatus().getName() : r.getAgent().getAgentStatus().getNameEn());
                resultRows.add(rowData);
            }
        } else {
            resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.name", locale)).resultColIndex(0).build());
            resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_M).headerLabel(getMessage("l.agentCode", locale)).resultColIndex(1).build());
            resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.ipoArea", locale)).resultColIndex(2).build());
            resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.agentStatus", locale)).resultColIndex(3).build());
            resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.representativeType", locale)).resultColIndex(4).build());
            for (IpPersonDTO r : results) {
                ArrayList<Object> rowData = new ArrayList<>();
                rowData.add(isPrimaryLang(locale) ? r.getName() : r.getAgent().getNameEn());
                rowData.add(r.getAgent().getAgentCode());
                rowData.add(isPrimaryLang(locale) ? r.getAgent().getAgentSpeciality().getName() : r.getAgent().getAgentSpeciality().getNameEn());
                rowData.add(isPrimaryLang(locale) ? r.getAgent().getAgentStatus().getName() : r.getAgent().getAgentStatus().getNameEn());
                rowData.add(isPrimaryLang(locale) ? r.getAgent().getRepresentativeType().getDescription() : r.getAgent().getRepresentativeType().getDescriptionEn());
                resultRows.add(rowData);
            }
        }
        return new ReceiptParamsExcel(generateFilterHeaderColumns(locale), filterRows, resultHeaderColumns, resultRows, fileName);
    }

    public ReceiptParamsExcel populateDecisionSearchReceiptParams(LegalDecisionFilter filter, String lang) {
        CommonReceiptUtils.validateLanguage(lang);
        Locale locale = Locale.of(lang);
        String fileName = SearchReceiptNames.LEGAL_DECISION.generateFileNameXlsx();
        List<List<Object>> filterRows = new ArrayList<>();
        ArrayList<ExcelHeaderColumn> resultHeaderColumns = new ArrayList<>();
        List<List<Object>> resultRows = new ArrayList<>();

        if (StringUtils.hasText(filter.getObjectType())) {
            ObjectTypeDTO ot = objectTypeService.getById(filter.getObjectType());
            if (Objects.nonNull(ot))
                filterRows.add(Arrays.asList(getMessage("l.objectType", locale), isSecondaryLang(locale) ? ot.getDescriptionEn() : ot.getDescription()));
        }
        if (StringUtils.hasText(filter.getObjectId())) {
            filterRows.add(Arrays.asList(getMessage("l.objectId", locale), filter.getObjectId()));
        }
        if (Objects.nonNull(filter.getDocumentDate())) {
            if (Objects.nonNull(filter.getDocumentDate().getFrom()))
                filterRows.add(Arrays.asList(getMessage("l.documentDateFrom", locale), filter.getDocumentDate().getFrom()));
            if (Objects.nonNull(filter.getDocumentDate().getTo()))
                filterRows.add(Arrays.asList(getMessage("l.documentDateTo", locale), filter.getDocumentDate().getTo()));
        }
        if (Objects.nonNull(filter.getDocumentNumber())) {
            filterRows.add(Arrays.asList(getMessage("l.documentNumber", locale), filter.getDocumentNumber()));
        }
        if (Objects.nonNull(filter.getDocumentType()) && Objects.nonNull(filter.getDocumentType().getId())) {
            filterRows.add(Arrays.asList(getMessage("l.documentType", locale), isSecondaryLang(locale) ? filter.getDocumentType().getNameEn() : filter.getDocumentType().getName()));
        }
        if (!CollectionUtils.isEmpty(filter.getLegalGroundTypes())) {
            ArrayList<String> groundTypesList = new ArrayList<>();
            for (LegalDecisionGroundTypeDTO s : filter.getLegalGroundTypes()) {
                groundTypesList.add(isSecondaryLang(locale) ? s.getNameEn() : s.getName());
            }
            filterRows.add(Arrays.asList(getMessage("l.ground.types", locale), String.join("; ", groundTypesList)));
            if (Objects.nonNull(filter.getLegalGroundTypesOperatorType()))
                filterRows.add(Arrays.asList(getMessage("l.searchType", locale), getMessage("l." + filter.getLegalGroundTypesOperatorType().name(), locale)));
        }

        filter.setPage(0);
        Integer maxResultsCount = SecurityUtils.isUserAuthenticated() ? RESULT_COUNT_AUTHENTICATED : RESULT_COUNT_UNAUTHENTICATED;
        filter.setPageSize(maxResultsCount);
        filterRows.add(Arrays.asList(getMessage("l.maxResults", locale), maxResultsCount));
        Page<LegalDecisionDocumentDTO> decisionResults = legalDecisionDocumentService.filterDocuments(filter);
        List<LegalDecisionDocumentDTO> results = decisionResults.stream().toList();


        resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_S).headerLabel(getMessage("l.objectId", locale)).resultColIndex(0).build());
        resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.title", locale)).resultColIndex(1).dataCellConfiguration(CellConfiguration.builder().wordWrap(true).build()).build());
        resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.documentType", locale)).resultColIndex(2).dataCellConfiguration(CellConfiguration.builder().wordWrap(true).build()).build());
        resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_S).headerLabel(getMessage("l.documentNumber", locale)).resultColIndex(3).build());
        resultHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_S).headerLabel(getMessage("l.documentDate", locale)).resultColIndex(4).build());
        for (LegalDecisionDocumentDTO r : results) {
            ArrayList<Object> rowData = new ArrayList<>();
            rowData.add(r.getObjectId());
            rowData.add(r.getTitle());
            rowData.add(isPrimaryLang(locale) ? r.getDocumentType().getName() : r.getDocumentType().getNameEn());
            rowData.add(r.getDocumentNumber());
            rowData.add(r.getDocumentDate());
            resultRows.add(rowData);
        }


        return new ReceiptParamsExcel(generateFilterHeaderColumns(locale), filterRows, resultHeaderColumns, resultRows, fileName);
    }

    private String getMessage(String msgCode, Locale locale) {
        String message = messageSource.getMessage(msgCode, null, "", locale);
        if (StringUtils.hasText(message)) {
            return message;
        } else {
            //if l.example.PATENT does not exist, fall back to generic l.example
            String codeWithoutLastPart = msgCode.substring(0, msgCode.lastIndexOf("."));
            return messageSource.getMessage(codeWithoutLastPart, null, "", locale);
        }

    }

    private List<ExcelHeaderColumn> generateFilterHeaderColumns(Locale locale) {
        List<ExcelHeaderColumn> excelHeaderColumns = new ArrayList<>();
        excelHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.filterName", locale)).resultColIndex(0).build());
        excelHeaderColumns.add(ExcelHeaderColumn.builder().width(CELL_WIDTH_L).headerLabel(getMessage("l.value", locale)).resultColIndex(1).build());
        return excelHeaderColumns;
    }

    private boolean isMarkLike(String registerType) {
        return List.of(RegisterTypes.MARK, RegisterTypes.GEO_INDICATION).contains(registerType);
    }

    @Getter
    @AllArgsConstructor
    public static class ReceiptParamsExcel {
        private List<ExcelHeaderColumn> filterHeaders;
        private List<List<Object>> filterRows;
        private List<ExcelHeaderColumn> resultHeaderColumns;
        private List<List<Object>> resultRows;
        private String fileName;
    }
}
