import { Grid } from "@mui/material";
import ContactPhoneIcon from "@mui/icons-material/ContactPhone";
import EmailIcon from "@mui/icons-material/Email";
import { BlockText, InlineIconText, concatNotEmptyBy, DividerSpg } from "@duosoftbg/bpo-components";
import React from "react";

const CorrespondentAddressInfo = ({ agentAddress }) => {
  const addressBG = concatNotEmptyBy(", ")(
    agentAddress?.zipCodeCa,
    agentAddress?.addressStreetCa,
    agentAddress?.cityNameCa
  );
  const addressEN = concatNotEmptyBy(", ")(
    agentAddress?.zipCodeCa,
    agentAddress?.addressStreetCaEn,
    agentAddress?.cityNameCaEn
  );

  return (
    <>
      <DividerSpg my={4} />
      <Grid container direction="row">
        <Grid item xs={12} md={6} lg={8}>
          <BlockText label={"l.detail.ca.address"} text={addressBG} textArray={[addressEN]} />
        </Grid>
        <Grid item xs={12} md={6} lg={4}>
          {agentAddress?.phoneCa && <InlineIconText icon={ContactPhoneIcon} text={agentAddress?.phoneCa} />}
          {agentAddress?.emailCa && <InlineIconText icon={EmailIcon} text={agentAddress?.emailCa} />}
        </Grid>
      </Grid>
    </>
  );
};

export default CorrespondentAddressInfo;
