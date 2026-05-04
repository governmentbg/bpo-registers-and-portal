import React from "react";
import { PLANT_BREED_VIEW_PANEL_DEFINITION_TO_COMPONENT } from "../../../../../../../../config/registers/view/panels/definition/panelToComponent/patentLike/PlantBreedPanelDefinitionToComponent";
import ViewRegisterRenderer from "../../../../common/ViewRegisterRenderer";

const ViewPlantBreedRenderer = ({ data }) => {
  return <ViewRegisterRenderer panelDenifitions={PLANT_BREED_VIEW_PANEL_DEFINITION_TO_COMPONENT} data={data} />;
};

export default ViewPlantBreedRenderer;
