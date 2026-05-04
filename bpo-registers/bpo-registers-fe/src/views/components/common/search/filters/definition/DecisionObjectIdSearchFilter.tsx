import { GridItem, InputFormField } from "@duosoftbg/bpo-components";

const DecisionObjectIdSearchFilter = () => {
  return (
    <GridItem sm={6} md={6} lg={6}>
      <InputFormField fieldName={"objectId"} labelCode={"l.searchFilter.decisionObjectId"} />
    </GridItem>
  );
};
export default DecisionObjectIdSearchFilter;
