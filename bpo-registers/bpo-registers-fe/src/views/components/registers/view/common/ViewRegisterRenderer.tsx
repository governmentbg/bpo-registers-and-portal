import React, { Fragment } from "react";
import AsyncViewPanelsWrapper from "./AsyncViewPanelsWrapper";
import ViewPanelsRendererBody from "./ViewPanelsRendererBody";

const ViewRegisterRenderer = ({ panelDenifitions, data }) => {
  return (
    <>
      {panelDenifitions.map((definitionToComponent) => (
        <Fragment key={definitionToComponent.id}>
          {definitionToComponent.loadPanelNotifier && (
            <AsyncViewPanelsWrapper definitionToComponent={definitionToComponent} data={data}></AsyncViewPanelsWrapper>
          )}
          {!definitionToComponent.loadPanelNotifier && (
            <ViewPanelsRendererBody data={data} definitionToComponent={definitionToComponent} />
          )}
        </Fragment>
      ))}
    </>
  );
};
export default ViewRegisterRenderer;
