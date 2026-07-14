import React from "react";
import { EU_PATENT_VIEW_PANEL_DEFINITION_TO_COMPONENT } from "../../../../../../../../config/registers/view/panels/definition/panelToComponent/patentLike/EuPatentPanelDefinitionToComponent";
import ViewRegisterRenderer from "../../../../common/ViewRegisterRenderer";

const ViewEuPatentRenderer = ({ data }) => {
  return <ViewRegisterRenderer panelDenifitions={EU_PATENT_VIEW_PANEL_DEFINITION_TO_COMPONENT} data={data} />;
};

export default ViewEuPatentRenderer;
