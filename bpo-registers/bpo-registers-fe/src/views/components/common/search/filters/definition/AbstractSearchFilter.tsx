import NameSearchWithTypeFilter from "./NameSearchWithTypeFilter";
import React from "react";

const AbstractSearchFilter = ({ label = "l.searchFilter.abstract" }) => {
  return (
    <NameSearchWithTypeFilter
      nameField={"abstract.text"}
      nameFieldLabelCode={label}
      searchTypeField={"abstract.searchType"}
      sm={12}
      md={12}
    />
  );
};
export default AbstractSearchFilter;
