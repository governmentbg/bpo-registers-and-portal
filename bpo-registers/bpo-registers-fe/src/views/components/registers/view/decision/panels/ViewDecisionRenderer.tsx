import React from "react";
import ViewRegisterRenderer from "../../common/ViewRegisterRenderer";
import { DECISION_VIEW_PANEL_DEFINITION_TO_COMPONENT } from "../../../../../../config/registers/view/panels/definition/panelToComponent/decision/DecisionPanelDefinitionToComponent";

const ViewDecisionRenderer = ({ data }) => {
  return <ViewRegisterRenderer panelDenifitions={DECISION_VIEW_PANEL_DEFINITION_TO_COMPONENT} data={data} />;
};

export default ViewDecisionRenderer;
