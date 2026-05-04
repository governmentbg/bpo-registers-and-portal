import NameSearchWithTypeFilter from "./NameSearchWithTypeFilter";
import React from "react";

const SingleDesignVerbalElementSearchFilter = () => {
  return (
    <NameSearchWithTypeFilter
      nameField={"singleDesignVerbalElement"}
      nameFieldLabelCode={"l.searchFilter.single.design.verbal.element"}
      searchTypeField={"singleDesignVerbalElementSearchType"}
      sm={12}
      md={12}
    />
  );
};
export default SingleDesignVerbalElementSearchFilter;
