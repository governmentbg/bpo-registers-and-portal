import { GridItem, AsyncAutocompleteFormField } from "@duosoftbg/bpo-components";
import { autocompleteViennaClasses } from "../../../../../../axios/api/services";
import SearchOperatorTypeField from "../fields/SearchOperatorTypeField";

const ViennaClassSearchFilter = () => {
  return (
    <>
      <GridItem xs={12} sm={12} md={12} lg={12}>
        <AsyncAutocompleteFormField
          multiple={true}
          disableCloseOnSelect={true}
          minCharsAutocomplete={2}
          label={"l.object.viennaClasses"}
          asyncFn={autocompleteViennaClasses}
          fieldName={"viennaClasses.viennaClasses"}
          isOptionEqualToValue={(option, val) => option.id === val.id}
          optionSelectedText={(option) => option.id + " - " + option.name}
          optionListText={(option) => option.id + " - " + option.name}
        />
        <SearchOperatorTypeField searchOperatorTypeField={"viennaClasses.viennaClassCodeType"} />
      </GridItem>
    </>
  );
};
export default ViennaClassSearchFilter;
