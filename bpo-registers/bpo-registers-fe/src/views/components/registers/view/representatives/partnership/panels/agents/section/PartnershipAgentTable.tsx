import { getI18n, useTranslation } from "react-i18next";
import { AlertSpg, GridSpg, isArrayEmpty, iTxt, OptionTableCell, RelativeBox } from "@duosoftbg/bpo-components";
import React from "react";
import { Box, Grid, Table, TableBody, TableContainer, TableHead, TableRow } from "@mui/material";
import TableCell from "@mui/material/TableCell";
import TableButton from "../../../../../../../common/table/TableButton";

const headCells = [
  {
    id: "agentName",
    label: "l.table.head.agentName",
  },
  {
    id: "agentCode",
    label: "l.table.head.agentCode",
  },
  {
    id: "agentSpeciality",
    label: "l.table.head.agentSpeciality",
  },
  {
    id: "ipoArea",
    label: "l.table.head.ipoArea",
  },
  {
    id: "options",
    label: "",
  },
];

const PartnershipAgentTable = ({ agents }) => {
  const { t } = useTranslation();
  const i18n = getI18n();

  const showTable = () => {
    if (isArrayEmpty(agents)) {
      return <AlertSpg severity="info">{t("m.empty.list")}</AlertSpg>;
    }

    return (
      <RelativeBox>
        <TableContainer>
          <Table sx={{ minWidth: 750 }} size={"small"}>
            <TableHead>
              <TableRow>
                {headCells.map((headCell) => (
                  <TableCell key={headCell.id}>{t(headCell.label)}</TableCell>
                ))}
              </TableRow>
            </TableHead>
            <TableBody>
              {agents.map((row) => {
                return (
                  <TableRow hover key={row.agent?.agentCode}>
                    <TableCell>{iTxt(i18n.language, row.name, row.agent?.nameEn)}</TableCell>
                    <TableCell>{row.agent?.agentCode}</TableCell>
                    <TableCell>{iTxt(i18n.language, row.agent?.speciality, row.agent?.specialityEn)}</TableCell>
                    <TableCell>
                      {iTxt(i18n.language, row.agent?.agentSpeciality?.name, row.agent?.agentSpeciality?.nameEn)}
                    </TableCell>
                    <OptionTableCell className={"docx-hidable"}>
                      <TableButton to={`/agents/view/${row.agent?.agentCode}`} />
                    </OptionTableCell>
                  </TableRow>
                );
              })}
            </TableBody>
          </Table>
        </TableContainer>
      </RelativeBox>
    );
  };

  return (
    <Box>
      <Grid container spacing={1}>
        <GridSpg item xs={12} m={2}>
          {showTable()}
        </GridSpg>
      </Grid>
    </Box>
  );
};

export default PartnershipAgentTable;
