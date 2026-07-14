import { useWatch } from "react-hook-form";
import { IpcSearchType } from "../../../../../../types/registers/search/filter/filterTypes";
import { autocompleteIpcCodes } from "../../../../../../axios/api/services";
import { AsyncAutocompleteFormField, GridItem } from "@duosoftbg/bpo-components";

const IpcCodeSearchFilter = () => {
  const searchType = useWatch({ name: "ipcSearchType" });

  if (IpcSearchType.CODE !== searchType) {
    return null;
  }

  return (
    <GridItem sm={12} md={12} lg={12}>
      {/*<NameSearchFilter nameField={"ipcCode"} nameFieldLabelCode={"l.searchFilter.ipc.name"} sm={12} md={12} lg={12} />*/}
      <AsyncAutocompleteFormField
        minCharsAutocomplete={2}
        label={"l.object.ipcCode"}
        asyncFn={autocompleteIpcCodes}
        allowManualInput={true}
        fieldName={"ipcCode"}
      />
    </GridItem>
  );
};
export default IpcCodeSearchFilter;
