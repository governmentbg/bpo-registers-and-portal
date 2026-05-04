import React from "react";
import ViewRegisterRenderer from "../../../../common/ViewRegisterRenderer";
import { DESIGN_VIEW_PANEL_DEFINITION_TO_COMPONENT } from "../../../../../../../../config/registers/view/panels/definition/panelToComponent/patentLike/DesignPanelDefinitionToComponent";

const ViewDesignRenderer = ({ data }) => {
  return <ViewRegisterRenderer panelDenifitions={DESIGN_VIEW_PANEL_DEFINITION_TO_COMPONENT} data={data} />;
};

export default ViewDesignRenderer;
