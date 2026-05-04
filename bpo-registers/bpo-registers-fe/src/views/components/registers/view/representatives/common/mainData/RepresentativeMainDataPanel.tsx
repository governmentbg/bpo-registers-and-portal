import { BlockText, GridContainer, isNotEmpty } from "@duosoftbg/bpo-components";
import AddressInfo from "./section/AddressInfo";
import CorrespondentAddressInfo from "./section/CorrespondentAddressInfo";
import React from "react";
import { isAgentCaDataNotEmpty, RegisterType } from "../../../../../../../utils/constants";

const RepresentativeMainDataPanel = ({ data, registerType }) => {
  const agent = data?.agent;
  const notEmptyCaData = isAgentCaDataNotEmpty(agent);

  return (
    <div className={"main-data-panel"}>
      <GridContainer spacing={3}>
        <BlockText
          withGrid
          label={`l.detail.agentName.${registerType}`}
          text={agent.name}
          textArray={[agent.agent?.nameEn]}
        />
        {registerType === RegisterType.PARTNERSHIP && (
          <BlockText
            withGrid
            label={"l.detail.agentType"}
            text={agent.agent?.representativeType?.description}
            textArray={[agent.agent?.representativeType?.descriptionEn]}
          />
        )}
        <BlockText
          withGrid
          label={"l.detail.ipoArea"}
          text={agent.agent?.agentSpeciality?.name}
          textArray={[agent.agent?.agentSpeciality?.nameEn]}
        />
        {registerType === RegisterType.AGENT && (
          <BlockText
            withGrid
            label={"l.detail.certification.country"}
            text={agent.agent?.qualifCountryCode?.name}
            textArray={[agent.agent?.qualifCountryCode?.nameEn]}
          />
        )}
        <BlockText
          withGrid
          label={`l.detail.origin.country.${registerType}`}
          text={agent.nationalityCountryCode?.name}
          textArray={[agent.nationalityCountryCode?.nameEn]}
        />
        {registerType === RegisterType.AGENT && (
          <BlockText
            withGrid
            label={"l.detail.speciality"}
            text={agent.agent?.speciality}
            textArray={[agent.agent?.specialityEn]}
          />
        )}
      </GridContainer>

      {isNotEmpty(agent.address) && <AddressInfo registerType={registerType} person={agent} />}

      {notEmptyCaData && <CorrespondentAddressInfo agentAddress={agent?.agent?.address} />}
    </div>
  );
};
export default RepresentativeMainDataPanel;
