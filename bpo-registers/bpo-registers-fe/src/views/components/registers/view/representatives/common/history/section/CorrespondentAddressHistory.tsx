import { concatNotEmptyBy, DividerSpg, isEmpty } from "@duosoftbg/bpo-components";
import { Grid } from "@mui/material";
import { ContactPhone, Email, Language } from "@mui/icons-material";
import HistoryBlockText from "../HistoryBlockText";

const CorrespondentAddressHistory = ({ oldData, newData }) => {
  if (isEmpty(newData?.nameAddress?.address) && isEmpty(oldData?.nameAddress?.address)) {
    return null;
  }

  const newCaAddressBG = concatNotEmptyBy(", ")(
    newData?.nameAddress?.address?.caZipCode,
    newData?.nameAddress?.address?.caCity,
    newData?.nameAddress?.address?.caAddress
  );

  const oldCaAddressBG = concatNotEmptyBy(", ")(
    oldData?.nameAddress?.address?.caZipCode,
    oldData?.nameAddress?.address?.caCity,
    oldData?.nameAddress?.address?.caAddress
  );

  const newCaAddressEN = concatNotEmptyBy(", ")(
    newData?.nameAddress?.address?.caZipCode,
    newData?.nameAddress?.address?.caCityEn,
    newData?.nameAddress?.address?.caAddressEn
  );

  const oldCaAddressEN = concatNotEmptyBy(", ")(
    oldData?.nameAddress?.address?.caZipCode,
    oldData?.nameAddress?.address?.caCityEn,
    oldData?.nameAddress?.address?.caAddressEn
  );

  const emptyHistoryCa = isEmpty(
    concatNotEmptyBy(",")(
      newCaAddressBG,
      oldCaAddressBG,
      newCaAddressEN,
      oldCaAddressEN,
      newData?.nameAddress?.address?.caPhone,
      oldData?.nameAddress?.address?.caPhone,
      newData?.nameAddress?.address?.caEmail,
      oldData?.nameAddress?.address?.caEmail,
      newData?.nameAddress?.address?.caWebsite,
      oldData?.nameAddress?.address?.caWebsite
    )
  );

  if (emptyHistoryCa) {
    return null;
  }

  return (
    <>
      <DividerSpg my={4} />
      <Grid container direction={"row"}>
        <Grid item xs={12} md={6} lg={4}>
          <HistoryBlockText label={"l.detail.ca.address"} newText={newCaAddressBG} oldText={oldCaAddressBG} />
          <HistoryBlockText label={"l.detail.ca.addressEn"} newText={newCaAddressEN} oldText={oldCaAddressEN} />
        </Grid>
        <Grid item xs={12} md={6} lg={8}>
          <HistoryBlockText
            startIcon={<ContactPhone />}
            newText={newData?.nameAddress?.address?.caPhone}
            oldText={oldData?.nameAddress?.address?.caPhone}
          />
          <HistoryBlockText
            startIcon={<Email />}
            newText={newData?.nameAddress?.address?.caEmail}
            oldText={oldData?.nameAddress?.address?.caEmail}
          />
          <HistoryBlockText
            startIcon={<Language />}
            newText={newData?.nameAddress?.address?.caWebsite}
            oldText={oldData?.nameAddress?.address?.caWebsite}
          />
        </Grid>
      </Grid>
    </>
  );
};

export default CorrespondentAddressHistory;
