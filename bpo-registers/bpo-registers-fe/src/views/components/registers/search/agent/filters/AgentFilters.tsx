import React, { Fragment } from "react";
import useSearchSelectedFilters from "../../../../../../hooks/registers/search/filter/useSearchSelectedFilters";
import { SEARCH_GROUP } from "../../../../../../config/registers/search/registersSearchConfig";
import { GridContainer } from "@duosoftbg/bpo-components";
import FilterButtons from "../../common/filterButtons/FilterButtons";
import { renderFilter } from "../../../../../../utils/filters";
import { AGENT_FILTER_DEFINITION_TO_COMPONENT } from "../../../../../../config/registers/search/filters/filterDefinitionToComponent/AgentFilterDefinitionToComponent";

const AgentFilters = ({ control }) => {
  let searchGroup = SEARCH_GROUP.AGENTS_SEARCH;
  let searchFilters = useSearchSelectedFilters(searchGroup);
  return (
    <>
      <GridContainer mt={0} spacing={4}>
        {AGENT_FILTER_DEFINITION_TO_COMPONENT.map((definitionToComponent) => (
          <Fragment key={definitionToComponent.id}>
            {renderFilter({
              searchGroup: searchGroup,
              filterId: definitionToComponent.id,
              searchFilters,
              component: definitionToComponent.component,
              withGrid: false,
            })}
          </Fragment>
        ))}
      </GridContainer>
      <GridContainer>
        <FilterButtons control={control}></FilterButtons>
      </GridContainer>
    </>
  );
};
export default AgentFilters;
