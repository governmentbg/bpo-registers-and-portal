import { GridItem, InputFormField } from "@duosoftbg/bpo-components";
import { Typography } from "@mui/material";
import React from "react";
import { useTranslation } from "react-i18next";

const DecisionDocumentNumberSearchFilter = () => {
  const { t } = useTranslation();
  return (
    <>
      <GridItem sm={6} md={6} lg={6}>
        <InputFormField fieldName={"documentNumber"} labelCode={"l.searchFilter.decisionDocumentNumber"} />
      </GridItem>
      <GridItem sm={6} md={6} lg={6}>
        <Typography variant="body2" component={"div"} align={"left"}>
          {t("l.missing.decision.nbrs")}
        </Typography>
      </GridItem>
    </>
  );
};
export default DecisionDocumentNumberSearchFilter;
