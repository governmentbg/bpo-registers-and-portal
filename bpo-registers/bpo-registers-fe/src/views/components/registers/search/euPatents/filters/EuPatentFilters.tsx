import React, { Fragment } from "react";
import useSearchSelectedFilters from "../../../../../../hooks/registers/search/filter/useSearchSelectedFilters";
import { SEARCH_GROUP } from "../../../../../../config/registers/search/registersSearchConfig";
import { renderFilter } from "../../../../../../utils/filters";
import { GridContainer } from "@duosoftbg/bpo-components";
import FilterButtons from "../../common/filterButtons/FilterButtons";
import { EU_PATENT_FILTER_DEFINITION_TO_COMPONENT } from "../../../../../../config/registers/search/filters/filterDefinitionToComponent/PatentFilterDefinitionToComponent";

const EuPatentFilters = ({ control }) => {
  let searchGroup = SEARCH_GROUP.EU_PATENT_SEARCH;
  let searchFilters = useSearchSelectedFilters(searchGroup);
  return (
    <>
      {EU_PATENT_FILTER_DEFINITION_TO_COMPONENT.map((definitionToComponent) => (
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
export default EuPatentFilters;
