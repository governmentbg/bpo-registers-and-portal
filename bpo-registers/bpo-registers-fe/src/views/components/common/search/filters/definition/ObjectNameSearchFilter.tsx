import NameSearchWithTypeFilter from "./NameSearchWithTypeFilter";
import React from "react";

const ObjectNameSearchFilter = ({ label = "l.searchFilter.name" }) => {
  return (
    <NameSearchWithTypeFilter
      nameField={"objectName.text"}
      nameFieldLabelCode={label}
      searchTypeField={"objectName.searchType"}
      sm={12}
      md={12}
    />
  );
};
export default ObjectNameSearchFilter;
