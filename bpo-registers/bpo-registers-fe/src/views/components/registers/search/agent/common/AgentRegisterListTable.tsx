import * as React from "react";
import useTableSelectedColumns from "../../../../../../hooks/registers/search/table/useTableSelectedColumns";
import { selectActiveColumns, selectActiveHeadCells } from "../../../../../../utils/tableHeadCells";
import AgentRegisterListTableBase from "./AgentRegisterListTableBase";
import SelectedColumnsDialog from "../../../../common/search/table/dialog/SelectedColumnsDialog";
import { TableButton } from "@duosoftbg/bpo-components";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import { AgentAddressesControlActions } from "../../../../../../store/redux/slice/ComponentsControl/agentAddressesControl";
import { RegisterType } from "../../../../../../utils/constants";

const AgentRegisterListTable = ({
  total,
  records,
  blockTable,
  onPageOrOrderChange,
  searchGroup,
  registerType = RegisterType.AGENT,
}) => {
  const selectedTableColumns = useTableSelectedColumns(searchGroup);
  const activeCells = selectActiveHeadCells(selectedTableColumns, searchGroup);
  const activeColumns = selectActiveColumns(selectedTableColumns, searchGroup);

  return (
    <AgentRegisterListTableBase
      total={total}
      records={records}
      blockTable={blockTable}
      onPageOrOrderChange={onPageOrOrderChange}
      searchGroup={searchGroup}
      activeCells={activeCells}
      activeColumns={activeColumns}
      selectedColumnsDialog={<SelectedColumnsDialog searchGroup={searchGroup} />}
      optionCellContent={registerType === RegisterType.AGENT ? AgentsOptionCellContent : null}
    />
  );
};

const AgentsOptionCellContent = ({ person }) => {
  const dispatch = useAppDispatch();
  const handleOpenAgentAddressDialog = () => {
    dispatch(AgentAddressesControlActions.openModal({ person }));
  };

  return <TableButton type={"location"} onBtnClick={handleOpenAgentAddressDialog} title={"l.btn.address"} />;
};
export default AgentRegisterListTable;
