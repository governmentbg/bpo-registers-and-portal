import View from "../../common/View";
import ViewPartnershipRenderer from "./panels/ViewPartnershipRenderer";
import React, { useRef } from "react";
import PartnershipSummary from "./panels/summary/PartnershipSummary";
import { useParams } from "react-router-dom";
import { getAgentByAgentCode } from "../../../../../../axios/api/services";
import { RepresentativeType } from "../../../../../../utils/constants";
import { Box } from "@mui/material";
import { useTranslation } from "react-i18next";

const ViewPartnership = () => {
  const params = useParams();
  const viewId = params.id;
  const componentRef = useRef();
  const { i18n } = useTranslation();
  return (
    <Box ref={componentRef} key={"view-page-" + i18n.language}>
      <View
        serviceFn={() => getAgentByAgentCode(viewId, RepresentativeType.PARTNERSHIP)}
        panelsRenderer={(data) => <ViewPartnershipRenderer data={data} />}
        summary={(data) => <PartnershipSummary data={data} componentToDownloadRef={componentRef} />}
      />
    </Box>
  );
};

export default ViewPartnership;
