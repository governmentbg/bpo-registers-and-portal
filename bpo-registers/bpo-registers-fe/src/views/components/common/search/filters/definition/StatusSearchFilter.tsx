import { GridItem, AsyncAutocompleteFormField, iTxt } from "@duosoftbg/bpo-components";
import { useWatch } from "react-hook-form";
import { getObjectStatuses } from "../../../../../../axios/api/services";
import { useTranslation } from "react-i18next";

const StatusSearchFilter = () => {
  const objectRange = useWatch({ name: "objectRange" });
  const { i18n } = useTranslation();

  return (
    <>
      <GridItem sm={6} md={6} lg={6}>
        <AsyncAutocompleteFormField
          label={"l.searchFilter.status.id"}
          asyncFn={getObjectStatuses}
          loadInitial={true}
          reloadOnComponentEvent={false}
          additionalAsyncFnParams={{ objectTypes: objectRange }}
          fieldName={"status"}
          isOptionEqualToValue={(option, val) => option.bpoOnlineStatus === val.bpoOnlineStatus}
          optionSelectedText={(option) => iTxt(i18n.language, option.bpoOnlineStatus, option.bpoOnlineStatusEn)}
          optionListText={(option) => iTxt(i18n.language, option.bpoOnlineStatus, option.bpoOnlineStatusEn)}
        />
      </GridItem>
    </>
  );
};
export default StatusSearchFilter;
