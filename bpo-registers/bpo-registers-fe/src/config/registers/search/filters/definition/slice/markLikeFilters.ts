import { FiltersDefinition } from "../../../../../../types/registers/search/filter/filtersDefinition";
import { UNIVERSAL_FILTERS } from "./universalFilters";
import { RegisterType } from "../../../../../../utils/constants";

export const COMMON_MARK_LIKE_FILTERS: FiltersDefinition = {
  ...UNIVERSAL_FILTERS,
  objectName: {
    id: "objectName",
    label: `l.searchFilter.name.${RegisterType.MARK}`,
  },
  registrationNumber: {
    id: "registrationNumber",
    label: `l.searchFilter.registrationNumber.${RegisterType.MARK}`,
  },
  filingDate: {
    id: "filingDate",
    label: `l.searchFilter.filingDate.${RegisterType.MARK}`,
  },
  filingNumber: {
    id: "filingNumber",
    label: `l.searchFilter.filingNumber.${RegisterType.MARK}`,
  },
  expirationDate: {
    id: "expirationDate",
    label: `l.searchFilter.expirationDate.${RegisterType.MARK}`,
  },
  applicantOwner: {
    id: "applicantOwner",
    label: `l.searchFilter.applicantOwner.${RegisterType.MARK}`,
  },
  representatives: {
    id: "representatives",
    label: `l.searchFilter.representative.${RegisterType.MARK}`,
  },
  publications: {
    id: "publications",
    label: `l.searchFilter.publications.${RegisterType.MARK}`,
  },
  niceClasses: {
    id: "niceClasses",
    label: "l.searchFilter.niceClasses",
  },
  viennaClasses: {
    id: "viennaClasses",
    label: "l.searchFilter.viennaClasses",
  },
  priority: {
    id: "priority",
    label: `l.searchFilter.priority.${RegisterType.MARK}`,
  },
};

export const MARK_FILTERS: FiltersDefinition = {
  ...COMMON_MARK_LIKE_FILTERS,
  objectSubtype: {
    id: "objectSubtype",
    label: `l.searchFilter.objectSubType.${RegisterType.MARK}`,
  },
  markKind: {
    id: "markKind",
    label: "l.searchFilter.markKind",
  },
  markType: {
    id: "markType",
    label: "l.searchFilter.markType",
  },
};

export const GEO_INDICATION_FILTERS: FiltersDefinition = {
  ...COMMON_MARK_LIKE_FILTERS,
  objectSubtype: {
    id: "objectSubtype",
    label: `l.searchFilter.objectSubType.${RegisterType.GEO_INDICATION}`,
  },
};
