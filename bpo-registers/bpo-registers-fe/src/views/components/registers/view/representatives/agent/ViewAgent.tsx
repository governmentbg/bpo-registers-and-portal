import View from "../../common/View";
import React, { useRef } from "react";
import { useParams } from "react-router-dom";
import { getAgentByAgentCode } from "../../../../../../axios/api/services";
import ViewAgentRenderer from "./panels/ViewAgentRenderer";
import { RepresentativeType } from "../../../../../../utils/constants";
import { Box } from "@mui/material";
import AgentSummary from "./panels/summary/AgentSummary";
import { useTranslation } from "react-i18next";

const ViewAgent = () => {
  const params = useParams();
  const viewId = params.id;
  const componentRef = useRef();
  const { i18n } = useTranslation();
  return (
    <Box ref={componentRef} key={"view-page-" + i18n.language}>
      <View
        serviceFn={() => getAgentByAgentCode(viewId, RepresentativeType.AGENT)}
        panelsRenderer={(data) => <ViewAgentRenderer data={data} />}
        summary={(data) => <AgentSummary data={data} componentToDownloadRef={componentRef} />}
      />
    </Box>
  );
};

export default ViewAgent;
