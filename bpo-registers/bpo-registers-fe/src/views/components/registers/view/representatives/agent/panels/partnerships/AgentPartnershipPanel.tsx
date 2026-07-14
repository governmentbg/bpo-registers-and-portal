import React from "react";
import AgentPartnershipTable from "./section/AgentPartnershipTable";

const AgentPartnershipPanel = ({ data }) => {
  return <AgentPartnershipTable agents={data?.agentRelations} />;
};

export default AgentPartnershipPanel;
