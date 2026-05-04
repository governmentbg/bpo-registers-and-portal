import { GridItem, InputFormField } from "@duosoftbg/bpo-components";
import React from "react";

const AgentSpecialitySearchFilter = () => {
  return (
    <>
      <GridItem sm={6} md={6} lg={6}>
        <InputFormField fieldName={"agentSpeciality"} labelCode={"l.searchFilter.agentSpeciality"} />
      </GridItem>
    </>
  );
};
export default AgentSpecialitySearchFilter;
