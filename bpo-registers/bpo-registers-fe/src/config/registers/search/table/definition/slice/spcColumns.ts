import { ColumnsDefinition } from "../../../../../../types/registers/search/table/columnsDefinition";
import { RegisterType } from "../../../../../../utils/constants";

export const SPC_COLUMNS: ColumnsDefinition = {
  title: {
    id: "title",
    label: `l.title.${RegisterType.SPC}`,
    sortable: true,
    getValue: (object) => {
      return object.title;
    },
  },
};
