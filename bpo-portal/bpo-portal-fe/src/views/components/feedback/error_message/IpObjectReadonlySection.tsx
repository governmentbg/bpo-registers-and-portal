import { GridContainer, GridItem, InputFormField } from "@duosoftbg/bpo-components";
import React from "react";

const IpObjectReadonlySection = (p) => {
  return (
    <div>
      <GridContainer spacing={3} mt={2}>
        <GridItem sm={6} md={6} lg={6}>
          <InputFormField isDisabled={true} fieldName={"objectType"} labelCode={"l.objectType"} />
        </GridItem>
        <GridItem sm={6} md={6} lg={6}>
          <InputFormField isDisabled={true} fieldName={"applicationNumber"} labelCode={"l.applicationNumber"} />
        </GridItem>
      </GridContainer>
    </div>
  );
};

export default IpObjectReadonlySection;
