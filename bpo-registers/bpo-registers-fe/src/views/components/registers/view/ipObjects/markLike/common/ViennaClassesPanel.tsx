import { BlockText, GridContainer } from "@duosoftbg/bpo-components";
import React from "react";

const ViennaClassesPanel = ({ classes }) => {
  return (
    <div className={"vienna-classes-data-panel"}>
      <GridContainer spacing={3}>
        <BlockText propsGrid={{ xs: 12, sm: 12, md: 12, lg: 12 }} withGrid label={""} text={classes?.join("; ")} />
      </GridContainer>
    </div>
  );
};
export default ViennaClassesPanel;
