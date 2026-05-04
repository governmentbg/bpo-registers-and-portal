import * as React from "react";
import useTableSelectedColumns from "../../../../../../hooks/registers/search/table/useTableSelectedColumns";
import { selectActiveColumns, selectActiveHeadCells } from "../../../../../../utils/tableHeadCells";
import RegisterListTableBase from "./RegisterListTableBase";
import SelectedColumnsDialog from "../../../../common/search/table/dialog/SelectedColumnsDialog";

const RegisterListTable = ({ total, records, blockTable, onPageOrOrderChange, searchGroup }) => {
  const selectedTableColumns = useTableSelectedColumns(searchGroup);
  const activeCells = selectActiveHeadCells(selectedTableColumns, searchGroup);
  const activeColumns = selectActiveColumns(selectedTableColumns, searchGroup);

  return (
    <RegisterListTableBase
      total={total}
      records={records}
      blockTable={blockTable}
      onPageOrOrderChange={onPageOrOrderChange}
      searchGroup={searchGroup}
      activeCells={activeCells}
      activeColumns={activeColumns}
      selectedColumnsDialog={<SelectedColumnsDialog searchGroup={searchGroup} />}
    />
  );
};
export default RegisterListTable;
