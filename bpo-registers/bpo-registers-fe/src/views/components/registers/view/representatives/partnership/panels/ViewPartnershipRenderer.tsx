import React from "react";
import { PARTNERSHIP_VIEW_PANEL_DEFINITION_TO_COMPONENT } from "../../../../../../../config/registers/view/panels/definition/panelToComponent/RepresentativePanelDefinitionToComponent";
import ViewRegisterRenderer from "../../../common/ViewRegisterRenderer";

const ViewPartnershipRenderer = ({ data }) => {
  return <ViewRegisterRenderer panelDenifitions={PARTNERSHIP_VIEW_PANEL_DEFINITION_TO_COMPONENT} data={data} />;
};

export default ViewPartnershipRenderer;
