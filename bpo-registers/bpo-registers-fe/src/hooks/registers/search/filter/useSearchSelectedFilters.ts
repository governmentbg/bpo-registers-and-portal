import { convertObjectToArray } from "@duosoftbg/bpo-components";
import { useMemo } from "react";
import { REGISTERS_SEARCH_CONFIG } from "../../../../config/registers/search/registersSearchConfig";
import useAppSelector from "../../../redux/base/useAppSelector";

export const sortFilters = (searchGroup, filters) => {
  let result = [];
  if (filters) {
    REGISTERS_SEARCH_CONFIG[searchGroup].selectedFilters.forEach((item) => {
      let filter = filters.find((e) => e.id === item.id);
      if (filter) {
        result.push(filter);
      }
    });
  }
  return result;
};

const useSearchSelectedFilters = (searchGroup) => {
  const searchFilters = useAppSelector((state) => state.SearchData.registersSearchData[searchGroup].selectedFilters);
  return useMemo(() => sortFilters(searchGroup, convertObjectToArray(searchFilters)), [searchGroup, searchFilters]);
};

export default useSearchSelectedFilters;
