import { PanelToComponentDefinition } from "../../../../../../../types/registers/view/panels/panelsDefinition";
import React from "react";
import LegalDecisionDataPanel from "../../../../../../../views/components/registers/view/decision/panels/LegalDecisionDataPanel";

export const DECISION_VIEW_PANEL_DEFINITION_TO_COMPONENT: PanelToComponentDefinition = [
  {
    id: "decisionData",
    label: "l.panel.decisionData",
    component: (data) => <LegalDecisionDataPanel data={data} />,
  },
];
