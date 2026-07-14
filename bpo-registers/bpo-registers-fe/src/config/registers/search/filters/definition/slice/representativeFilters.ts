import { FiltersDefinition } from "../../../../../../types/registers/search/filter/filtersDefinition";
import { UNIVERSAL_FILTERS } from "./universalFilters";

const COMMON_REPRESENTATIVE_FILTERS: FiltersDefinition = {
  ...UNIVERSAL_FILTERS,
  ipoArea: {
    id: "ipoArea",
    label: "l.searchFilter.ipoArea",
  },
  agentCode: {
    id: "agentCode",
    label: "l.searchFilter.agentCode",
  },
  agentSpeciality: {
    id: "agentSpeciality",
    label: "l.searchFilter.agentSpeciality",
  },
};
export const AGENT_FILTERS: FiltersDefinition = {
  ...COMMON_REPRESENTATIVE_FILTERS,
  agentName: {
    id: "agentName",
    label: "l.searchFilter.agentName",
  },
};

export const PARTNERSHIP_FILTERS: FiltersDefinition = {
  ...COMMON_REPRESENTATIVE_FILTERS,
  partnershipName: {
    id: "partnershipName",
    label: "l.searchFilter.name",
  },
};
