import React from "react";
import PartnershipAgentTable from "./section/PartnershipAgentTable";

const PartnershipAgentPanel = ({ data }) => {
  return <PartnershipAgentTable agents={data?.agentRelations} />;
};

export default PartnershipAgentPanel;
