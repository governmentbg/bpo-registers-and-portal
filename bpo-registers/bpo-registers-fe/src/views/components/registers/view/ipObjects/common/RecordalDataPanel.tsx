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

const RecordalDataPanel = ({ data }) => {
  const { t } = useTranslation();

  return (
    <TableContainer>
      <Table aria-label="collapsible table">
        <TableHead>
          <TableRow>
            <TableCell style={{ width: 35 }} />
            <TableCell>{t("l.table.head.recordalNumber")}</TableCell>
            <TableCell>{t("l.table.head.recordalType")}</TableCell>
            <TableCell>{t("l.table.head.recordalDate")}</TableCell>
            <TableCell>{t("l.table.head.invalidationDate")}</TableCell>
          </TableRow>
        </TableHead>

        <TableBody>
          {data.recordals.map((recodal, index) => (
            <RecordalDataRow key={recodal.id} recordal={recodal} />
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

const RecordalDataRow = ({ recordal }) => {
  const [openDetailRow, setOpenDetailRow] = useState(false);
  const hasDetails = isArrayNotEmpty(recordal.details);
  const { t } = useTranslation();

  return (
    <Fragment>
      <TableRow hover>
        <OptionTableCell>
          {hasDetails && (
            <IconButton aria-label="expand row" size="small" onClick={() => setOpenDetailRow(!openDetailRow)}>
              {openDetailRow ? <KeyboardArrowUp /> : <KeyboardArrowDown />}
            </IconButton>
          )}
        </OptionTableCell>
        <TableCell>{recordal.recordalNumber}</TableCell>
        <TableCell>{recordal.recordalType.description}</TableCell>
        <TableCell>
          {recordal.registrationDate ? recordal.registrationDate : t("l.table.head.recordalDate.empty")}
        </TableCell>
        <TableCell>{recordal.invalidationDate}</TableCell>
      </TableRow>
      <TableRow style={{ background: "rgb(251, 250, 250)" }}>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={7}>
          <Collapse in={openDetailRow} timeout="auto" unmountOnExit>
            {recordal.details.map((detail, index) => (
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

export default RecordalDataPanel;
