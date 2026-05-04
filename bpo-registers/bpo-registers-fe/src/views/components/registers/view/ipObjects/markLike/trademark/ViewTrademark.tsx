import View from "../../../common/View";
import React, { useRef } from "react";
import { useParams } from "react-router-dom";
import { getTrademarkById } from "../../../../../../../axios/api/services";
import ViewTrademarkRenderer from "./panels/ViewTrademarkRenderer";
import IpObjectSummary from "../../summary/IpObjectSummary";
import { RegisterType } from "../../../../../../../utils/constants";
import { Box } from "@mui/material";
import { useTranslation } from "react-i18next";

const ViewTrademark = () => {
  const params = useParams();
  const viewId = params.id;
  const componentRef = useRef();
  const { i18n } = useTranslation();
  return (
    <Box ref={componentRef} key={"view-page-" + i18n.language}>
      <View
        serviceFn={() => getTrademarkById(viewId.replaceAll("_", "/"))}
        panelsRenderer={(data) => <ViewTrademarkRenderer data={data} />}
        summary={(data) => (
          <IpObjectSummary data={data} registerType={RegisterType.MARK} componentToDownloadRef={componentRef} />
        )}
      />
    </Box>
  );
};

export default ViewTrademark;
