import View from "../../../common/View";
import React, { useRef } from "react";
import { useParams } from "react-router-dom";
import { getGeoIndicationById } from "../../../../../../../axios/api/services";
import ViewGeoIndicationRenderer from "./panels/ViewGeoIndicationRenderer";
import IpObjectSummary from "../../summary/IpObjectSummary";
import { RegisterType } from "../../../../../../../utils/constants";
import { Box } from "@mui/material";
import { useTranslation } from "react-i18next";

const ViewGeoIndication = () => {
  const params = useParams();
  const viewId = params.id;
  const componentRef = useRef();
  const { i18n } = useTranslation();
  return (
    <Box ref={componentRef} key={"view-page-" + i18n.language}>
      <View
        serviceFn={() => getGeoIndicationById(viewId.replaceAll("_", "/"))}
        panelsRenderer={(data) => <ViewGeoIndicationRenderer data={data} />}
        summary={(data) => (
          <IpObjectSummary
            data={data}
            registerType={RegisterType.GEO_INDICATION}
            componentToDownloadRef={componentRef}
          />
        )}
      />
    </Box>
  );
};

export default ViewGeoIndication;
