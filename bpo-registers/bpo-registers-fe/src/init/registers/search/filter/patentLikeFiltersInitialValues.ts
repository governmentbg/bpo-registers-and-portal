import {
  AbstractFilterDetails,
  CpcSearchType,
  DesignFilterDetails,
  EuPatentFilterDetails,
  InventorFilterDetails,
  IpcSearchType,
  LocarnoClassesFilterDetails,
  PatentClassificationFilterDetails,
  PatentFilterDetails,
  PlantBreedFilterDetails,
  SearchOperatorType,
  SingleDesignNameFilterDetails,
  SingleDesignVerbalElementFilterDetails,
  SpcFilterDetails,
  TextSearchType,
  UtilityModelFilterDetails,
} from "../../../../types/registers/search/filter/filterTypes";
import { commonIpObjectFiltersInitialValues, textMatchFiltersInitialValues } from "./commonInitialValues";
import { FILE_TYPE } from "@duosoftbg/bpo-components";
import { objectSubtypeFiltersInitialValues } from "./markLikeFiltersInitialValues";

export const abstractFiltersInitialValues: AbstractFilterDetails = {
  abstract: textMatchFiltersInitialValues,
};

export const patentClassificationFiltersInitialValues: PatentClassificationFilterDetails = {
  ipcSearchType: IpcSearchType.CODE,
  ipcCode: "",
  ipcClasses: {
    ipcClasses: [],
    ipcClassOperatorType: SearchOperatorType.AND,
  },
  cpcSearchType: CpcSearchType.CODE,
  cpcCode: "",
  cpcClasses: {
    cpcClasses: [],
    cpcClassOperatorType: SearchOperatorType.AND,
  },
};

export const inventorFiltersInitialValues: InventorFilterDetails = {
  inventor: "",
  inventorPersonSearchType: TextSearchType.CONTAINS_WORDS,
};

export const locarnoFiltersInitialValues: LocarnoClassesFilterDetails = {
  locarnoClasses: {
    locarnoClasses: [],
    locarnoClassCodeType: SearchOperatorType.AND,
  },
};

export const singleDesignNameFiltersInitialValues: SingleDesignNameFilterDetails = {
  singleDesignName: "",
  singleDesignNameSearchType: TextSearchType.CONTAINS_WORDS,
};

export const singleDesignVerbalElementFiltersInitialValues: SingleDesignVerbalElementFilterDetails = {
  singleDesignVerbalElement: "",
  singleDesignVerbalElementSearchType: TextSearchType.CONTAINS_WORDS,
};

export const patentFiltersInitialValues: PatentFilterDetails = {
  ...commonIpObjectFiltersInitialValues,
  ...abstractFiltersInitialValues,
  ...patentClassificationFiltersInitialValues,
  ...inventorFiltersInitialValues,
  objectRange: [FILE_TYPE.PATENT],
};

export const designFiltersInitialValues: DesignFilterDetails = {
  ...commonIpObjectFiltersInitialValues,
  ...inventorFiltersInitialValues,
  ...locarnoFiltersInitialValues,
  ...singleDesignNameFiltersInitialValues,
  ...singleDesignVerbalElementFiltersInitialValues,
  objectRange: [FILE_TYPE.DESIGN],
};

export const euPatentFiltersInitialValues: EuPatentFilterDetails = {
  ...commonIpObjectFiltersInitialValues,
  ...abstractFiltersInitialValues,
  ...patentClassificationFiltersInitialValues,
  ...inventorFiltersInitialValues,
  objectRange: [FILE_TYPE.EU_PATENT],
};

export const utilityModelFiltersInitialValues: UtilityModelFilterDetails = {
  ...commonIpObjectFiltersInitialValues,
  ...abstractFiltersInitialValues,
  ...patentClassificationFiltersInitialValues,
  ...inventorFiltersInitialValues,
  objectRange: [FILE_TYPE.UTILITY_MODEL],
};

export const spcFiltersInitialValues: SpcFilterDetails = {
  ...commonIpObjectFiltersInitialValues,
  ...abstractFiltersInitialValues,
  ...patentClassificationFiltersInitialValues,
  ...inventorFiltersInitialValues,
  objectRange: [FILE_TYPE.SPC],
};

export const plantBreedFiltersInitialValues: PlantBreedFilterDetails = {
  ...commonIpObjectFiltersInitialValues,
  ...abstractFiltersInitialValues,
  ...objectSubtypeFiltersInitialValues,
  ...inventorFiltersInitialValues,
  latinClassification: "",
  bgClassification: "",
  objectRange: [FILE_TYPE.PLANT_AND_BREED],
};
