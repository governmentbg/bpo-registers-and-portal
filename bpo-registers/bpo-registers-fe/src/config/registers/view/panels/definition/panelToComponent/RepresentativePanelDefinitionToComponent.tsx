import React from "react";
import PartnershipAgentPanel from "../../../../../../views/components/registers/view/representatives/partnership/panels/agents/PartnershipAgentPanel";
import { isArrayEmpty } from "@duosoftbg/bpo-components";
import PartnershipHistoryPanel from "../../../../../../views/components/registers/view/representatives/partnership/panels/history/PartnershipHistoryPanel";
import { PanelToComponentDefinition } from "../../../../../../types/registers/view/panels/panelsDefinition";
import RepresentativeMainDataPanel from "../../../../../../views/components/registers/view/representatives/common/mainData/RepresentativeMainDataPanel";
import { RegisterType } from "../../../../../../utils/constants";
import AgentPartnershipPanel from "../../../../../../views/components/registers/view/representatives/agent/panels/partnerships/AgentPartnershipPanel";
import AgentHistoryPanel from "../../../../../../views/components/registers/view/representatives/agent/panels/history/AgentHistoryPanel";

export const PARTNERSHIP_VIEW_PANEL_DEFINITION_TO_COMPONENT: PanelToComponentDefinition = [
  {
    id: "partnershipMainData",
    label: "l.panel.partnershipMainData",
    component: (data) => <RepresentativeMainDataPanel data={data} registerType={RegisterType.PARTNERSHIP} />,
  },
  {
    id: "partnershipAgents",
    label: "l.panel.partnershipAgents",
    component: (data) => <PartnershipAgentPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.agentRelations),
  },
  {
    id: "partnershipHistory",
    label: "t.panel.history",
    component: (data) => <PartnershipHistoryPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.agentHistory),
  },
];

export const AGENT_VIEW_PANEL_DEFINITION_TO_COMPONENT: PanelToComponentDefinition = [
  {
    id: "agentMainData",
    label: "l.panel.agentMainData",
    component: (data) => <RepresentativeMainDataPanel data={data} registerType={RegisterType.AGENT} />,
  },
  {
    id: "agentPartnerships",
    label: "l.panel.agentPartnerships",
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.agentRelations),
    component: (data) => <AgentPartnershipPanel data={data} />,
  },
  {
    id: "agentHistory",
    label: "t.panel.history",
    component: (data) => <AgentHistoryPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.agentHistory),
  },
];
