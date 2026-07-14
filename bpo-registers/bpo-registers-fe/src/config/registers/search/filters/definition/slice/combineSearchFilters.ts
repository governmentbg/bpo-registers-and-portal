import { FiltersDefinition } from "../../../../../../types/registers/search/filter/filtersDefinition";
import { UNIVERSAL_FILTERS } from "./universalFilters";

export const COMBINE_SEARCH_FILTER: FiltersDefinition = {
  ...UNIVERSAL_FILTERS,
  registrationNumber: {
    id: "registrationNumber",
    label: "l.searchFilter.registrationNumber",
  },
  objectRange: {
    id: "objectRange",
    label: "l.searchFilter.objectRange",
  },
};
