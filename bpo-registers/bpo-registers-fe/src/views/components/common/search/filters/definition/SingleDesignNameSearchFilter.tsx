import NameSearchWithTypeFilter from "./NameSearchWithTypeFilter";
import React from "react";

const SingleDesignNameSearchFilter = () => {
  return (
    <NameSearchWithTypeFilter
      nameField={"singleDesignName"}
      nameFieldLabelCode={"l.product.name"}
      searchTypeField={"singleDesignNameSearchType"}
      sm={12}
      md={12}
    />
  );
};
export default SingleDesignNameSearchFilter;
