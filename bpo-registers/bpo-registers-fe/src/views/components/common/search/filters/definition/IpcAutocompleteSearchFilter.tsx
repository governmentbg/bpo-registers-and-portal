import { AsyncAutocompleteFormField, GridItem } from "@duosoftbg/bpo-components";
import { autocompleteIpc } from "../../../../../../axios/api/services";
import SearchOperatorTypeField from "../fields/SearchOperatorTypeField";
import { useWatch } from "react-hook-form";
import { IpcSearchType } from "../../../../../../types/registers/search/filter/filterTypes";

const IpcAutocompleteSearchFilter = () => {
  const searchType = useWatch({ name: "ipcSearchType" });

  if (IpcSearchType.AUTOCOMPLETE !== searchType) {
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
          label={"l.object.ipc"}
          asyncFn={autocompleteIpc}
          fieldName={"ipcClasses.ipcClasses"}
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
        <SearchOperatorTypeField searchOperatorTypeField={"ipcClasses.ipcClassOperatorType"} />
      </GridItem>
    </>
  );
};

export const stringifyIpcCpcOption = (option) =>
  [
    option?.id?.sectionCode,
    option?.id?.classCode,
    option?.id?.subclassCode,
    option?.id?.groupCode,
    option?.id?.subgroupCode,
    option?.id?.editionCode,
    option?.name?.replace(/[^a-zA-Z0-9]/g, "").toLowerCase(),
  ]
    .join("")
    .replaceAll("[^a-zA-Z0-9]", "")
    .toLowerCase();

export const filterIpcCpcOptions = (options, { inputValue }) => {
  const processedInput = inputValue.replace(/[^a-zA-Z0-9]/g, "").toLowerCase();
  return options.filter((option) => stringifyIpcCpcOption(option).includes(processedInput));
};
export default IpcAutocompleteSearchFilter;
