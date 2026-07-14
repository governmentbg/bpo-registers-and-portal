import { AsyncAutocompleteFormField, GridItem } from "@duosoftbg/bpo-components";
import { autocompleteCpc } from "../../../../../../axios/api/services";
import SearchOperatorTypeField from "../fields/SearchOperatorTypeField";
import { useWatch } from "react-hook-form";
import { CpcSearchType } from "../../../../../../types/registers/search/filter/filterTypes";
import { filterIpcCpcOptions, stringifyIpcCpcOption } from "./IpcAutocompleteSearchFilter";

const CpcAutocompleteSearchFilter = () => {
  const searchType = useWatch({ name: "cpcSearchType" });

  if (CpcSearchType.AUTOCOMPLETE !== searchType) {
    return null;
  }
  return (
    <>
      <GridItem xs={12} sm={12} md={12} lg={12}>
        <AsyncAutocompleteFormField
          filterOptions={filterIpcCpcOptions}
          multiple={true}
          disableCloseOnSelect={true}
          minCharsAutocomplete={2}
          label={"l.object.cpc"}
          asyncFn={autocompleteCpc}
          fieldName={"cpcClasses.cpcClasses"}
          isOptionEqualToValue={(option, val) => stringifyIpcCpcOption(option) === stringifyIpcCpcOption(val)}
          optionSelectedText={(option) =>
            option?.id?.sectionCode +
            option?.id?.classCode +
            option?.id?.subclassCode +
            " " +
            option?.id?.groupCode +
            "/" +
            option?.id?.subgroupCode +
            " (" +
            option?.id?.editionCode +
            ")"
          }
          optionListText={(option) =>
            option?.id?.sectionCode +
            option?.id?.classCode +
            option?.id?.subclassCode +
            " " +
            option?.id?.groupCode +
            "/" +
            option?.id?.subgroupCode +
            " (" +
            option?.id?.editionCode +
            ")" +
            " - " +
            option?.name
          }
        />
        <SearchOperatorTypeField searchOperatorTypeField={"cpcClasses.cpcClassOperatorType"} />
      </GridItem>
    </>
  );
};
export default CpcAutocompleteSearchFilter;
