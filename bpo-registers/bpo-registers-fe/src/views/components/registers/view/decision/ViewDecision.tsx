import React, { useRef } from "react";
import { useParams } from "react-router-dom";
import ViewDecisionRenderer from "./panels/ViewDecisionRenderer";
import { Box } from "@mui/material";
import { getDecisionById } from "../../../../../axios/api/services";
import View from "../common/View";
import LegalDecisionSummary from "../ipObjects/summary/LegalDecisionSummary";
import { useTranslation } from "react-i18next";

const ViewDecision = () => {
  const params = useParams();
  const viewId = params.id;
  const componentRef = useRef();
  const { i18n } = useTranslation();
  return (
    <Box ref={componentRef} key={"view-page-" + i18n.language}>
      <View
        serviceFn={() => getDecisionById(viewId.replaceAll("_", "/"))}
        panelsRenderer={(data) => <ViewDecisionRenderer data={data} />}
        summary={(data) => <LegalDecisionSummary data={data} />}
      />
    </Box>
  );
};

export default ViewDecision;
