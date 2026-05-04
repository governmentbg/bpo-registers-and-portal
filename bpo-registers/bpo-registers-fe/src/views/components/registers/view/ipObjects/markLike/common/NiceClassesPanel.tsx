import { BlockText, GridContainer } from "@duosoftbg/bpo-components";
import React from "react";

const NiceClassesPanel = ({ data }) => {
  return (
    <div className={"nice-classes-data-panel"}>
      <GridContainer spacing={3}>
        {data?.niceClasses.map((x, index) => (
          <BlockText
            key={"nice-class-" + index}
            propsGrid={{ xs: 12, sm: 12, md: 12, lg: 12 }}
            withGrid
            label={""}
            text={x.id.classId + ". " + x.specification}
          />
        ))}
      </GridContainer>
    </div>
  );
};
export default NiceClassesPanel;
