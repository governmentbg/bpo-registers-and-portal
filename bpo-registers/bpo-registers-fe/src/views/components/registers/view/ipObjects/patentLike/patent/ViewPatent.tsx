import View from "../../../common/View";
import React, { useRef } from "react";
import { useParams } from "react-router-dom";
import { getPatentById } from "../../../../../../../axios/api/services";
import ViewPatentRenderer from "./panels/ViewPatentRenderer";
import IpObjectSummary from "../../summary/IpObjectSummary";
import { RegisterType } from "../../../../../../../utils/constants";
import { Box } from "@mui/material";
import { useTranslation } from "react-i18next";

const ViewPatent = () => {
  const params = useParams();
  const viewId = params.id;
  const componentRef = useRef();
  const { i18n } = useTranslation();
  return (
    <Box ref={componentRef} key={"view-page-" + i18n.language}>
      <View
        serviceFn={() => getPatentById(viewId.replaceAll("_", "/"))}
        panelsRenderer={(data) => <ViewPatentRenderer data={data} />}
        summary={(data) => (
          <IpObjectSummary data={data} registerType={RegisterType.PATENT} componentToDownloadRef={componentRef} />
        )}
      />
    </Box>
  );
};

export default ViewPatent;
