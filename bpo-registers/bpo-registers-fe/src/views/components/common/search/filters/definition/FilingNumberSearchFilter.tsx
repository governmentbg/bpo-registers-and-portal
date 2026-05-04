import { autocompleteFilingNumbers } from "../../../../../../axios/api/services";
import AutocompleteFromToFilter from "./AutocompleteFromToFilter";
import React from "react";
import { useWatch } from "react-hook-form";

const FilingNumberSearchFilter = () => {
  const objectRange = useWatch({ name: "objectRange" });

  return (
    <AutocompleteFromToFilter
      label={"l.searchFilter.filingNumber"}
      from={`filingNumber.from`}
      to={`filingNumber.to`}
      fetchDataFn={autocompleteFilingNumbers}
      additionalParams={{ objectTypes: objectRange }}
    />
  );
};
export default FilingNumberSearchFilter;
