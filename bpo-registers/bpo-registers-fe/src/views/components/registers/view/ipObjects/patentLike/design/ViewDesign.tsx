import View from "../../../common/View";
import React, { useRef } from "react";
import { useParams } from "react-router-dom";
import { getDesignById } from "../../../../../../../axios/api/services";
import ViewDesignRenderer from "./panels/ViewDesignRenderer";
import IpObjectSummary from "../../summary/IpObjectSummary";
import { RegisterType } from "../../../../../../../utils/constants";
import { Box } from "@mui/material";
import { useTranslation } from "react-i18next";

const ViewDesign = () => {
  const params = useParams();
  const viewId = params.id;
  const componentRef = useRef();
  const { i18n } = useTranslation();
  return (
    <Box ref={componentRef} key={"view-page-" + i18n.language}>
      <View
        serviceFn={() => getDesignById(viewId.replaceAll("_", "/"))}
        panelsRenderer={(data) => <ViewDesignRenderer data={data} />}
        summary={(data) => (
          <IpObjectSummary data={data} registerType={RegisterType.DESIGN} componentToDownloadRef={componentRef} />
        )}
      />
    </Box>
  );
};

export default ViewDesign;
