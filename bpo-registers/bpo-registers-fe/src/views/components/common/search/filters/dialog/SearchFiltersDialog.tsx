import { useEffect, useState } from "react";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import useSearchSelectedFilters from "../../../../../../hooks/registers/search/filter/useSearchSelectedFilters";
import { updateSelectedFiltersValue } from "../../../../../../store/redux/slice/SearchData/registersSearchData";
import SearchFiltersDialogContent from "./SearchFiltersDialogContent";
import {
  REGISTERS_SEARCH_CONFIG,
  selectFilterDefinitionsBySearchGroup,
} from "../../../../../../config/registers/search/registersSearchConfig";
import { useFormContext } from "react-hook-form";
import { deepCopy } from "@duosoftbg/bpo-components";
import { RegistersConfigName } from "../../../../../../utils/constants";

const SearchFiltersDialog = ({ searchGroup, title = "t.modal.searchFilters" }) => {
  const dispatch = useAppDispatch();
  const [filters, setFilters] = useState([]);
  const { setValue } = useFormContext();

  let searchFilters = useSearchSelectedFilters(searchGroup);

  useEffect(() => {
    setFilters(searchFilters);
  }, [searchFilters]);

  const handleChange = (event) => {
    const initialValue = REGISTERS_SEARCH_CONFIG[searchGroup].filtersInitialValues[event.target.name];
    if (initialValue === undefined) {
      console.error(
        `Cannot reset value for filter! [${searchGroup}].filtersInitialValues[${event.target.name}] is undefined !`
      );
    } else {
      setValue(event.target.name, deepCopy(initialValue));
    }

    dispatch(
      updateSelectedFiltersValue({
        group: searchGroup,
        name: event.target.name,
        value: event.target.checked,
      })
    );
  };

  return (
    <SearchFiltersDialogContent
      searchGroup={searchGroup}
      title={title}
      filters={filters}
      handleChange={handleChange}
      configName={RegistersConfigName.SELECTED_FILTERS}
      filtersDefinition={selectFilterDefinitionsBySearchGroup(searchGroup)}
      searchConfig={REGISTERS_SEARCH_CONFIG}
    />
  );
};
export default SearchFiltersDialog;
