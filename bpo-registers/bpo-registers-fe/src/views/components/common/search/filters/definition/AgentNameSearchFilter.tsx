import { GridItem, InputFormField } from "@duosoftbg/bpo-components";
import { AGENT_FILTERS } from "../../../../../../config/registers/search/filters/definition/slice/representativeFilters";

const AgentNameSearchFilter = ({ label = AGENT_FILTERS.agentName.label }) => {
  return (
    <GridItem sm={6} md={6} lg={6}>
      <InputFormField fieldName={"name"} labelCode={label} />
    </GridItem>
  );
};
export default AgentNameSearchFilter;
