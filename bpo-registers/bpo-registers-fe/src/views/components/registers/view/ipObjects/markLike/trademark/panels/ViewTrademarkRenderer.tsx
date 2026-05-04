import React from "react";
import { TRADEMARK_VIEW_PANEL_DEFINITION_TO_COMPONENT } from "../../../../../../../../config/registers/view/panels/definition/panelToComponent/markLike/TrademarkPanelDefinitionToComponent";
import ViewRegisterRenderer from "../../../../common/ViewRegisterRenderer";

const ViewTrademarkRenderer = ({ data }) => {
  return <ViewRegisterRenderer panelDenifitions={TRADEMARK_VIEW_PANEL_DEFINITION_TO_COMPONENT} data={data} />;
};

export default ViewTrademarkRenderer;
