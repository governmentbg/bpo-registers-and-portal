import { GridItem, InputFormField } from "@duosoftbg/bpo-components";
import React from "react";

const AgentCodeSearchFilter = () => {
  return (
    <>
      <GridItem sm={6} md={6} lg={6}>
        <InputFormField fieldName={"agentCode"} labelCode={"l.searchFilter.agentCode"} />
      </GridItem>
    </>
  );
};
export default AgentCodeSearchFilter;
