import React from "react";
import { AsyncAutocompleteFormField, GridItem } from "@duosoftbg/bpo-components";
import { getAllBgClassifications } from "../../../../../../axios/api/services";

const BgClassificationSearchFilter = () => {
  return (
    <GridItem xs={12} sm={12} md={12} lg={12}>
      <AsyncAutocompleteFormField
        label={"l.searchFilter.bgClassification"}
        asyncFn={getAllBgClassifications}
        loadInitial={true}
        reloadOnComponentEvent={false}
        fieldName={"bgClassification"}
      />
    </GridItem>
  );
};
export default BgClassificationSearchFilter;
