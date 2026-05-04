import View from "../../../common/View";
import React, { useRef } from "react";
import { useParams } from "react-router-dom";
import { getEuPatentById } from "../../../../../../../axios/api/services";
import ViewEuPatentRenderer from "./panels/ViewEuPatentRenderer";
import IpObjectSummary from "../../summary/IpObjectSummary";
import { RegisterType } from "../../../../../../../utils/constants";
import { Box } from "@mui/material";
import { useTranslation } from "react-i18next";

const ViewEuPatent = () => {
  const params = useParams();
  const viewId = params.id;
  const componentRef = useRef();
  const { i18n } = useTranslation();
  return (
    <Box ref={componentRef} key={"view-page-" + i18n.language}>
      <View
        serviceFn={() => getEuPatentById(viewId.replaceAll("_", "/"))}
        panelsRenderer={(data) => <ViewEuPatentRenderer data={data} />}
        summary={(data) => (
          <IpObjectSummary data={data} registerType={RegisterType.EU_PATENT} componentToDownloadRef={componentRef} />
        )}
      />
    </Box>
  );
};

export default ViewEuPatent;
