import React from "react";
import RepresentativeHistoryPanel from "../../../common/history/RepresentativeHistoryPanel";
import { RegisterType } from "../../../../../../../../utils/constants";

const PartnershipHistoryPanel = ({ data }) => {
  return <RepresentativeHistoryPanel viewType={RegisterType.PARTNERSHIP} data={data} />;
};
export default PartnershipHistoryPanel;
