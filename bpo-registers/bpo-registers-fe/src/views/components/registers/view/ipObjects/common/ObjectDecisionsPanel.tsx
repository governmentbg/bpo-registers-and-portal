import React, { Fragment } from "react";

import { Table } from "@mui/material";
import { useTranslation } from "react-i18next";
import TableContainer from "@mui/material/TableContainer/TableContainer";
import TableHead from "@mui/material/TableHead/TableHead";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell/TableCell";
import TableBody from "@mui/material/TableBody";
import { BlockText, useAsyncCall } from "@duosoftbg/bpo-components";
import { handleFileDownload } from "../../../../../../utils/functions";

const ObjectDecisionsPanel = ({ data }) => {
  const { asyncCall } = useAsyncCall();
  const { t } = useTranslation();
  return (
    <TableContainer>
      <Table aria-label="collapsible table">
        <TableHead>
          <TableRow>
            <TableCell>{t("l.table.head.decision.docType")}</TableCell>
            <TableCell>{t("l.table.head.decision.docNumber")}</TableCell>
            <TableCell>{t("l.table.head.decision.docDate")}</TableCell>
            <TableCell>{t("l.table.head.decision.link")}</TableCell>
          </TableRow>
        </TableHead>

        <TableBody>
          {data.decisions.map((decision) => (
            <DecisionDataRow key={decision.id} decision={decision} asyncCall={asyncCall} />
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

const DecisionDataRow = ({ decision, asyncCall }) => {
  return (
    <Fragment>
      <TableRow hover>
        <TableCell>{decision?.documentType?.name}</TableCell>
        <TableCell>{decision?.documentNumber}</TableCell>
        <TableCell>{decision?.documentDate}</TableCell>
        <TableCell>
          <BlockText
            propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
            withGrid
            label={""}
            text={decision?.attachment?.fileName}
            onClickFn={() =>
              handleFileDownload(
                decision?.attachment?.bucketName,
                decision?.attachment?.fileLocation,
                decision?.attachment?.fileName,
                asyncCall
              )
            }
          />
        </TableCell>
      </TableRow>
    </Fragment>
  );
};
export default ObjectDecisionsPanel;
