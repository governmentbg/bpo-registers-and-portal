import { useWatch } from "react-hook-form";
import { autocompleteCpcCodes } from "../../../../../../axios/api/services";
import { AsyncAutocompleteFormField, GridItem } from "@duosoftbg/bpo-components";
import { CpcSearchType } from "../../../../../../types/registers/search/filter/filterTypes";

const CpcCodeSearchFilter = () => {
  const searchType = useWatch({ name: "cpcSearchType" });

  if (CpcSearchType.CODE !== searchType) {
    return null;
  }

  return (
    <GridItem sm={12} md={12} lg={12}>
      {/*<NameSearchFilter nameField={"cpcCode"} nameFieldLabelCode={"l.searchFilter.cpc.name"} sm={12} md={12} lg={12} />*/}
      <AsyncAutocompleteFormField
        minCharsAutocomplete={2}
        label={"l.object.cpcCode"}
        asyncFn={autocompleteCpcCodes}
        allowManualInput={true}
        fieldName={"cpcCode"}
      />
    </GridItem>
  );
};
export default CpcCodeSearchFilter;
