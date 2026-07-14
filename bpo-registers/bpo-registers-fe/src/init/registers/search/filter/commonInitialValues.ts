import {
  CommonAgentFilterDetails,
  CommonIpObjectFilterDetails,
  PublicationSearchType,
  PublicationsFilterDetails,
  RepresentativeSearchType,
  TextMatchFilterDetails,
  TextSearchType,
} from "../../../../types/registers/search/filter/filterTypes";
import { ASC_ORDER, DEFAULT_PAGE, DEFAULT_PAGE_SIZE, DESC_ORDER, InitialValues } from "@duosoftbg/bpo-components";

export const publicationFiltersInitialValues: PublicationsFilterDetails = {
  publicationSearchType: PublicationSearchType.JOURNAL_NBR,
  publicationYear: "",
  publicationNumber: "",
  publicationSection: "",
  publicationDate: InitialValues.forms.common.fromToStringObjectInitialValues,
};

export const textMatchFiltersInitialValues: TextMatchFilterDetails = {
  text: "",
  searchType: TextSearchType.CONTAINS_WORDS,
};

export const commonIpObjectFiltersInitialValues: CommonIpObjectFilterDetails = {
  objectName: textMatchFiltersInitialValues,
  filingNumber: InitialValues.forms.common.fromToAutocompleteResponseObjectInitialValues,
  filingDate: InitialValues.forms.common.fromToStringObjectInitialValues,
  registrationNumber: InitialValues.forms.common.fromToAutocompleteResponseObjectInitialValues,
  protectedNumber: InitialValues.forms.common.fromToAutocompleteResponseObjectInitialValues,
  expirationDate: InitialValues.forms.common.fromToStringObjectInitialValues,
  status: { bpoOnlineStatus: "", bpoOnlineStatusEn: "" },
  publications: publicationFiltersInitialValues,
  representativeSearchType: RepresentativeSearchType.TEXT,
  representativeTypes: [],
  representativeName: "",
  representativeNameSearchType: TextSearchType.CONTAINS_WORDS,
  representatives: [],
  applicantOwner: "",
  applicantOwnerPersonSearchType: TextSearchType.CONTAINS_WORDS,
  applicantOwnerCountry: InitialValues.forms.common.baseNomenclatureInitialValues,
  objectRange: [],
  priority: {
    priorityDate: InitialValues.forms.common.fromToStringObjectInitialValues,
    priorityCountry: InitialValues.forms.common.baseNomenclatureInitialValues,
    priorityNumber: "",
  },

  page: DEFAULT_PAGE,
  pageSize: DEFAULT_PAGE_SIZE,
  order: DESC_ORDER,
  orderBy: "objectId",
};

export const commonAgentFiltersInitialValues: CommonAgentFilterDetails = {
  name: "",
  status: "1",
  ipoArea: "",
  agentCode: "",
  city: null,
  representativeTypeList: [],
  page: DEFAULT_PAGE,
  pageSize: DEFAULT_PAGE_SIZE,
  order: ASC_ORDER,
  orderBy: "agentName",
};
