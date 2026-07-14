import View from "../../../common/View";
import React, { useRef } from "react";
import { useParams } from "react-router-dom";
import { getSpcById } from "../../../../../../../axios/api/services";
import ViewSpcRenderer from "./panels/ViewSpcRenderer";
import IpObjectSummary from "../../summary/IpObjectSummary";
import { RegisterType } from "../../../../../../../utils/constants";
import { Box } from "@mui/material";
import { useTranslation } from "react-i18next";

const ViewSpc = () => {
  const params = useParams();
  const viewId = params.id;
  const componentRef = useRef();
  const { i18n } = useTranslation();
  return (
    <Box ref={componentRef} key={"view-page-" + i18n.language}>
      <View
        serviceFn={() => getSpcById(viewId.replaceAll("_", "/"))}
        panelsRenderer={(data) => <ViewSpcRenderer data={data} />}
        summary={(data) => (
          <IpObjectSummary data={data} registerType={RegisterType.SPC} componentToDownloadRef={componentRef} />
        )}
      />
    </Box>
  );
};

export default ViewSpc;
