import React from "react";
import { GEO_INDICATION_VIEW_PANEL_DEFINITION_TO_COMPONENT } from "../../../../../../../../config/registers/view/panels/definition/panelToComponent/markLike/GeoIndicationPanelDefinitionToComponent";
import ViewRegisterRenderer from "../../../../common/ViewRegisterRenderer";

const ViewGeoIndicationRenderer = ({ data }) => {
  return <ViewRegisterRenderer panelDenifitions={GEO_INDICATION_VIEW_PANEL_DEFINITION_TO_COMPONENT} data={data} />;
};

export default ViewGeoIndicationRenderer;
