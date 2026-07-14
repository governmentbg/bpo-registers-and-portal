import React from "react";
import { AsyncAutocompleteFormField, GridItem } from "@duosoftbg/bpo-components";
import { getAllLatinClassifications } from "../../../../../../axios/api/services";

const LatinClassificationSearchFilter = () => {
  return (
    <GridItem xs={12} sm={12} md={12} lg={12}>
      <AsyncAutocompleteFormField
        label={"l.searchFilter.latinClassification"}
        asyncFn={getAllLatinClassifications}
        loadInitial={true}
        reloadOnComponentEvent={false}
        fieldName={"latinClassification"}
      />
    </GridItem>
  );
};
export default LatinClassificationSearchFilter;
