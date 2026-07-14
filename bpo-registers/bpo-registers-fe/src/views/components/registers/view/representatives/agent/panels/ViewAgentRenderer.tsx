import React from "react";
import { AGENT_VIEW_PANEL_DEFINITION_TO_COMPONENT } from "../../../../../../../config/registers/view/panels/definition/panelToComponent/RepresentativePanelDefinitionToComponent";
import ViewRegisterRenderer from "../../../common/ViewRegisterRenderer";

const ViewAgentRenderer = ({ data }) => {
  return <ViewRegisterRenderer panelDenifitions={AGENT_VIEW_PANEL_DEFINITION_TO_COMPONENT} data={data} />;
};

export default ViewAgentRenderer;
