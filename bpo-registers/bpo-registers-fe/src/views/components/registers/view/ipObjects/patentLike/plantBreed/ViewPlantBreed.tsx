import View from "../../../common/View";
import React, { useRef } from "react";
import { useParams } from "react-router-dom";
import { getPlantsBreedsById } from "../../../../../../../axios/api/services";
import ViewPlantBreedRenderer from "./panels/ViewPlantBreedRenderer";
import IpObjectSummary from "../../summary/IpObjectSummary";
import { RegisterType } from "../../../../../../../utils/constants";
import { Box } from "@mui/material";
import { useTranslation } from "react-i18next";

const ViewPlantBreed = () => {
  const params = useParams();
  const viewId = params.id;
  const componentRef = useRef();
  const { i18n } = useTranslation();
  return (
    <Box ref={componentRef} key={"view-page-" + i18n.language}>
      <View
        serviceFn={() => getPlantsBreedsById(viewId.replaceAll("_", "/"))}
        panelsRenderer={(data) => <ViewPlantBreedRenderer data={data} />}
        summary={(data) => (
          <IpObjectSummary data={data} registerType={RegisterType.PLANT_BREED} componentToDownloadRef={componentRef} />
        )}
      />
    </Box>
  );
};

export default ViewPlantBreed;
