import React, { Fragment } from "react";
import useSearchSelectedFilters from "../../../../../../hooks/registers/search/filter/useSearchSelectedFilters";
import { SEARCH_GROUP } from "../../../../../../config/registers/search/registersSearchConfig";
import { GridContainer } from "@duosoftbg/bpo-components";
import FilterButtons from "../../common/filterButtons/FilterButtons";
import { renderFilter } from "../../../../../../utils/filters";
import { DECISION_FILTER_DEFINITION_TO_COMPONENT } from "../../../../../../config/registers/search/filters/filterDefinitionToComponent/DecisionFilterDefinitionToComponent";
import { Link, Typography } from "@mui/material";
import { useTranslation } from "react-i18next";

const DecisionFilters = ({ control }) => {
  const { t } = useTranslation();
  let searchGroup = SEARCH_GROUP.DECISION_SEARCH;
  let searchFilters = useSearchSelectedFilters(searchGroup);
  return (
    <>
      {DECISION_FILTER_DEFINITION_TO_COMPONENT.map((definitionToComponent) => (
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
      <Typography variant="body2" component={"div"} fontWeight={"fontWeightBold"} align={"center"}>
        {t("l.court.register")}
        <Link href="https://sofia-adms-g.justice.bg/bg/19140" target="_blank" rel="noopener noreferrer">
          {t("l.here")}
        </Link>
        .
      </Typography>
    </>
  );
};
export default DecisionFilters;
