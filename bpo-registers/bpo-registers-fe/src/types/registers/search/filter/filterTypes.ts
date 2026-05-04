import { AutocompleteResponse, BaseNomenclature, FromToObjectType } from "@duosoftbg/bpo-components";

export enum TextSearchType {
  CONTAINS_WORDS = "CONTAINS_WORDS",
  WHOLE_WORDS = "WHOLE_WORDS",
  EXACTLY = "EXACTLY",
}

export enum SearchOperatorType {
  AND = "AND",
  OR = "OR",
  AND_NOT = "AND_NOT",
}

export enum RepresentativeSearchType {
  TEXT = "TEXT",
  AUTOCOMPLETE = "AUTOCOMPLETE",
}

export enum IpcSearchType {
  CODE = "CODE",
  AUTOCOMPLETE = "AUTOCOMPLETE",
}

export enum CpcSearchType {
  CODE = "CODE",
  AUTOCOMPLETE = "AUTOCOMPLETE",
}

export enum PublicationSearchType {
  JOURNAL_NBR = "JOURNAL_NBR",
  PUBLICATION_DATE = "PUBLICATION_DATE",
}

export interface TextMatchFilterDetails {
  text: string;
  searchType: TextSearchType;
}

export interface CommonFilterDetails {
  page: number;
  pageSize: number;
  order: string;
  orderBy: string;
}

export interface CommonIpObjectFilterDetails extends CommonFilterDetails {
  objectName: TextMatchFilterDetails;
  filingNumber: FromToObjectType<AutocompleteResponse>;
  filingDate: FromToObjectType<string>;
  registrationNumber: FromToObjectType<AutocompleteResponse>;
  protectedNumber: FromToObjectType<AutocompleteResponse>;
  expirationDate: FromToObjectType<string>;
  status: { bpoOnlineStatus: string; bpoOnlineStatusEn: string };
  publications: PublicationsFilterDetails;
  representativeSearchType: RepresentativeSearchType;
  representativeTypes: RepresentativeTypeDTO[];
  representativeName: string;
  representativeNameSearchType: TextSearchType;
  representatives: RepresentativeAutocompleteDTO[];
  applicantOwner: string;
  applicantOwnerPersonSearchType: TextSearchType;
  applicantOwnerCountry: BaseNomenclature;
  objectRange: string[];
  priority: { priorityDate: FromToObjectType<string>; priorityCountry: BaseNomenclature; priorityNumber: string };
}

export interface CommonAgentFilterDetails extends CommonFilterDetails {
  name: string;
  agentCode: string;
  ipoArea: string;
  status: string;
  city: string;
  representativeTypeList: string[];
}

export interface AgentFilterDetails extends CommonAgentFilterDetails {
  agentSpeciality: string;
}

export interface PartnershipFilterDetails extends CommonAgentFilterDetails {}

export interface PublicationsFilterDetails {
  publicationSearchType: PublicationSearchType;
  publicationYear: string;
  publicationNumber: string;
  publicationSection: string;
  publicationDate: FromToObjectType<string>;
}

export interface ObjectSubtypeFilterDetails {
  objectSubtype: string[];
}

export interface NiceClassesFilterDetails {
  niceClasses: {
    niceClassCodes: string[];
    niceClassCodeType: SearchOperatorType;
    specification: TextMatchFilterDetails;
  };
}

export interface ViennaClassesFilterDetails {
  viennaClasses: {
    viennaClasses: BaseNomenclature[];
    viennaClassCodeType: SearchOperatorType;
  };
}

export interface LocarnoClassesFilterDetails {
  locarnoClasses: {
    locarnoClasses: BaseNomenclature[];
    locarnoClassCodeType: SearchOperatorType;
  };
}

export interface InventorFilterDetails {
  inventor: string;
  inventorPersonSearchType: TextSearchType;
}

export interface SingleDesignNameFilterDetails {
  singleDesignName: string;
  singleDesignNameSearchType: TextSearchType;
}

export interface SingleDesignVerbalElementFilterDetails {
  singleDesignVerbalElement: string;
  singleDesignVerbalElementSearchType: TextSearchType;
}

export interface PatentClassificationFilterDetails {
  ipcSearchType: IpcSearchType;
  ipcCode: string;
  ipcClasses: {
    ipcClasses: string[];
    ipcClassOperatorType: SearchOperatorType;
  };

  cpcSearchType: CpcSearchType;
  cpcCode: string;
  cpcClasses: {
    cpcClasses: string[];
    cpcClassOperatorType: SearchOperatorType;
  };
}

export interface AbstractFilterDetails {
  abstract: TextMatchFilterDetails;
}
// export interface LocarnoFilterDetails {
//   locarnoClass: string;
//   locarnoSubclass: string;
// }

interface MarkKindDTO {
  id: string;
  description: string;
  descriptionEn: string;
}

interface RepresentativeTypeDTO {
  id: string;
  description: string;
  descriptionEn: string;
}

interface RepresentativeAutocompleteDTO {
  id: number;
  name: string;
  agentCode: string;
  representativeType: string;
  representativeTypeName: string;
  representativeTypeNameEn: string;
}

export interface MarkFilterDetails
  extends CommonIpObjectFilterDetails,
    ObjectSubtypeFilterDetails,
    NiceClassesFilterDetails,
    ViennaClassesFilterDetails {
  markKind: MarkKindDTO;
}

export interface GeoIndicationFilterDetails
  extends CommonIpObjectFilterDetails,
    ObjectSubtypeFilterDetails,
    NiceClassesFilterDetails,
    ViennaClassesFilterDetails {}

export interface PatentFilterDetails
  extends CommonIpObjectFilterDetails,
    AbstractFilterDetails,
    PatentClassificationFilterDetails,
    InventorFilterDetails {}

export interface EuPatentFilterDetails
  extends CommonIpObjectFilterDetails,
    AbstractFilterDetails,
    PatentClassificationFilterDetails,
    InventorFilterDetails {}

export interface UtilityModelFilterDetails
  extends CommonIpObjectFilterDetails,
    AbstractFilterDetails,
    PatentClassificationFilterDetails,
    InventorFilterDetails {}

export interface PlantBreedFilterDetails
  extends CommonIpObjectFilterDetails,
    ObjectSubtypeFilterDetails,
    AbstractFilterDetails,
    InventorFilterDetails {
  latinClassification: string;
  bgClassification: string;
}

export interface SpcFilterDetails
  extends CommonIpObjectFilterDetails,
    AbstractFilterDetails,
    PatentClassificationFilterDetails,
    InventorFilterDetails {}

export interface DesignFilterDetails
  extends CommonIpObjectFilterDetails,
    InventorFilterDetails,
    LocarnoClassesFilterDetails {}

export interface CombinedSearchFilterDetails extends CommonIpObjectFilterDetails {}

export interface DecisionFilterDetails extends CommonFilterDetails {
  objectType: string;
  objectId: string;
  documentNumber: string;
  documentDate: FromToObjectType<string>;
  documentType: {
    id: number;
    name: string;
    nameEn: string;
  };
  legalGroundTypes: {
    id: number;
    name: string;
    nameEn: string;
  }[];
  legalGroundTypesOperatorType: SearchOperatorType;
}
