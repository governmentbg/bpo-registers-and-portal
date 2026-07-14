import { useMemo } from "react";
import { REGISTERS_SEARCH_CONFIG } from "../../../../config/registers/search/registersSearchConfig";
import useAppSelector from "../../../redux/base/useAppSelector";
import { convertObjectToArray } from "@duosoftbg/bpo-components";

export const sortColumns = (searchGroup, selectedColumns) => {
  let result = [];
  if (selectedColumns) {
    REGISTERS_SEARCH_CONFIG[searchGroup].selectedTableColumns.forEach((item) => {
      let selectedColumn = selectedColumns.find((e) => e.id === item.id);
      if (selectedColumn) {
        result.push(selectedColumn);
      }
    });
  }
  return result;
};

const useTableSelectedColumns = (searchGroup) => {
  const tableColumns = useAppSelector(
    (state) => state.SearchData.registersSearchData[searchGroup].selectedTableColumns
  );
  return useMemo(() => sortColumns(searchGroup, convertObjectToArray(tableColumns)), [searchGroup, tableColumns]);
};

export default useTableSelectedColumns;
