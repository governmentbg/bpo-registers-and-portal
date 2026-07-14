import React from "react";
import RepresentativeHistoryPanel from "../../../common/history/RepresentativeHistoryPanel";
import { RegisterType } from "../../../../../../../../utils/constants";

const AgentHistoryPanel = ({ data }) => {
  return <RepresentativeHistoryPanel viewType={RegisterType.AGENT} data={data} />;
};
export default AgentHistoryPanel;
