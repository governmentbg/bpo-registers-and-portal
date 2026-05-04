import React from "react";
import AutocompleteFromToFilter from "./AutocompleteFromToFilter";
import { autocompleteRegistrationNumbers } from "../../../../../../axios/api/services";
import { useWatch } from "react-hook-form";

const RegistrationNumberSearchFilter = ({ label = "l.searchFilter.registrationNumber" }) => {
  const objectRange = useWatch({ name: "objectRange" });

  return (
    <AutocompleteFromToFilter
      label={label}
      from={`registrationNumber.from`}
      to={`registrationNumber.to`}
      fetchDataFn={autocompleteRegistrationNumbers}
      additionalParams={{ objectTypes: objectRange }}
    />
  );
};
export default RegistrationNumberSearchFilter;
