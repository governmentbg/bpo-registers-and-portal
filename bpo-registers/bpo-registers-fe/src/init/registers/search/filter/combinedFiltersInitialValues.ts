import { CombinedSearchFilterDetails } from "../../../../types/registers/search/filter/filterTypes";
import { commonIpObjectFiltersInitialValues } from "./commonInitialValues";
import { FILE_TYPE } from "@duosoftbg/bpo-components";

export const combinedFiltersInitialValues: CombinedSearchFilterDetails = {
  ...commonIpObjectFiltersInitialValues,
  objectRange: [
    FILE_TYPE.MARK,
    FILE_TYPE.GI,
    FILE_TYPE.PATENT,
    FILE_TYPE.UTILITY_MODEL,
    FILE_TYPE.EU_PATENT,
    FILE_TYPE.PLANT_AND_BREED,
    FILE_TYPE.SPC,
    FILE_TYPE.DESIGN,
  ],
};
