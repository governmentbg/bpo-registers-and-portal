import { AsyncAutocompleteFormField, GridItem } from "@duosoftbg/bpo-components";
import { autocompleteLocarnoClasses } from "../../../../../../axios/api/services";
import SearchOperatorTypeField from "../fields/SearchOperatorTypeField";

const LocarnoClassSearchFilter = () => {
  return (
    <>
      <GridItem xs={12} sm={12} md={12} lg={12}>
        <AsyncAutocompleteFormField
          multiple={true}
          disableCloseOnSelect={true}
          minCharsAutocomplete={2}
          label={"l.object.locarnoClasses"}
          asyncFn={autocompleteLocarnoClasses}
          fieldName={"locarnoClasses.locarnoClasses"}
          isOptionEqualToValue={(option, val) => option.id === val.id}
          optionSelectedText={(option) => option.id + " - " + option.description}
          optionListText={(option) => option.id + " - " + option.description}
        />
        <SearchOperatorTypeField searchOperatorTypeField={"locarnoClasses.locarnoClassCodeType"} />
      </GridItem>
    </>
  );
};
export default LocarnoClassSearchFilter;
