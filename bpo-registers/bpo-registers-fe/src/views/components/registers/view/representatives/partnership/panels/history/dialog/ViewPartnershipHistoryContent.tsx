import React from "react";
import { BlockText, GridContainer, GridItem, iTxt } from "@duosoftbg/bpo-components";
import i18n from "i18next";
import { Typography } from "@mui/material";
import HistoryBlockText from "../../../../common/history/HistoryBlockText";
import AddressHistory from "../../../../common/history/section/AddressHistory";
import CorrespondentAddressHistory from "../../../../common/history/section/CorrespondentAddressHistory";
import PartnershipAgentHistory from "../section/PartnershipAgentHistory";
import { RegisterType } from "../../../../../../../../../utils/constants";
import { i18nKeyByCode } from "../../../../../../../../../utils/labels";

const ViewPartnershipHistoryContent = ({ history }) => {
  const viewType = RegisterType.PARTNERSHIP;

  const oldData = history?.historyRecord?.oldData?.partnership;
  const newData = history?.historyRecord?.newData?.partnership;

  const invalidatedStatus =
    history?.historyType?.id === "RECORD_INVALIDATED" || history?.historyType?.id === "RECORD_TEMPORARY_INACTIVATED";

  return (
    <>
      <GridContainer mt={0}>
        <GridItem xs={12} sm={12} md={12}>
          <Typography align={"center"} fontSize={14} ml={4}>
            {history.historyTimestamp}
          </Typography>
        </GridItem>
      </GridContainer>
      <GridContainer spacing={2}>
        <HistoryBlockText
          withGrid
          label={"l.detail.agentCode"}
          newText={newData?.agentCode}
          oldText={oldData?.agentCode}
        />
        <HistoryBlockText
          withGrid
          label={"l.detail.agentType"}
          newText={iTxt(
            i18n.language,
            newData?.representativeType?.description,
            newData?.representativeType?.descriptionEn
          )}
          oldText={iTxt(
            i18n.language,
            oldData?.representativeType?.description,
            oldData?.representativeType?.descriptionEn
          )}
        />
        {/*TODO - get history invalidation status and remove historyType check???*/}
        {invalidatedStatus && (
          <BlockText
            withGrid
            label={"l.detail.status"}
            text={iTxt(i18n.language, history?.historyType?.name, history?.historyType?.nameEn)}
          />
        )}
        <HistoryBlockText
          withGrid
          label={i18nKeyByCode(viewType, "l.detail.agentName")}
          newText={newData?.nameAddress?.name}
          oldText={oldData?.nameAddress.name}
        />
        <HistoryBlockText
          withGrid
          label={i18nKeyByCode(viewType, "l.detail.agentNameEn")}
          newText={newData?.nameAddress?.nameEn}
          oldText={oldData?.nameAddress?.nameEn}
        />
        <HistoryBlockText
          withGrid
          label={"l.detail.ipoArea"}
          newText={iTxt(i18n.language, newData?.agentSpeciality?.name, newData?.agentSpeciality?.nameEn)}
          oldText={iTxt(i18n.language, oldData?.agentSpeciality?.name, oldData?.agentSpeciality?.nameEn)}
        />
        <HistoryBlockText
          withGrid
          label={"l.detail.origin.country"}
          newText={newData?.oriCountryCode}
          oldText={oldData?.oriCountryCode}
        />
      </GridContainer>
      <AddressHistory viewType={viewType} oldData={oldData} newData={newData} />
      <CorrespondentAddressHistory oldData={oldData} newData={newData} />
      <PartnershipAgentHistory oldData={oldData} newData={newData} />
    </>
  );
};

export default ViewPartnershipHistoryContent;
