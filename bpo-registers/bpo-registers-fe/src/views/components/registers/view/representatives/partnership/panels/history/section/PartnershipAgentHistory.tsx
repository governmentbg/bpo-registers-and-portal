import { concatNotEmptyBy, DividerSpg, isArrayEmpty, isArrayNotEmpty } from "@duosoftbg/bpo-components";
import React from "react";
import HistoryBlockText from "../../../../common/history/HistoryBlockText";

const PartnershipAgentHistory = ({ oldData, newData }) => {
  if (isArrayEmpty(newData?.partnershipMembers) && isArrayEmpty(oldData?.partnershipMembers)) {
    return null;
  }

  const hasMembersChanged = (oldMembers, newMembers) => {
    if (isArrayNotEmpty(oldMembers) && isArrayNotEmpty(newMembers)) {
      if (oldMembers.length === newMembers.length) {
        return oldMembers.every((oldMember) => {
          return !!newMembers.some((newMember) => newMember.agentCode === oldMember.agentCode);
        });
      }
    }
    return false;
  };

  const formatMemberRecords = (members) => {
    if (isArrayNotEmpty(members)) {
      let formattedArray = members.map((item) => `${item["agentName"]} : ${item["agentCode"]}`);
      return concatNotEmptyBy("; ")(...formattedArray);
    }
  };

  const oldMembers = oldData?.partnershipMembers;
  const newMembers = newData?.partnershipMembers;

  return (
    <>
      <DividerSpg my={4} />
      <HistoryBlockText
        withGrid
        label={"l.detail.partnership.participants"}
        newText={formatMemberRecords(newMembers)}
        oldText={formatMemberRecords(oldMembers)}
        historyArrayMatch={hasMembersChanged(oldMembers, newMembers)}
        propsGrid={{ md: 12, lg: 12 }}
      />
    </>
  );
};
export default PartnershipAgentHistory;
