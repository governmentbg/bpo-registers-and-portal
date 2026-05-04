import ViewPanelsWrapper from "./ViewPanelsWrapper";
import React from "react";

const ViewPanelsRendererBody = ({ definitionToComponent, data }) => {
  return (
    <>
      <ViewPanelsWrapper
        key={definitionToComponent.id}
        title={definitionToComponent.label}
        emptyPanel={definitionToComponent.defineEmptyPanelFn && definitionToComponent?.defineEmptyPanelFn(data)}
      >
        {definitionToComponent.component(data)}
      </ViewPanelsWrapper>
    </>
  );
};

export default ViewPanelsRendererBody;
