import { useTranslation } from "react-i18next";
import { useFormContext } from "react-hook-form";
import * as React from "react";
import { Fragment } from "react";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import { AlertSpg, BpoSearchTable, OptionTableCell } from "@duosoftbg/bpo-components";
import TableRow from "@mui/material/TableRow";
import { generateUrl, openInNewTab } from "../../../../../../utils/urls";
import { useNavigate } from "react-router-dom";
import { RegisterType } from "../../../../../../utils/constants";
import { SEARCH_GROUP } from "../../../../../../config/registers/search/registersSearchConfig";

const AgentRegisterListTableBase = ({
  total,
  records,
  blockTable,
  onPageOrOrderChange,
  searchGroup,
  activeCells,
  activeColumns,
  selectedColumnsDialog,
  optionCellContent = undefined,
}) => {
  const { t } = useTranslation();

  return (
    <Fragment>
      {total > 0 && (
        <>
          {selectedColumnsDialog}
          <BpoSearchTable
            group={searchGroup}
            headCells={activeCells}
            total={total}
            blockTable={blockTable}
            onPageOrOrderChange={onPageOrOrderChange}
          >
            <TableBody>
              {records.map((row, index) => (
                <AgentTableRow
                  key={row.id}
                  row={row}
                  index={index}
                  activeColumns={activeColumns}
                  optionCellContent={optionCellContent}
                  searchGroup={searchGroup}
                />
              ))}
            </TableBody>
          </BpoSearchTable>
        </>
      )}
      {!(total > 0) && (
        <AlertSpg mt={10} mb={10} severity="info">
          {t("m.empty.list")}
        </AlertSpg>
      )}
    </Fragment>
  );
};

const AgentTableRow = ({ row, index, activeColumns, optionCellContent, searchGroup }) => {
  const { getValues } = useFormContext();
  const navigate = useNavigate();
  const OptionCellContent = optionCellContent;
  const optionCellContentProps = { person: row };
  const registerType = searchGroup === SEARCH_GROUP.AGENTS_SEARCH ? RegisterType.AGENT : RegisterType.PARTNERSHIP;

  return (
    <TableRow
      hover
      onClick={(event) => {
        if (event.ctrlKey || event.metaKey) {
          // Open in a new tab
          openInNewTab(generateUrl(registerType, row?.agent?.agentCode, "view", true));
        } else {
          // Keep state of SPA
          navigate(generateUrl(registerType, row?.agent?.agentCode, "view"));
        }
      }}
      style={{ cursor: "pointer" }}
    >
      <TableCell>{index + 1 + getValues().page * getValues().pageSize}</TableCell>
      <>
        {activeColumns.map((cell) => (
          <TableCell key={cell.id}>{cell.getValue(row) ?? ""}</TableCell>
        ))}
      </>
      {optionCellContent && (
        <OptionTableCell
          className={"docx-hidable"}
          onClick={(event) => {
            event.stopPropagation(); // Prevent row click event for this entire column
          }}
        >
          {optionCellContent && <OptionCellContent {...optionCellContentProps} />}
        </OptionTableCell>
      )}
    </TableRow>
  );
};
export default AgentRegisterListTableBase;
