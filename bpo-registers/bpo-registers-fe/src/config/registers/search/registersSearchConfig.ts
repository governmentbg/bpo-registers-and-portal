import { SearchGroup } from "../../../types/registers/search/searchGroup";
import {
  REGISTERS_TABLE_COMBINED_COLUMNS_DEFINITION,
  REGISTERS_TABLE_DESIGN_COLUMNS_DEFINITION,
  REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION,
  REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION,
  REGISTERS_TABLE_REPRESENTATIVE_LIKE_COLUMNS_DEFINITION,
  REGISTERS_TABLE_SPC_COLUMNS_DEFINITION,
} from "./table/definition";
import {
  geoIndicationsFiltersInitialValues,
  markFiltersInitialValues,
} from "../../../init/registers/search/filter/markLikeFiltersInitialValues";
import {
  designFiltersInitialValues,
  euPatentFiltersInitialValues,
  patentFiltersInitialValues,
  plantBreedFiltersInitialValues,
  spcFiltersInitialValues,
  utilityModelFiltersInitialValues,
} from "../../../init/registers/search/filter/patentLikeFiltersInitialValues";
import {
  agentsFiltersInitialValues,
  partnershipsFiltersInitialValues,
} from "../../../init/registers/search/filter/agentsFilterInitialValues";
import { GEO_INDICATION_FILTERS, MARK_FILTERS } from "./filters/definition/slice/markLikeFilters";
import {
  DESIGN_FILTERS,
  EU_PATENT_FILTERS,
  PATENTS_FILTERS,
  PLANTS_BREEDS_FILTERS,
  SPC_FILTERS,
  UTILITY_MODEL_FILTERS,
} from "./filters/definition/slice/patentLikeFilters";
import { AGENT_FILTERS, PARTNERSHIP_FILTERS } from "./filters/definition/slice/representativeFilters";
import { combinedFiltersInitialValues } from "../../../init/registers/search/filter/combinedFiltersInitialValues";
import { COMBINE_SEARCH_FILTER } from "./filters/definition/slice/combineSearchFilters";
import { DECISION_FILTERS } from "./filters/definition/slice/decisionsFilters";
import { DECISION_COLUMNS } from "./table/definition/slice/decisionColumns";
import { decisionFiltersInitialValues } from "../../../init/registers/search/filter/decisionFiltersInitialValues";

export const SEARCH_GROUP: SearchGroup = {
  MARK_SEARCH: "mark_search",
  GEO_INDICATION_SEARCH: "gi_search",
  PATENT_SEARCH: "patent_search",
  DESIGN_SEARCH: "design_search",
  EU_PATENT_SEARCH: "eu_patent_search",
  UTILITY_MODEL_SEARCH: "utility_model_search",
  PLANT_BREED_SEARCH: "plant_breed_search",
  SPC_SEARCH: "spc_search",
  COMBINED_SEARCH: "combined_search",
  AGENTS_SEARCH: "agent_search",
  PARTNERSHIP_SEARCH: "partnership_search",
  DECISION_SEARCH: "decision_search",
};

export const selectTableColumnsBySearchGroup = (searchGroup) => {
  switch (searchGroup) {
    case SEARCH_GROUP.MARK_SEARCH:
    case SEARCH_GROUP.GEO_INDICATION_SEARCH:
      return REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION;
    case SEARCH_GROUP.DESIGN_SEARCH:
      return REGISTERS_TABLE_DESIGN_COLUMNS_DEFINITION;
    case SEARCH_GROUP.SPC_SEARCH:
      return REGISTERS_TABLE_SPC_COLUMNS_DEFINITION;
    case SEARCH_GROUP.COMBINED_SEARCH:
      return REGISTERS_TABLE_COMBINED_COLUMNS_DEFINITION;
    case SEARCH_GROUP.AGENTS_SEARCH:
    case SEARCH_GROUP.PARTNERSHIP_SEARCH:
      return REGISTERS_TABLE_REPRESENTATIVE_LIKE_COLUMNS_DEFINITION;
    case SEARCH_GROUP.DECISION_SEARCH:
      return DECISION_COLUMNS;
    default:
      return REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION;
  }
};

export const selectFilterDefinitionsBySearchGroup = (searchGroup) => {
  switch (searchGroup) {
    case SEARCH_GROUP.MARK_SEARCH:
      return MARK_FILTERS;
    case SEARCH_GROUP.GEO_INDICATION_SEARCH:
      return GEO_INDICATION_FILTERS;
    case SEARCH_GROUP.PATENT_SEARCH:
      return PATENTS_FILTERS;
    case SEARCH_GROUP.DESIGN_SEARCH:
      return DESIGN_FILTERS;
    case SEARCH_GROUP.EU_PATENT_SEARCH:
      return EU_PATENT_FILTERS;
    case SEARCH_GROUP.UTILITY_MODEL_SEARCH:
      return UTILITY_MODEL_FILTERS;
    case SEARCH_GROUP.PLANT_BREED_SEARCH:
      return PLANTS_BREEDS_FILTERS;
    case SEARCH_GROUP.SPC_SEARCH:
      return SPC_FILTERS;
    case SEARCH_GROUP.COMBINED_SEARCH:
      return COMBINE_SEARCH_FILTER;
    case SEARCH_GROUP.AGENTS_SEARCH:
      return AGENT_FILTERS;
    case SEARCH_GROUP.PARTNERSHIP_SEARCH:
      return PARTNERSHIP_FILTERS;
    case SEARCH_GROUP.DECISION_SEARCH:
      return DECISION_FILTERS;
  }
};

export const REGISTERS_SEARCH_CONFIG = {
  [SEARCH_GROUP.MARK_SEARCH]: {
    hasSearchStarted: false,
    filtersInitialValues: markFiltersInitialValues,
    selectedFilters: [
      { id: MARK_FILTERS.objectName.id, required: true, active: true },
      { id: MARK_FILTERS.filingNumber.id, required: false, active: false },
      { id: MARK_FILTERS.filingDate.id, required: false, active: false },
      { id: MARK_FILTERS.registrationNumber.id, required: false, active: false },
      { id: MARK_FILTERS.status.id, required: false, active: false },
      { id: MARK_FILTERS.applicantOwner.id, required: false, active: false },
      { id: MARK_FILTERS.representatives.id, required: false, active: false },
      { id: MARK_FILTERS.publications.id, required: false, active: false },
      { id: MARK_FILTERS.priority.id, required: false, active: false },
      { id: MARK_FILTERS.expirationDate.id, required: false, active: false },
      { id: MARK_FILTERS.viennaClasses.id, required: false, active: false },
      { id: MARK_FILTERS.niceClasses.id, required: false, active: false },
      { id: MARK_FILTERS.markKind.id, required: false, active: false },
      { id: MARK_FILTERS.objectSubtype.id, required: false, active: false },
    ],
    selectedTableColumns: [
      { id: REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION.objectId.id, active: true },
      { id: REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION.filingDate.id, active: true },
      { id: REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION.title.id, active: true },
      { id: REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION.registrationNumber.id, active: true },
      { id: REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION.statusId.id, active: true },
      { id: REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION.mainOwner.id, active: true },
      { id: REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION.image.id, active: true },
      { id: REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION.niceClasses.id, active: true },
    ],
  },
  [SEARCH_GROUP.PATENT_SEARCH]: {
    hasSearchStarted: false,
    filtersInitialValues: patentFiltersInitialValues,
    selectedFilters: [
      { id: PATENTS_FILTERS.objectName.id, required: true, active: true },
      { id: PATENTS_FILTERS.filingNumber.id, required: false, active: false },
      { id: PATENTS_FILTERS.filingDate.id, required: false, active: false },
      { id: PATENTS_FILTERS.protectedNumber.id, required: false, active: false },
      { id: PATENTS_FILTERS.status.id, required: false, active: false },
      { id: PATENTS_FILTERS.applicantOwner.id, required: false, active: false },
      { id: PATENTS_FILTERS.representatives.id, required: false, active: false },
      { id: PATENTS_FILTERS.publications.id, required: false, active: false },
      { id: PATENTS_FILTERS.priority.id, required: false, active: false },
      { id: PATENTS_FILTERS.expirationDate.id, required: false, active: false },
      { id: PATENTS_FILTERS.abstract.id, required: false, active: false },
      { id: PATENTS_FILTERS.inventor.id, required: false, active: false },
      { id: PATENTS_FILTERS.ipcClasses.id, required: false, active: false },
      { id: PATENTS_FILTERS.cpcClasses.id, required: false, active: false },
    ],
    selectedTableColumns: [
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.objectId.id, active: true },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.filingDate.id, active: false },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.title.id, active: false },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.registrationNumber.id, active: false },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.statusId.id, active: false },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.mainOwner.id, active: false },
    ],
  },
  [SEARCH_GROUP.DESIGN_SEARCH]: {
    hasSearchStarted: false,
    filtersInitialValues: designFiltersInitialValues,
    selectedFilters: [
      { id: DESIGN_FILTERS.mainDesignName.id, required: true, active: true },
      { id: DESIGN_FILTERS.filingNumber.id, required: false, active: false },
      { id: DESIGN_FILTERS.filingDate.id, required: false, active: false },
      { id: DESIGN_FILTERS.registrationNumber.id, required: false, active: false },
      { id: DESIGN_FILTERS.status.id, required: false, active: false },
      { id: DESIGN_FILTERS.applicantOwner.id, required: false, active: false },
      { id: DESIGN_FILTERS.representatives.id, required: false, active: false },
      { id: DESIGN_FILTERS.publications.id, required: false, active: false },
      { id: DESIGN_FILTERS.priority.id, required: false, active: false },
      { id: DESIGN_FILTERS.expirationDate.id, required: false, active: false },
      { id: DESIGN_FILTERS.author.id, required: false, active: false },
      { id: DESIGN_FILTERS.locarnoClasses.id, required: false, active: false },
      { id: DESIGN_FILTERS.singleDesignName.id, required: false, active: false },
      { id: DESIGN_FILTERS.singleDesignVerbalElement.id, required: false, active: false },
    ],
    selectedTableColumns: [
      { id: REGISTERS_TABLE_DESIGN_COLUMNS_DEFINITION.objectId.id, active: true },
      { id: REGISTERS_TABLE_DESIGN_COLUMNS_DEFINITION.filingDate.id, active: false },
      { id: REGISTERS_TABLE_DESIGN_COLUMNS_DEFINITION.title.id, active: false },
      { id: REGISTERS_TABLE_DESIGN_COLUMNS_DEFINITION.registrationNumber.id, active: false },
      { id: REGISTERS_TABLE_DESIGN_COLUMNS_DEFINITION.statusId.id, active: false },
      { id: REGISTERS_TABLE_DESIGN_COLUMNS_DEFINITION.mainOwner.id, active: false },
    ],
  },
  [SEARCH_GROUP.EU_PATENT_SEARCH]: {
    hasSearchStarted: false,
    filtersInitialValues: euPatentFiltersInitialValues,
    selectedFilters: [
      { id: EU_PATENT_FILTERS.objectName.id, required: true, active: true },
      { id: EU_PATENT_FILTERS.filingNumber.id, required: false, active: false },
      { id: EU_PATENT_FILTERS.filingDate.id, required: false, active: false },
      { id: EU_PATENT_FILTERS.protectedNumber.id, required: false, active: false },
      { id: EU_PATENT_FILTERS.status.id, required: false, active: false },
      { id: EU_PATENT_FILTERS.applicantOwner.id, required: false, active: false },
      { id: EU_PATENT_FILTERS.representatives.id, required: false, active: false },
      { id: EU_PATENT_FILTERS.publications.id, required: false, active: false },
      { id: EU_PATENT_FILTERS.priority.id, required: false, active: false },
      { id: EU_PATENT_FILTERS.expirationDate.id, required: false, active: false },
      { id: EU_PATENT_FILTERS.abstract.id, required: false, active: false },
      { id: EU_PATENT_FILTERS.inventor.id, required: false, active: false },
      { id: EU_PATENT_FILTERS.ipcClasses.id, required: false, active: false },
      { id: EU_PATENT_FILTERS.cpcClasses.id, required: false, active: false },
    ],
    selectedTableColumns: [
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.objectId.id, active: true },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.filingDate.id, active: false },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.title.id, active: false },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.registrationNumber.id, active: false },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.statusId.id, active: false },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.mainOwner.id, active: false },
    ],
  },
  [SEARCH_GROUP.SPC_SEARCH]: {
    hasSearchStarted: false,
    filtersInitialValues: spcFiltersInitialValues,
    selectedFilters: [
      { id: SPC_FILTERS.objectName.id, required: true, active: true },
      { id: SPC_FILTERS.filingNumber.id, required: false, active: false },
      { id: SPC_FILTERS.filingDate.id, required: false, active: false },
      { id: SPC_FILTERS.protectedNumber.id, required: false, active: false },
      { id: SPC_FILTERS.status.id, required: false, active: false },
      { id: SPC_FILTERS.applicantOwner.id, required: false, active: false },
      { id: SPC_FILTERS.representatives.id, required: false, active: false },
      { id: SPC_FILTERS.publications.id, required: false, active: false },
      { id: SPC_FILTERS.priority.id, required: false, active: false },
      { id: SPC_FILTERS.abstract.id, required: false, active: false },
      { id: SPC_FILTERS.inventor.id, required: false, active: false },
      { id: SPC_FILTERS.ipcClasses.id, required: false, active: false },
      { id: SPC_FILTERS.cpcClasses.id, required: false, active: false },
    ],
    selectedTableColumns: [
      { id: REGISTERS_TABLE_SPC_COLUMNS_DEFINITION.objectId.id, active: true },
      { id: REGISTERS_TABLE_SPC_COLUMNS_DEFINITION.filingDate.id, active: false },
      { id: REGISTERS_TABLE_SPC_COLUMNS_DEFINITION.title.id, active: false },
      { id: REGISTERS_TABLE_SPC_COLUMNS_DEFINITION.registrationNumber.id, active: false },
      { id: REGISTERS_TABLE_SPC_COLUMNS_DEFINITION.statusId.id, active: false },
      { id: REGISTERS_TABLE_SPC_COLUMNS_DEFINITION.mainOwner.id, active: false },
    ],
  },
  [SEARCH_GROUP.UTILITY_MODEL_SEARCH]: {
    hasSearchStarted: false,
    filtersInitialValues: utilityModelFiltersInitialValues,
    selectedFilters: [
      { id: UTILITY_MODEL_FILTERS.objectName.id, required: true, active: true },
      { id: UTILITY_MODEL_FILTERS.filingNumber.id, required: false, active: false },
      { id: UTILITY_MODEL_FILTERS.filingDate.id, required: false, active: false },
      { id: UTILITY_MODEL_FILTERS.protectedNumber.id, required: false, active: false },
      { id: UTILITY_MODEL_FILTERS.status.id, required: false, active: false },
      { id: UTILITY_MODEL_FILTERS.applicantOwner.id, required: false, active: false },
      { id: UTILITY_MODEL_FILTERS.representatives.id, required: false, active: false },
      { id: UTILITY_MODEL_FILTERS.publications.id, required: false, active: false },
      { id: UTILITY_MODEL_FILTERS.priority.id, required: false, active: false },
      { id: UTILITY_MODEL_FILTERS.expirationDate.id, required: false, active: false },
      { id: UTILITY_MODEL_FILTERS.abstract.id, required: false, active: false },
      { id: UTILITY_MODEL_FILTERS.inventor.id, required: false, active: false },
      { id: UTILITY_MODEL_FILTERS.ipcClasses.id, required: false, active: false },
      { id: UTILITY_MODEL_FILTERS.cpcClasses.id, required: false, active: false },
    ],
    selectedTableColumns: [
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.objectId.id, active: true },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.filingDate.id, active: false },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.title.id, active: false },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.registrationNumber.id, active: false },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.statusId.id, active: false },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.mainOwner.id, active: false },
    ],
  },
  [SEARCH_GROUP.PLANT_BREED_SEARCH]: {
    hasSearchStarted: false,
    filtersInitialValues: plantBreedFiltersInitialValues,
    selectedFilters: [
      { id: PLANTS_BREEDS_FILTERS.objectName.id, required: true, active: true },
      { id: PLANTS_BREEDS_FILTERS.filingNumber.id, required: false, active: false },
      { id: PLANTS_BREEDS_FILTERS.filingDate.id, required: false, active: false },
      { id: PLANTS_BREEDS_FILTERS.protectedNumber.id, required: false, active: false },
      { id: PLANTS_BREEDS_FILTERS.status.id, required: false, active: false },
      { id: PLANTS_BREEDS_FILTERS.applicantOwner.id, required: false, active: false },
      { id: PLANTS_BREEDS_FILTERS.representatives.id, required: false, active: false },
      { id: PLANTS_BREEDS_FILTERS.publications.id, required: false, active: false },
      { id: PLANTS_BREEDS_FILTERS.priority.id, required: false, active: false },
      { id: PLANTS_BREEDS_FILTERS.origin.id, required: false, active: false },
      { id: PLANTS_BREEDS_FILTERS.author.id, required: false, active: false },
      { id: PLANTS_BREEDS_FILTERS.latinClassification.id, required: false, active: false },
      { id: PLANTS_BREEDS_FILTERS.bgClassification.id, required: false, active: false },
      { id: PLANTS_BREEDS_FILTERS.objectSubtype.id, required: true, active: true },
    ],
    selectedTableColumns: [
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.objectId.id, active: true },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.filingDate.id, active: false },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.title.id, active: false },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.registrationNumber.id, active: false },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.statusId.id, active: false },
      { id: REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION.mainOwner.id, active: false },
    ],
  },
  [SEARCH_GROUP.GEO_INDICATION_SEARCH]: {
    hasSearchStarted: false,
    filtersInitialValues: geoIndicationsFiltersInitialValues,
    selectedFilters: [
      { id: GEO_INDICATION_FILTERS.objectName.id, required: true, active: true },
      { id: GEO_INDICATION_FILTERS.filingNumber.id, required: false, active: false },
      { id: GEO_INDICATION_FILTERS.filingDate.id, required: false, active: false },
      { id: GEO_INDICATION_FILTERS.registrationNumber.id, required: false, active: false },
      { id: GEO_INDICATION_FILTERS.status.id, required: false, active: false },
      { id: GEO_INDICATION_FILTERS.applicantOwner.id, required: false, active: false },
      { id: GEO_INDICATION_FILTERS.representatives.id, required: false, active: false },
      { id: GEO_INDICATION_FILTERS.publications.id, required: false, active: false },
      { id: GEO_INDICATION_FILTERS.priority.id, required: false, active: false },
      { id: GEO_INDICATION_FILTERS.viennaClasses.id, required: false, active: false },
      { id: GEO_INDICATION_FILTERS.niceClasses.id, required: false, active: false },
      { id: GEO_INDICATION_FILTERS.objectSubtype.id, required: false, active: false },
    ],
    selectedTableColumns: [
      { id: REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION.objectId.id, active: true },
      { id: REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION.filingDate.id, active: false },
      { id: REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION.title.id, active: false },
      { id: REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION.registrationNumber.id, active: false },
      { id: REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION.statusId.id, active: false },
      { id: REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION.mainOwner.id, active: false },
    ],
  },
  [SEARCH_GROUP.COMBINED_SEARCH]: {
    hasSearchStarted: false,
    filtersInitialValues: combinedFiltersInitialValues,
    selectedFilters: [
      { id: COMBINE_SEARCH_FILTER.objectName.id, required: true, active: true },
      { id: COMBINE_SEARCH_FILTER.filingNumber.id, required: false, active: false },
      { id: COMBINE_SEARCH_FILTER.filingDate.id, required: false, active: false },
      { id: COMBINE_SEARCH_FILTER.registrationNumber.id, required: false, active: false },
      { id: COMBINE_SEARCH_FILTER.status.id, required: false, active: false },
      { id: COMBINE_SEARCH_FILTER.applicantOwner.id, required: false, active: false },
      { id: COMBINE_SEARCH_FILTER.representatives.id, required: false, active: false },
      { id: COMBINE_SEARCH_FILTER.publications.id, required: false, active: false },
      { id: COMBINE_SEARCH_FILTER.priority.id, required: false, active: false },
      { id: COMBINE_SEARCH_FILTER.expirationDate.id, required: false, active: false },
      { id: COMBINE_SEARCH_FILTER.objectRange.id, required: true, active: true },
    ],
    selectedTableColumns: [
      { id: REGISTERS_TABLE_COMBINED_COLUMNS_DEFINITION.objectId.id, active: true },
      { id: REGISTERS_TABLE_COMBINED_COLUMNS_DEFINITION.filingDate.id, active: false },
      { id: REGISTERS_TABLE_COMBINED_COLUMNS_DEFINITION.title.id, active: false },
      { id: REGISTERS_TABLE_COMBINED_COLUMNS_DEFINITION.registrationNumber.id, active: false },
      { id: REGISTERS_TABLE_COMBINED_COLUMNS_DEFINITION.statusId.id, active: false },
      { id: REGISTERS_TABLE_COMBINED_COLUMNS_DEFINITION.mainOwner.id, active: false },
    ],
  },
  [SEARCH_GROUP.AGENTS_SEARCH]: {
    hasSearchStarted: true,
    filtersInitialValues: agentsFiltersInitialValues,
    selectedFilters: [
      { id: AGENT_FILTERS.agentName.id, required: true, active: true },
      { id: AGENT_FILTERS.ipoArea.id, required: false, active: true },
      { id: AGENT_FILTERS.agentCode.id, required: false, active: true },
      { id: AGENT_FILTERS.status.id, required: false, active: true },
      { id: AGENT_FILTERS.city.id, required: false, active: true },
      { id: AGENT_FILTERS.agentSpeciality.id, required: false, active: true },
    ],
    selectedTableColumns: [
      { id: REGISTERS_TABLE_REPRESENTATIVE_LIKE_COLUMNS_DEFINITION.agentName.id, active: true },
      { id: REGISTERS_TABLE_REPRESENTATIVE_LIKE_COLUMNS_DEFINITION.agentCode.id, active: true },
      { id: REGISTERS_TABLE_REPRESENTATIVE_LIKE_COLUMNS_DEFINITION.ipoArea.id, active: true },
      { id: REGISTERS_TABLE_REPRESENTATIVE_LIKE_COLUMNS_DEFINITION.agentStatus.id, active: true },
      { id: REGISTERS_TABLE_REPRESENTATIVE_LIKE_COLUMNS_DEFINITION.agentSpeciality.id, active: true },
    ],
  },
  [SEARCH_GROUP.PARTNERSHIP_SEARCH]: {
    hasSearchStarted: true,
    filtersInitialValues: partnershipsFiltersInitialValues,
    selectedFilters: [
      { id: PARTNERSHIP_FILTERS.partnershipName.id, required: true, active: true },
      { id: PARTNERSHIP_FILTERS.ipoArea.id, required: false, active: true },
      { id: PARTNERSHIP_FILTERS.agentCode.id, required: false, active: true },
      { id: PARTNERSHIP_FILTERS.status.id, required: false, active: true },
      { id: PARTNERSHIP_FILTERS.city.id, required: false, active: true },
    ],
    selectedTableColumns: [
      { id: REGISTERS_TABLE_REPRESENTATIVE_LIKE_COLUMNS_DEFINITION.partnershipName.id, active: true },
      { id: REGISTERS_TABLE_REPRESENTATIVE_LIKE_COLUMNS_DEFINITION.agentCode.id, active: true },
      { id: REGISTERS_TABLE_REPRESENTATIVE_LIKE_COLUMNS_DEFINITION.ipoArea.id, active: true },
      { id: REGISTERS_TABLE_REPRESENTATIVE_LIKE_COLUMNS_DEFINITION.agentStatus.id, active: true },
      { id: REGISTERS_TABLE_REPRESENTATIVE_LIKE_COLUMNS_DEFINITION.representativeType.id, active: true },
    ],
  },
  [SEARCH_GROUP.DECISION_SEARCH]: {
    hasSearchStarted: false,
    filtersInitialValues: decisionFiltersInitialValues,
    selectedFilters: [
      { id: DECISION_FILTERS.objectType.id, required: false, active: true },
      { id: DECISION_FILTERS.objectId.id, required: false, active: false },
      { id: DECISION_FILTERS.documentDate.id, required: false, active: false },
      { id: DECISION_FILTERS.documentNumber.id, required: false, active: false },
      { id: DECISION_FILTERS.documentType.id, required: false, active: false },
      { id: DECISION_FILTERS.legalGroundTypes.id, required: false, active: false },
    ],
    selectedTableColumns: [
      { id: DECISION_COLUMNS.objectId.id, active: true },
      { id: DECISION_COLUMNS.title.id, active: true },
      { id: DECISION_COLUMNS.documentType.id, active: true },
      { id: DECISION_COLUMNS.documentNumber.id, active: true },
      { id: DECISION_COLUMNS.documentDate.id, active: true },
    ],
  },
};
