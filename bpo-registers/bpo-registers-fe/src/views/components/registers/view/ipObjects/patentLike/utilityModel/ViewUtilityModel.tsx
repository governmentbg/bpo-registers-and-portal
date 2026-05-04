import View from "../../../common/View";
import React, { useRef } from "react";
import { useParams } from "react-router-dom";
import { getUtilityModelById } from "../../../../../../../axios/api/services";
import ViewUtilityModelRenderer from "./panels/ViewUtilityModelRenderer";
import IpObjectSummary from "../../summary/IpObjectSummary";
import { RegisterType } from "../../../../../../../utils/constants";
import { Box } from "@mui/material";
import { useTranslation } from "react-i18next";

const ViewUtilityModel = () => {
  const params = useParams();
  const viewId = params.id;
  const componentRef = useRef();
  const { i18n } = useTranslation();
  return (
    <Box ref={componentRef} key={"view-page-" + i18n.language}>
      <View
        serviceFn={() => getUtilityModelById(viewId.replaceAll("_", "/"))}
        panelsRenderer={(data) => <ViewUtilityModelRenderer data={data} />}
        summary={(data) => (
          <IpObjectSummary
            data={data}
            registerType={RegisterType.UTILITY_MODEL}
            componentToDownloadRef={componentRef}
          />
        )}
      />
    </Box>
  );
};

export default ViewUtilityModel;
