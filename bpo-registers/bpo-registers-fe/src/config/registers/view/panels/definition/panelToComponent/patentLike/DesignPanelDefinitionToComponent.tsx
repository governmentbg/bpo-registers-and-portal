import { PanelToComponentDefinition } from "../../../../../../../types/registers/view/panels/panelsDefinition";
import MainDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/MainDataPanel";
import { PersonType, RegisterType } from "../../../../../../../utils/constants";
import React from "react";
import RelatedObjectsDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/RelatedObjectsDataPanel";
import { isArrayEmpty } from "@duosoftbg/bpo-components";
import PublicationDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/PublicationDataPanel";
import PersonDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/PersonDataPanel";
import { COMMON_PANEL_DEFINITION_TO_COMPONENT } from "../common/CommonPanelDefinitionToComponent";
import { getObjectRelationshipsByObjectId } from "../../../../../../../axios/api/services";
import SingleDesignsDataPanel from "../../../../../../../views/components/registers/view/ipObjects/patentLike/design/panels/SingleDesignsDataPanel";

export const DESIGN_VIEW_PANEL_DEFINITION_TO_COMPONENT: PanelToComponentDefinition = [
  {
    id: "patentLikeMainData",
    label: "l.panel.patentLikeMainData",
    component: (data) => <MainDataPanel data={data} registerType={RegisterType.DESIGN} />,
  },
  {
    id: "singleDesignsData",
    label: "l.panel.singleDesignsData",
    component: (data) => <SingleDesignsDataPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.singleDesigns),
  },
  {
    id: "patentLikeRelatedObjects",
    label: "l.panel.relatedObjects",
    component: (data) => <RelatedObjectsDataPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.relationshipsView),
    loadPanelNotifier: {
      notifierFieldName: "relationshipsView",
      notifierAsyncFunc: (data) => {
        return () => getObjectRelationshipsByObjectId(data.id);
      },
    },
  },
  {
    id: "patentLikePublicationData",
    label: `l.panel.publicationData.${RegisterType.DESIGN}`,
    component: (data) => <PublicationDataPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.ipObject?.publications),
  },
  {
    id: "patentLikeApplicantOwnerData",
    label: "l.panel.patentLikeApplicantOwnerData",
    component: (data) => <PersonDataPanel data={data} personType={PersonType.OWNER} />,
    defineEmptyPanelFn: (data) =>
      isArrayEmpty(data?.ipObject?.persons.filter((z) => z.id?.personRole === PersonType.OWNER)),
  },
  {
    id: "patentLikeRepresentativeData",
    label: "l.panel.representativeData",
    component: (data) => <PersonDataPanel data={data} personType={PersonType.REPRESENTATIVE} />,
    defineEmptyPanelFn: (data) =>
      isArrayEmpty(data?.ipObject?.persons.filter((z) => z.id?.personRole === PersonType.REPRESENTATIVE)),
  },
  {
    id: "patentLikeInventorData",
    label: "l.panel.designAuthorData",
    component: (data) => <PersonDataPanel data={data} personType={PersonType.INVENTOR} />,
    defineEmptyPanelFn: (data) =>
      isArrayEmpty(data?.ipObject?.persons.filter((z) => z.id?.personRole === PersonType.INVENTOR)),
  },
  COMMON_PANEL_DEFINITION_TO_COMPONENT.recordalData,
  COMMON_PANEL_DEFINITION_TO_COMPONENT.cancellationData,
  COMMON_PANEL_DEFINITION_TO_COMPONENT.objectDecisionsData,
];
