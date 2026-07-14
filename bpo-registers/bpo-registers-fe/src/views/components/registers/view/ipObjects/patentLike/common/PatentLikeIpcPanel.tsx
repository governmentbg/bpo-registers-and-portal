import { BlockText, GridContainer } from "@duosoftbg/bpo-components";
import React from "react";

const PatentLikeIpcPanel = ({ data }) => {
  return (
    <div className={"ipc-data-panel"}>
      <GridContainer spacing={3}>
        {data?.ipcClasses.map((x, index) => (
          <BlockText
            key={"ipc-" + index}
            propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
            withGrid
            label={""}
            text={
              x.id.sectionCode +
              x.id.classCode +
              x.id.subclassCode +
              " " +
              x.id.groupCode +
              "/" +
              x.id.subgroupCode +
              " (" +
              x.id.editionCode +
              ")"
            }
          />
        ))}
      </GridContainer>
    </div>
  );
};
export default PatentLikeIpcPanel;
