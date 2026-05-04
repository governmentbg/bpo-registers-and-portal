import { List } from "@mui/material";
import React from "react";
import RepresentativeHistoryRecord from "./RepresentativeHistoryRecord";
import ViewRepresentativeHistoryDialog from "./dialog/ViewRepresentativeHistoryDialog";

const RepresentativeHistoryPanel = ({ viewType, data }) => {
  return (
    <>
      <ViewRepresentativeHistoryDialog />
      {data.agentHistory && (
        <List>
          {data.agentHistory.map((row) => {
            return <RepresentativeHistoryRecord key={row.id} viewType={viewType} historyRecord={row} />;
          })}
        </List>
      )}
    </>
  );
};
export default RepresentativeHistoryPanel;
