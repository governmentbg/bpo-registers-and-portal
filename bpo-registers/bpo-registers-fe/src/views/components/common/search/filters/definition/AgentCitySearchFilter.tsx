import { AsyncAutocompleteFormField, GridItem } from "@duosoftbg/bpo-components";
import { getAgentCities } from "../../../../../../axios/api/services";

const AgentCitySearchFilter = () => {
  return (
    <GridItem sm={6} md={6} lg={6}>
      <AsyncAutocompleteFormField
        label={"l.searchFilter.city"}
        asyncFn={getAgentCities}
        fieldName={"city"}
        minCharsAutocomplete={1}
      ></AsyncAutocompleteFormField>
    </GridItem>
  );
};
export default AgentCitySearchFilter;
