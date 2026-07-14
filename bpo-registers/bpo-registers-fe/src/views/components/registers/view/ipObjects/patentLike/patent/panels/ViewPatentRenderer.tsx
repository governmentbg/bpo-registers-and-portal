import React from "react";
import { PATENT_VIEW_PANEL_DEFINITION_TO_COMPONENT } from "../../../../../../../../config/registers/view/panels/definition/panelToComponent/patentLike/PatentPanelDefinitionToComponent";
import ViewRegisterRenderer from "../../../../common/ViewRegisterRenderer";

const ViewPatentRenderer = ({ data }) => {
  return <ViewRegisterRenderer panelDenifitions={PATENT_VIEW_PANEL_DEFINITION_TO_COMPONENT} data={data} />;
};

export default ViewPatentRenderer;
