import { FiltersDefinition } from "../../../../../../types/registers/search/filter/filtersDefinition";
import { UNIVERSAL_FILTERS } from "./universalFilters";
import { RegisterType } from "../../../../../../utils/constants";

export const COMMON_PATENT_LIKE_FILTERS: FiltersDefinition = {
  ...UNIVERSAL_FILTERS,
  //override
  objectName: {
    id: "objectName",
    label: `l.searchFilter.name.${RegisterType.PATENT}`,
  },
  filingNumber: {
    id: "filingNumber",
    label: `l.searchFilter.filingNumber.${RegisterType.PATENT}`,
  },
  filingDate: {
    id: "filingDate",
    label: `l.searchFilter.filingDate.${RegisterType.PATENT}`,
  },
  protectedNumber: {
    id: "protectedNumber",
    label: `l.searchFilter.protectedNumber.${RegisterType.PATENT}`,
  },
  status: {
    id: "status",
    label: `l.searchFilter.status.${RegisterType.PATENT}`,
  },
  applicantOwner: {
    id: "applicantOwner",
    label: `l.searchFilter.applicantOwner.${RegisterType.PATENT}`,
  },
  representatives: {
    id: "representatives",
    label: `l.searchFilter.representative.${RegisterType.PATENT}`,
  },
  publications: {
    id: "publications",
    label: `l.searchFilter.publications.${RegisterType.PATENT}`,
  },
  priority: {
    id: "priority",
    label: `l.searchFilter.priority.${RegisterType.PATENT}`,
  },
  expirationDate: {
    id: "expirationDate",
    label: `l.searchFilter.expirationDate.${RegisterType.PATENT}`,
  },
  // custom
  ipcClasses: {
    id: "ipcClasses",
    label: "l.searchFilter.ipcClass",
  },
  cpcClasses: {
    id: "cpcClasses",
    label: "l.searchFilter.cpcClass",
  },
  inventor: {
    id: "inventor",
    label: `l.searchFilter.inventor.${RegisterType.PATENT}`,
  },
  abstract: {
    id: "abstract",
    label: `l.searchFilter.abstract.${RegisterType.PATENT}`,
  },
};
export const PATENTS_FILTERS: FiltersDefinition = { ...COMMON_PATENT_LIKE_FILTERS };
export const EU_PATENT_FILTERS: FiltersDefinition = {
  ...COMMON_PATENT_LIKE_FILTERS,
  filingNumber: {
    id: "filingNumber",
    label: `l.searchFilter.filingNumber.${RegisterType.EU_PATENT}`,
  },
  filingDate: {
    id: "filingDate",
    label: `l.searchFilter.filingDate.${RegisterType.EU_PATENT}`,
  },
  publications: {
    id: "publications",
    label: `l.searchFilter.publications.${RegisterType.EU_PATENT}`,
  },
};
export const UTILITY_MODEL_FILTERS: FiltersDefinition = { ...COMMON_PATENT_LIKE_FILTERS };
export const PLANTS_BREEDS_FILTERS: FiltersDefinition = {
  ...COMMON_PATENT_LIKE_FILTERS,
  latinClassification: {
    id: "latinClassification",
    label: "l.searchFilter.latinClassification",
  },
  bgClassification: {
    id: "bgClassification",
    label: "l.searchFilter.bgClassification",
  },
  origin: {
    id: "origin",
    label: "l.searchFilter.origin",
  },
  author: {
    id: "author",
    label: "l.searchFilter.author",
  },
};
export const SPC_FILTERS: FiltersDefinition = {
  ...COMMON_PATENT_LIKE_FILTERS,
  objectName: {
    id: "objectName",
    label: `l.searchFilter.name.${RegisterType.SPC}`,
  },
  abstract: {
    id: "abstract",
    label: `l.searchFilter.abstract.${RegisterType.SPC}`,
  },
};
export const DESIGN_FILTERS: FiltersDefinition = {
  ...COMMON_PATENT_LIKE_FILTERS,
  mainDesignName: {
    id: "mainDesignName",
    label: `l.searchFilter.name.${RegisterType.DESIGN}`,
  },
  singleDesignName: {
    id: "singleDesignName",
    label: `l.searchFilter.single.design.name`,
  },
  singleDesignVerbalElement: {
    id: "singleDesignVerbalElement",
    label: `l.searchFilter.single.design.verbal.element`,
  },
  locarnoClasses: {
    id: "locarnoClasses",
    label: "l.searchFilter.locarnoClasses",
  },
  author: {
    id: "author",
    label: `l.searchFilter.author.${RegisterType.DESIGN}`,
  },
  registrationNumber: {
    id: "registrationNumber",
    label: `l.searchFilter.registrationNumber.${RegisterType.DESIGN}`,
  },
  expirationDate: {
    id: "expirationDate",
    label: `l.searchFilter.expirationDate.${RegisterType.DESIGN}`,
  },
};
