import { ColumnsDefinition } from "../../../../../types/registers/search/table/columnsDefinition";
import { UNIVERSAL_COLUMNS } from "./slice/universalColumns";
import { REPRESENTATIVE_LIKE_COLUMNS } from "./slice/agentColumns";
import { MARK_LIKE_COLUMNS } from "./slice/markLikeColumns";
import { PATENT_LIKE_COLUMNS } from "./slice/patentLikeColumns";
import { DESIGN_COLUMNS } from "./slice/designColumns";
import { SPC_COLUMNS } from "./slice/spcColumns";

export const REGISTERS_TABLE_MARK_LIKE_COLUMNS_DEFINITION: ColumnsDefinition = {
  ...UNIVERSAL_COLUMNS,
  ...MARK_LIKE_COLUMNS,
};

export const REGISTERS_TABLE_PATENT_LIKE_COLUMNS_DEFINITION: ColumnsDefinition = {
  ...UNIVERSAL_COLUMNS,
  ...PATENT_LIKE_COLUMNS,
};

export const REGISTERS_TABLE_SPC_COLUMNS_DEFINITION: ColumnsDefinition = {
  ...UNIVERSAL_COLUMNS,
  ...PATENT_LIKE_COLUMNS,
  ...SPC_COLUMNS,
};

export const REGISTERS_TABLE_DESIGN_COLUMNS_DEFINITION: ColumnsDefinition = {
  ...UNIVERSAL_COLUMNS,
  ...DESIGN_COLUMNS,
};

export const REGISTERS_TABLE_REPRESENTATIVE_LIKE_COLUMNS_DEFINITION: ColumnsDefinition = {
  ...UNIVERSAL_COLUMNS,
  ...REPRESENTATIVE_LIKE_COLUMNS,
};

export const REGISTERS_TABLE_COMBINED_COLUMNS_DEFINITION: ColumnsDefinition = {
  ...UNIVERSAL_COLUMNS,
  mainOwner: {
    id: "mainOwner",
    label: "l.object.mainOwner",
    sortable: true,
    getValue: (object) => {
      return object.mainOwner;
    },
  },
};
