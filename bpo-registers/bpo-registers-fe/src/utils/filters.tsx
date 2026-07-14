import { FormSection, GridContainer } from "@duosoftbg/bpo-components";
import React from "react";
import { selectFilterDefinitionsBySearchGroup } from "../config/registers/search/registersSearchConfig";

export const renderFilter = ({ searchGroup, filterId, searchFilters, component, withGrid = true }) => {
  let filter = searchFilters.find((item) => item.id === filterId);
  if (filter?.value) {
    if (withGrid) {
      return <GridFilter searchGroup={searchGroup} filterId={filterId} component={component} />;
    }

    return component;
  }
  return null;
};

const GridFilter = ({ searchGroup, filterId, component }) => {
  return (
    <FormSection label={selectFilterDefinitionsBySearchGroup(searchGroup)[filterId].label}>
      <GridContainer spacing={4} mt={0}>
        {component}
      </GridContainer>
    </FormSection>
  );
};
