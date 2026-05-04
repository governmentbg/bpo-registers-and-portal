import TableContainer from "@mui/material/TableContainer/TableContainer";
import TableHead from "@mui/material/TableHead/TableHead";
import React, { Fragment, useState } from "react";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell/TableCell";
import TableBody from "@mui/material/TableBody";
import { useTranslation } from "react-i18next";
import { DividerSpg, GridContainer, HtmlParseText, isArrayNotEmpty, OptionTableCell } from "@duosoftbg/bpo-components";
import IconButton from "@mui/material/IconButton";
import { KeyboardArrowDown, KeyboardArrowUp } from "@mui/icons-material";
import Collapse from "@mui/material/Collapse/Collapse";
import { Table } from "@mui/material";

const CancellationDataPanel = ({ data }) => {
  const { t } = useTranslation();

  return (
    <TableContainer>
      <Table aria-label="collapsible table">
        <TableHead>
          <TableRow>
            <TableCell style={{ width: 35 }} />
            <TableCell>{t("l.table.head.cancellation.recordalType")}</TableCell>
            <TableCell>{t("l.table.head.recordalNumber")}</TableCell>
            <TableCell>{t("l.table.head.status")}</TableCell>
          </TableRow>
        </TableHead>

        <TableBody>
          {data.cancellations.map((cancellation) => (
            <CancellationDataRow key={cancellation.id} cancellation={cancellation} />
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

const CancellationDataRow = ({ cancellation }) => {
  const [openDetailRow, setOpenDetailRow] = useState(false);
  const hasDetails = isArrayNotEmpty(cancellation.details);

  return (
    <Fragment>
      <TableRow hover>
        <OptionTableCell className={"docx-hidable"}>
          {hasDetails && (
            <IconButton aria-label="expand row" size="small" onClick={() => setOpenDetailRow(!openDetailRow)}>
              {openDetailRow ? <KeyboardArrowUp /> : <KeyboardArrowDown />}
            </IconButton>
          )}
        </OptionTableCell>
        <TableCell>{cancellation?.recordalType?.description}</TableCell>
        <TableCell>{cancellation.recordalNumber}</TableCell>
        <TableCell>{cancellation?.status?.bpoOnlineStatus}</TableCell>
      </TableRow>
      <TableRow style={{ background: "rgb(251, 250, 250)" }}>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={7}>
          <Collapse in={openDetailRow} timeout="auto" unmountOnExit>
            {cancellation.details.map((detail, index) => (
              <div key={detail.id} style={{ marginLeft: "40px" }}>
                {index !== 0 && <DividerSpg />}
                <GridContainer spacing={0}>
                  <HtmlParseText
                    propsGrid={{ xs: 12, sm: 12, md: 12, lg: 12 }}
                    withGrid
                    label={detail.type.description}
                    text={detail.description}
                  />
                </GridContainer>
              </div>
            ))}
          </Collapse>
        </TableCell>
      </TableRow>
    </Fragment>
  );
};

export default CancellationDataPanel;
