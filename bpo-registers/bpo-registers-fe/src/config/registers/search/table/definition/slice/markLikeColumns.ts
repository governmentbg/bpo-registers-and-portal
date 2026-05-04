import { ColumnsDefinition } from "../../../../../../types/registers/search/table/columnsDefinition";
import { concatNotEmptyBy } from "@duosoftbg/bpo-components";
import { CellType, RegisterType } from "../../../../../../utils/constants";

export const MARK_LIKE_COLUMNS: ColumnsDefinition = {
  niceClasses: {
    id: "niceClasses",
    label: "l.niceClasses",
    sortable: false,
    getValue: (object) => {
      return concatNotEmptyBy(", ")(...object.niceClasses);
    },
  },
  mainOwner: {
    id: "mainOwner",
    label: `l.object.mainOwner.${RegisterType.MARK}`,
    sortable: true,
    getValue: (object) => {
      return object.mainOwner;
    },
  },
  image: {
    id: "image",
    label: "l.object.image",
    sortable: false,
    getValue: (object) => {
      return object.hasImg;
    },
    type: CellType.IMAGE,
  },
};
