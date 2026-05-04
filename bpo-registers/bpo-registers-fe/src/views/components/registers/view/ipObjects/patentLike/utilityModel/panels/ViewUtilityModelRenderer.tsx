import React from "react";
import { UTILITY_MODEL_VIEW_PANEL_DEFINITION_TO_COMPONENT } from "../../../../../../../../config/registers/view/panels/definition/panelToComponent/patentLike/UtilityModelPanelDefinitionToComponent";
import ViewRegisterRenderer from "../../../../common/ViewRegisterRenderer";

const ViewUtilityModelRenderer = ({ data }) => {
  return <ViewRegisterRenderer panelDenifitions={UTILITY_MODEL_VIEW_PANEL_DEFINITION_TO_COMPONENT} data={data} />;
};

export default ViewUtilityModelRenderer;
