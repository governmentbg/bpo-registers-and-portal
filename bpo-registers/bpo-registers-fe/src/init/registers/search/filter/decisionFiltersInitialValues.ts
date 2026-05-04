import { DecisionFilterDetails, SearchOperatorType } from "../../../../types/registers/search/filter/filterTypes";
import { DEFAULT_PAGE, DEFAULT_PAGE_SIZE, DESC_ORDER, InitialValues } from "@duosoftbg/bpo-components";

export const decisionFiltersInitialValues: DecisionFilterDetails = {
  documentDate: InitialValues.forms.common.fromToStringObjectInitialValues,
  documentNumber: "",
  documentType: {
    id: null,
    name: "",
    nameEn: "",
  },
  legalGroundTypes: [],
  legalGroundTypesOperatorType: SearchOperatorType.AND,
  objectId: "",
  objectType: "",
  page: DEFAULT_PAGE,
  pageSize: DEFAULT_PAGE_SIZE,
  order: DESC_ORDER,
  orderBy: "objectId",
};
