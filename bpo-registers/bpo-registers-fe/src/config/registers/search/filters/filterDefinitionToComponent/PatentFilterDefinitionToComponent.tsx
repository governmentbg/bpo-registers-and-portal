import React from "react";
import ObjectNameSearchFilter from "../../../../../views/components/common/search/filters/definition/ObjectNameSearchFilter";
import RepresentativeSearchFilter from "../../../../../views/components/common/search/filters/definition/RepresentativeSearchFilter";
import FilingNumberSearchFilter from "../../../../../views/components/common/search/filters/definition/FilingNumberSearchFilter";
import FilingDateSearchFilter from "../../../../../views/components/common/search/filters/definition/FilingDateSearchFilter";
import RegistrationNumberSearchFilter from "../../../../../views/components/common/search/filters/definition/RegistrationNumberSearchFilter";
import ExpirationDateSearchFilter from "../../../../../views/components/common/search/filters/definition/ExpirationDateSearchFilter";
import ApplicantOwnerSearchFilter from "../../../../../views/components/common/search/filters/definition/ApplicantOwnerSearchFilter";
import StatusSearchFilter from "../../../../../views/components/common/search/filters/definition/StatusSearchFilter";
import PublicationsSearchFilter from "../../../../../views/components/common/search/filters/definition/PublicationsSearchFilter";
import PrioritySearchFilter from "../../../../../views/components/common/search/filters/definition/PrioritySearchFilter";
import AbstractSearchFilter from "../../../../../views/components/common/search/filters/definition/AbstractSearchFilter";
import BgClassificationSearchFilter from "../../../../../views/components/common/search/filters/definition/BgClassificationSearchFilter";
import LatinClassificationSearchFilter from "../../../../../views/components/common/search/filters/definition/LatinClassificationSearchFilter";
import ObjectSubtypeSearchFilter from "../../../../../views/components/common/search/filters/definition/ObjectSubtypeSearchFilter";
import {
  COMMON_PATENT_LIKE_FILTERS,
  DESIGN_FILTERS,
  PLANTS_BREEDS_FILTERS,
  SPC_FILTERS,
} from "../definition/slice/patentLikeFilters";
import InventorSearchFilter from "../../../../../views/components/common/search/filters/definition/InventorSearchFilter";
import SingleDesignNameSearchFilter from "../../../../../views/components/common/search/filters/definition/SingleDesignNameSearchFilter";
import SingleDesignVerbalElementSearchFilter from "../../../../../views/components/common/search/filters/definition/SingleDesignVerbalElementSearchFilter";
import LocarnoClassSearchFilter from "../../../../../views/components/common/search/filters/definition/LocarnoClassSearchFilter";
import IpcSearchFilter from "../../../../../views/components/common/search/filters/definition/IpcSearchFilter";
import CpcSearchFilter from "../../../../../views/components/common/search/filters/definition/CpcSearchFilter";

export const PATENT_LIKE_FILTER_DEFINITION_TO_COMPONENT = [
  { id: COMMON_PATENT_LIKE_FILTERS.objectName.id, component: <ObjectNameSearchFilter /> },
  {
    id: COMMON_PATENT_LIKE_FILTERS.filingNumber.id,
    component: <FilingNumberSearchFilter />,
  },
  {
    id: COMMON_PATENT_LIKE_FILTERS.filingDate.id,
    component: <FilingDateSearchFilter />,
  },
  {
    id: COMMON_PATENT_LIKE_FILTERS.protectedNumber.id,
    component: <RegistrationNumberSearchFilter label={"l.searchFilter.protectedNumber"} />,
  },
  {
    id: COMMON_PATENT_LIKE_FILTERS.status.id,
    component: <StatusSearchFilter />,
  },
  {
    id: COMMON_PATENT_LIKE_FILTERS.applicantOwner.id,
    component: <ApplicantOwnerSearchFilter />,
  },
  { id: COMMON_PATENT_LIKE_FILTERS.representatives.id, component: <RepresentativeSearchFilter /> },
  {
    id: COMMON_PATENT_LIKE_FILTERS.publications.id,
    component: <PublicationsSearchFilter />,
  },
  {
    id: COMMON_PATENT_LIKE_FILTERS.priority.id,
    component: <PrioritySearchFilter />,
  },
  {
    id: COMMON_PATENT_LIKE_FILTERS.expirationDate.id,
    component: <ExpirationDateSearchFilter />,
  },
  {
    id: COMMON_PATENT_LIKE_FILTERS.abstract.id,
    component: <AbstractSearchFilter />,
  },
  {
    id: COMMON_PATENT_LIKE_FILTERS.inventor.id,
    component: <InventorSearchFilter />,
  },
  {
    id: COMMON_PATENT_LIKE_FILTERS.ipcClasses.id,
    component: <IpcSearchFilter />,
  },
  {
    id: COMMON_PATENT_LIKE_FILTERS.cpcClasses.id,
    component: <CpcSearchFilter />,
  },
];

export const PATENT_FILTER_DEFINITION_TO_COMPONENT = [...PATENT_LIKE_FILTER_DEFINITION_TO_COMPONENT];

export const DESIGN_FILTER_DEFINITION_TO_COMPONENT = [
  {
    id: DESIGN_FILTERS.mainDesignName.id,
    component: <ObjectNameSearchFilter label={DESIGN_FILTERS.mainDesignName.label} />,
  },
  {
    id: DESIGN_FILTERS.filingNumber.id,
    component: <FilingNumberSearchFilter />,
  },
  {
    id: DESIGN_FILTERS.filingDate.id,
    component: <FilingDateSearchFilter />,
  },
  {
    id: DESIGN_FILTERS.registrationNumber.id,
    component: <RegistrationNumberSearchFilter />,
  },
  {
    id: DESIGN_FILTERS.status.id,
    component: <StatusSearchFilter />,
  },
  {
    id: DESIGN_FILTERS.applicantOwner.id,
    component: <ApplicantOwnerSearchFilter />,
  },
  { id: DESIGN_FILTERS.representatives.id, component: <RepresentativeSearchFilter /> },
  {
    id: DESIGN_FILTERS.publications.id,
    component: <PublicationsSearchFilter />,
  },
  {
    id: DESIGN_FILTERS.priority.id,
    component: <PrioritySearchFilter />,
  },
  {
    id: DESIGN_FILTERS.expirationDate.id,
    component: <ExpirationDateSearchFilter />,
  },
  {
    id: DESIGN_FILTERS.author.id,
    component: <InventorSearchFilter isAuthor />,
  },
  {
    id: DESIGN_FILTERS.locarnoClasses.id,
    component: <LocarnoClassSearchFilter />,
  },
  {
    id: DESIGN_FILTERS.singleDesignName.id,
    component: <SingleDesignNameSearchFilter />,
  },
  {
    id: DESIGN_FILTERS.singleDesignVerbalElement.id,
    component: <SingleDesignVerbalElementSearchFilter />,
  },
];

export const EU_PATENT_FILTER_DEFINITION_TO_COMPONENT = [...PATENT_LIKE_FILTER_DEFINITION_TO_COMPONENT];
export const UTILITY_MODEL_FILTER_DEFINITION_TO_COMPONENT = [...PATENT_LIKE_FILTER_DEFINITION_TO_COMPONENT];
export const PLANT_BREED_FILTER_DEFINITION_TO_COMPONENT = [
  ...PATENT_LIKE_FILTER_DEFINITION_TO_COMPONENT,
  {
    id: PLANTS_BREEDS_FILTERS.origin.id,
    component: <AbstractSearchFilter label={PLANTS_BREEDS_FILTERS.origin.label} />,
  },
  {
    id: PLANTS_BREEDS_FILTERS.author.id,
    component: <InventorSearchFilter isAuthor />,
  },
  {
    id: PLANTS_BREEDS_FILTERS.latinClassification.id,
    component: <LatinClassificationSearchFilter />,
  },
  {
    id: PLANTS_BREEDS_FILTERS.bgClassification.id,
    component: <BgClassificationSearchFilter />,
  },
  {
    id: PLANTS_BREEDS_FILTERS.objectSubtype.id,
    component: <ObjectSubtypeSearchFilter />,
  },
];

export const SPC_DEFINITION_TO_COMPONENT = [
  { id: SPC_FILTERS.objectName.id, component: <ObjectNameSearchFilter label={"l.main.patent.title"} /> },
  {
    id: SPC_FILTERS.filingNumber.id,
    component: <FilingNumberSearchFilter />,
  },
  {
    id: SPC_FILTERS.filingDate.id,
    component: <FilingDateSearchFilter />,
  },
  {
    id: SPC_FILTERS.protectedNumber.id,
    component: <RegistrationNumberSearchFilter label={"l.searchFilter.protectedNumber"} />,
  },
  {
    id: SPC_FILTERS.status.id,
    component: <StatusSearchFilter />,
  },
  {
    id: SPC_FILTERS.applicantOwner.id,
    component: <ApplicantOwnerSearchFilter />,
  },
  { id: SPC_FILTERS.representatives.id, component: <RepresentativeSearchFilter /> },
  {
    id: SPC_FILTERS.publications.id,
    component: <PublicationsSearchFilter />,
  },
  {
    id: SPC_FILTERS.priority.id,
    component: <PrioritySearchFilter />,
  },
  {
    id: SPC_FILTERS.expirationDate.id,
    component: <ExpirationDateSearchFilter />,
  },
  {
    id: SPC_FILTERS.abstract.id,
    component: <AbstractSearchFilter label={"l.product.name"} />,
  },
  {
    id: SPC_FILTERS.inventor.id,
    component: <InventorSearchFilter />,
  },
  {
    id: SPC_FILTERS.ipcClasses.id,
    component: <IpcSearchFilter />,
  },
  {
    id: SPC_FILTERS.cpcClasses.id,
    component: <CpcSearchFilter />,
  },
];
