import { Grid } from "@mui/material";
import ContactPhoneIcon from "@mui/icons-material/ContactPhone";
import EmailIcon from "@mui/icons-material/Email";
import LanguageIcon from "@mui/icons-material/Language";
import { BlockText, InlineIconText, concatNotEmptyBy, DividerSpg } from "@duosoftbg/bpo-components";
import React from "react";

const AgentAddressInfo = ({ registerType, person }) => {
  const mainAddress = person.address;
  const agentAddress = person?.agent?.address;

  const addressBG = concatNotEmptyBy(", ")(
    mainAddress.residenceCountryCode?.name,
    mainAddress.zipCode,
    mainAddress.cityName,
    mainAddress.addressStreet
  );

  const addressEN = concatNotEmptyBy(", ")(
    mainAddress.residenceCountryCode?.nameEn,
    mainAddress.zipCode,
    agentAddress?.cityNameEn,
    agentAddress?.addressStreetEn
  );

  return (
    <>
      <DividerSpg my={4} />
      <Grid container direction="row">
        <Grid item xs={12} md={6} lg={8}>
          <BlockText label={`l.detail.official.address.${registerType}`} text={addressBG} textArray={[addressEN]} />
        </Grid>
        <Grid item xs={12} md={6} lg={4}>
          {person?.telephone && <InlineIconText icon={ContactPhoneIcon} text={person?.telephone} />}
          {person?.email && <InlineIconText icon={EmailIcon} text={person?.email} />}
          {agentAddress?.website && <InlineIconText icon={LanguageIcon} text={agentAddress?.website} />}
        </Grid>
      </Grid>
    </>
  );
};

export default AgentAddressInfo;
