import {
  GeoIndicationFilterDetails,
  MarkFilterDetails,
  NiceClassesFilterDetails,
  ObjectSubtypeFilterDetails,
  SearchOperatorType,
  ViennaClassesFilterDetails,
} from "../../../../types/registers/search/filter/filterTypes";
import { FILE_TYPE } from "@duosoftbg/bpo-components";
import { commonIpObjectFiltersInitialValues, textMatchFiltersInitialValues } from "./commonInitialValues";

export const objectSubtypeFiltersInitialValues: ObjectSubtypeFilterDetails = {
  objectSubtype: [],
};

export const niceClassesFiltersInitialValues: NiceClassesFilterDetails = {
  niceClasses: {
    niceClassCodes: [],
    niceClassCodeType: SearchOperatorType.AND,
    specification: textMatchFiltersInitialValues,
  },
};

export const viennaClassesFiltersInitialValues: ViennaClassesFilterDetails = {
  viennaClasses: {
    viennaClasses: [],
    viennaClassCodeType: SearchOperatorType.AND,
  },
};

export const markFiltersInitialValues: MarkFilterDetails = {
  ...commonIpObjectFiltersInitialValues,
  ...objectSubtypeFiltersInitialValues,
  ...niceClassesFiltersInitialValues,
  ...viennaClassesFiltersInitialValues,

  objectRange: [FILE_TYPE.MARK],
  markKind: null,
};

export const geoIndicationsFiltersInitialValues: GeoIndicationFilterDetails = {
  ...commonIpObjectFiltersInitialValues,
  ...objectSubtypeFiltersInitialValues,
  ...niceClassesFiltersInitialValues,
  ...viennaClassesFiltersInitialValues,
  objectRange: [FILE_TYPE.GI],
};
