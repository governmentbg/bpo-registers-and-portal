import { ColumnsDefinition } from "../../../../../../types/registers/search/table/columnsDefinition";
import { RegisterType } from "../../../../../../utils/constants";

export const DESIGN_COLUMNS: ColumnsDefinition = {
  registrationNumber: {
    id: "registrationNumber",
    label: "l.registrationNumber",
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
  title: {
    id: "title",
    label: `l.title.${RegisterType.DESIGN}`,
    sortable: true,
    getValue: (object) => {
      return object.title;
    },
  },
};
