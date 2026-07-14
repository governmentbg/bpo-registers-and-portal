import React from "react";
import ObjectNameSearchFilter from "../../../../../views/components/common/search/filters/definition/ObjectNameSearchFilter";
import RepresentativeSearchFilter from "../../../../../views/components/common/search/filters/definition/RepresentativeSearchFilter";
import FilingNumberSearchFilter from "../../../../../views/components/common/search/filters/definition/FilingNumberSearchFilter";
import FilingDateSearchFilter from "../../../../../views/components/common/search/filters/definition/FilingDateSearchFilter";
import RegistrationNumberSearchFilter from "../../../../../views/components/common/search/filters/definition/RegistrationNumberSearchFilter";
import ApplicantOwnerSearchFilter from "../../../../../views/components/common/search/filters/definition/ApplicantOwnerSearchFilter";
import StatusSearchFilter from "../../../../../views/components/common/search/filters/definition/StatusSearchFilter";
import PublicationsSearchFilter from "../../../../../views/components/common/search/filters/definition/PublicationsSearchFilter";
import PrioritySearchFilter from "../../../../../views/components/common/search/filters/definition/PrioritySearchFilter";
import { COMBINE_SEARCH_FILTER } from "../definition/slice/combineSearchFilters";
import ExpirationDateSearchFilter from "../../../../../views/components/common/search/filters/definition/ExpirationDateSearchFilter";
import ObjectRangeSearchFilter from "../../../../../views/components/common/search/filters/definition/ObjectRangeSearchFilter";

export const COMBINED_FILTER_DEFINITION_TO_COMPONENT = [
  { id: COMBINE_SEARCH_FILTER.objectName.id, component: <ObjectNameSearchFilter /> },
  {
    id: COMBINE_SEARCH_FILTER.filingNumber.id,
    component: <FilingNumberSearchFilter />,
  },
  {
    id: COMBINE_SEARCH_FILTER.filingDate.id,
    component: <FilingDateSearchFilter />,
  },
  {
    id: COMBINE_SEARCH_FILTER.registrationNumber.id,
    component: <RegistrationNumberSearchFilter label={COMBINE_SEARCH_FILTER.registrationNumber.label} />,
  },
  {
    id: COMBINE_SEARCH_FILTER.status.id,
    component: <StatusSearchFilter />,
  },
  {
    id: COMBINE_SEARCH_FILTER.applicantOwner.id,
    component: <ApplicantOwnerSearchFilter />,
  },
  { id: COMBINE_SEARCH_FILTER.representatives.id, component: <RepresentativeSearchFilter /> },
  {
    id: COMBINE_SEARCH_FILTER.publications.id,
    component: <PublicationsSearchFilter />,
  },
  {
    id: COMBINE_SEARCH_FILTER.priority.id,
    component: <PrioritySearchFilter />,
  },
  {
    id: COMBINE_SEARCH_FILTER.expirationDate.id,
    component: <ExpirationDateSearchFilter />,
  },
  { id: COMBINE_SEARCH_FILTER.objectRange.id, component: <ObjectRangeSearchFilter /> },
];
