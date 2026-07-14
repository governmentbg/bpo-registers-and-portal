import { ColumnsDefinition } from "../../../../../../types/registers/search/table/columnsDefinition";

export const PATENT_LIKE_COLUMNS: ColumnsDefinition = {
  registrationNumber: {
    id: "registrationNumber",
    label: "l.object.registrationNumber.col",
    sortable: true,
    getValue: (object) => {
      return object.registrationNbr;
    },
  },
  mainOwner: {
    id: "mainOwner",
    label: "l.object.mainOwner",
    sortable: true,
    getValue: (object) => {
      return object.mainOwner;
    },
  },
};
