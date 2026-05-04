import React from "react";
import ObjectNameSearchFilter from "../../../../../views/components/common/search/filters/definition/ObjectNameSearchFilter";
import RepresentativeSearchFilter from "../../../../../views/components/common/search/filters/definition/RepresentativeSearchFilter";
import FilingNumberSearchFilter from "../../../../../views/components/common/search/filters/definition/FilingNumberSearchFilter";
import FilingDateSearchFilter from "../../../../../views/components/common/search/filters/definition/FilingDateSearchFilter";
import MarkKindSearchFilter from "../../../../../views/components/common/search/filters/definition/MarkKindSearchFilter";
import RegistrationNumberSearchFilter from "../../../../../views/components/common/search/filters/definition/RegistrationNumberSearchFilter";
import ExpirationDateSearchFilter from "../../../../../views/components/common/search/filters/definition/ExpirationDateSearchFilter";
import ApplicantOwnerSearchFilter from "../../../../../views/components/common/search/filters/definition/ApplicantOwnerSearchFilter";
import ObjectSubtypeSearchFilter from "../../../../../views/components/common/search/filters/definition/ObjectSubtypeSearchFilter";
import StatusSearchFilter from "../../../../../views/components/common/search/filters/definition/StatusSearchFilter";
import PublicationsSearchFilter from "../../../../../views/components/common/search/filters/definition/PublicationsSearchFilter";
import NiceClassSearchFilter from "../../../../../views/components/common/search/filters/definition/NiceClassSearchFilter";
import ViennaClassSearchFilter from "../../../../../views/components/common/search/filters/definition/ViennaClassSearchFilter";
import { COMMON_MARK_LIKE_FILTERS, GEO_INDICATION_FILTERS, MARK_FILTERS } from "../definition/slice/markLikeFilters";
import PrioritySearchFilter from "../../../../../views/components/common/search/filters/definition/PrioritySearchFilter";
import { UNIVERSAL_FILTERS } from "../definition/slice/universalFilters";

export const MARK_LIKE_FILTER_DEFINITION_TO_COMPONENT = [
  { id: COMMON_MARK_LIKE_FILTERS.objectName.id, component: <ObjectNameSearchFilter /> },
  {
    id: COMMON_MARK_LIKE_FILTERS.filingNumber.id,
    component: <FilingNumberSearchFilter />,
  },
  {
    id: COMMON_MARK_LIKE_FILTERS.filingDate.id,
    component: <FilingDateSearchFilter />,
  },
  {
    id: COMMON_MARK_LIKE_FILTERS.registrationNumber.id,
    component: <RegistrationNumberSearchFilter />,
  },
  {
    id: COMMON_MARK_LIKE_FILTERS.status.id,
    component: <StatusSearchFilter />,
  },
  {
    id: COMMON_MARK_LIKE_FILTERS.applicantOwner.id,
    component: <ApplicantOwnerSearchFilter />,
  },
  { id: COMMON_MARK_LIKE_FILTERS.representatives.id, component: <RepresentativeSearchFilter /> },
  {
    id: COMMON_MARK_LIKE_FILTERS.publications.id,
    component: <PublicationsSearchFilter />,
  },
  {
    id: UNIVERSAL_FILTERS.priority.id,
    component: <PrioritySearchFilter />,
  },
  {
    id: MARK_FILTERS.expirationDate.id,
    component: <ExpirationDateSearchFilter />,
  },
  {
    id: COMMON_MARK_LIKE_FILTERS.viennaClasses.id,
    component: <ViennaClassSearchFilter />,
  },
];

export const MARK_FILTER_DEFINITION_TO_COMPONENT = [
  ...MARK_LIKE_FILTER_DEFINITION_TO_COMPONENT,
  {
    id: MARK_FILTERS.niceClasses.id,
    component: <NiceClassSearchFilter />,
  },
  {
    id: MARK_FILTERS.markKind.id,
    component: <MarkKindSearchFilter />,
  },
  {
    id: MARK_FILTERS.objectSubtype.id,
    component: <ObjectSubtypeSearchFilter labelCode={`l.object.mark.objectSubType`} />,
  },
];

export const GEO_INDICATION_FILTER_DEFINITION_TO_COMPONENT = [
  ...MARK_LIKE_FILTER_DEFINITION_TO_COMPONENT,
  {
    id: GEO_INDICATION_FILTERS.niceClasses.id,
    component: <NiceClassSearchFilter onlyGoods={true} />,
  },
  {
    id: GEO_INDICATION_FILTERS.objectSubtype.id,
    component: <ObjectSubtypeSearchFilter />,
  },
];
