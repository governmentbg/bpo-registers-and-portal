import React from "react";
import { SPC_VIEW_PANEL_DEFINITION_TO_COMPONENT } from "../../../../../../../../config/registers/view/panels/definition/panelToComponent/patentLike/SpcPanelDefinitionToComponent";
import ViewRegisterRenderer from "../../../../common/ViewRegisterRenderer";

const ViewSpcRenderer = ({ data }) => {
  return <ViewRegisterRenderer panelDenifitions={SPC_VIEW_PANEL_DEFINITION_TO_COMPONENT} data={data} />;
};

export default ViewSpcRenderer;
