import React, { Fragment } from "react";
import useSearchSelectedFilters from "../../../../../../hooks/registers/search/filter/useSearchSelectedFilters";
import { SEARCH_GROUP } from "../../../../../../config/registers/search/registersSearchConfig";
import { GridContainer } from "@duosoftbg/bpo-components";
import FilterButtons from "../../common/filterButtons/FilterButtons";
import { PATENT_FILTER_DEFINITION_TO_COMPONENT } from "../../../../../../config/registers/search/filters/filterDefinitionToComponent/PatentFilterDefinitionToComponent";
import { renderFilter } from "../../../../../../utils/filters";

const PatentFilters = ({ control }) => {
  let searchGroup = SEARCH_GROUP.PATENT_SEARCH;
  let searchFilters = useSearchSelectedFilters(searchGroup);
  return (
    <>
      {PATENT_FILTER_DEFINITION_TO_COMPONENT.map((definitionToComponent) => (
        <Fragment key={definitionToComponent.id}>
          {renderFilter({
            searchGroup: searchGroup,
            filterId: definitionToComponent.id,
            searchFilters,
            component: definitionToComponent.component,
          })}
        </Fragment>
      ))}
      <GridContainer>
        <FilterButtons control={control}></FilterButtons>
      </GridContainer>
    </>
  );
};
export default PatentFilters;
