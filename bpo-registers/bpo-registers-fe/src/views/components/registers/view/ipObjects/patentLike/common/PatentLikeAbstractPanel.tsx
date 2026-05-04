import { BlockText, GridContainer } from "@duosoftbg/bpo-components";
import React from "react";

const PatentLikeAbstractPanel = ({ data }) => {
  return (
    <div className={"abstract-data-panel"}>
      <GridContainer spacing={3}>
        <BlockText propsGrid={{ xs: 12, sm: 12, md: 12, lg: 12 }} withGrid label={""} text={data?.mainAbstract} />
      </GridContainer>
    </div>
  );
};
export default PatentLikeAbstractPanel;
