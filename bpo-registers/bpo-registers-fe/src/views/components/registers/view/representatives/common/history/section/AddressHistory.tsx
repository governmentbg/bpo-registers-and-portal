import { concatNotEmptyBy, DividerSpg, isEmpty } from "@duosoftbg/bpo-components";
import { Grid } from "@mui/material";
import { ContactPhone, Email, Language } from "@mui/icons-material";
import HistoryBlockText from "../HistoryBlockText";
import { i18nKeyByCode } from "../../../../../../../../utils/labels";

const AddressHistory = ({ viewType, oldData, newData }) => {
  if (isEmpty(newData?.nameAddress?.address) && isEmpty(oldData?.nameAddress?.address)) {
    return null;
  }

  const newAddressBG = concatNotEmptyBy(", ")(
    newData?.nameAddress?.address?.countryCode,
    newData?.nameAddress?.address?.zipCode,
    newData?.nameAddress?.address?.cityName,
    newData?.nameAddress?.address?.addressStreet
  );

  const oldAddressBG = concatNotEmptyBy(", ")(
    oldData?.nameAddress?.address?.countryCode,
    oldData?.nameAddress?.address?.zipCode,
    oldData?.nameAddress?.address?.cityName,
    oldData?.nameAddress?.address?.addressStreet
  );

  const newAddressEN = concatNotEmptyBy(", ")(
    newData?.nameAddress?.address?.countryCode,
    newData?.nameAddress?.address?.zipCode,
    newData?.nameAddress?.address?.cityNameEn,
    newData?.nameAddress?.address?.addressStreetEn
  );

  const oldAddressEN = concatNotEmptyBy(", ")(
    oldData?.nameAddress?.address?.countryCode,
    oldData?.nameAddress?.address?.zipCode,
    oldData?.nameAddress?.address?.cityNameEn,
    oldData?.nameAddress?.address?.addressStreetEn
  );

  return (
    <>
      <DividerSpg my={4} />
      <Grid container direction={"row"}>
        <Grid item xs={12} md={6} lg={4}>
          <HistoryBlockText
            label={i18nKeyByCode(viewType, "l.detail.official.address")}
            newText={newAddressBG}
            oldText={oldAddressBG}
          />
          <HistoryBlockText
            label={i18nKeyByCode(viewType, "l.detail.official.addressEn")}
            newText={newAddressEN}
            oldText={oldAddressEN}
          />
        </Grid>
        <Grid item xs={12} md={6} lg={8}>
          <HistoryBlockText
            startIcon={<ContactPhone />}
            newText={newData?.nameAddress?.address?.phone}
            oldText={oldData?.nameAddress?.address?.phone}
          />
          <HistoryBlockText
            startIcon={<Email />}
            newText={newData?.nameAddress?.address?.email}
            oldText={oldData?.nameAddress?.address?.email}
          />
          <HistoryBlockText
            startIcon={<Language />}
            newText={newData?.nameAddress?.address?.website}
            oldText={oldData?.nameAddress?.address?.website}
          />
        </Grid>
      </Grid>
    </>
  );
};

export default AddressHistory;
