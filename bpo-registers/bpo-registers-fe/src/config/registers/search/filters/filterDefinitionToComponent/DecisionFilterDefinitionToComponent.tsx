import React from "react";
import { DECISION_FILTERS } from "../definition/slice/decisionsFilters";
import DecisionDateSearchFilter from "../../../../../views/components/common/search/filters/definition/DecisionDateSearchFilter";
import ObjectTypeSearchFilter from "../../../../../views/components/common/search/filters/definition/ObjectTypeSearchFilter";
import DecisionDocumentNumberSearchFilter from "../../../../../views/components/common/search/filters/definition/DecisionDocumentNumberSearchFilter";
import DecisionObjectIdSearchFilter from "../../../../../views/components/common/search/filters/definition/DecisionObjectIdSearchFilter";
import LegalDecisionTypeSearchFilter from "../../../../../views/components/common/search/filters/definition/LegalDecisionTypeSearchFilter";
import LegalDecisionGroundTypeSearchFilter from "../../../../../views/components/common/search/filters/definition/LegalDecisionGroundTypeSearchFilter";

export const DECISION_FILTER_DEFINITION_TO_COMPONENT = [
  {
    id: DECISION_FILTERS.objectType.id,
    component: <ObjectTypeSearchFilter />,
  },
  {
    id: DECISION_FILTERS.objectId.id,
    component: <DecisionObjectIdSearchFilter />,
  },
  { id: DECISION_FILTERS.documentDate.id, component: <DecisionDateSearchFilter /> },
  {
    id: DECISION_FILTERS.documentNumber.id,
    component: <DecisionDocumentNumberSearchFilter />,
  },
  {
    id: DECISION_FILTERS.documentType.id,
    component: <LegalDecisionTypeSearchFilter />,
  },
  {
    id: DECISION_FILTERS.legalGroundTypes.id,
    component: <LegalDecisionGroundTypeSearchFilter />,
  },
];
