import { PanelToComponentDefinition } from "../../../../../../../types/registers/view/panels/panelsDefinition";
import MainDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/MainDataPanel";
import { PersonType, RegisterType } from "../../../../../../../utils/constants";
import React from "react";
import RelatedObjectsDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/RelatedObjectsDataPanel";
import { isArrayEmpty } from "@duosoftbg/bpo-components";
import PatentLikeCpcPanel from "../../../../../../../views/components/registers/view/ipObjects/patentLike/common/PatentLikeCpcPanel";
import PatentLikeIpcPanel from "../../../../../../../views/components/registers/view/ipObjects/patentLike/common/PatentLikeIpcPanel";
import PublicationDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/PublicationDataPanel";
import PersonDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/PersonDataPanel";
import { getObjectRelationshipsByObjectId } from "../../../../../../../axios/api/services";
import { COMMON_PANEL_DEFINITION_TO_COMPONENT } from "../common/CommonPanelDefinitionToComponent";

export const SPC_VIEW_PANEL_DEFINITION_TO_COMPONENT: PanelToComponentDefinition = [
  {
    id: "patentLikeMainData",
    label: "l.panel.patentLikeMainData",
    component: (data) => <MainDataPanel data={data} registerType={RegisterType.SPC} />,
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
    id: "patentLikeCpcData",
    label: "l.panel.patentLikeCpcData",
    component: (data) => <PatentLikeCpcPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.cpcClasses),
  },
  {
    id: "patentLikeIpcData",
    label: "l.panel.patentLikeIpcData",
    component: (data) => <PatentLikeIpcPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.ipcClasses),
  },
  {
    id: "patentLikePublicationData",
    label: `l.panel.publicationData.${RegisterType.SPC}`,
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
    label: "l.panel.patentLikeInventorData",
    component: (data) => <PersonDataPanel data={data} personType={PersonType.INVENTOR} />,
    defineEmptyPanelFn: (data) =>
      isArrayEmpty(data?.ipObject?.persons.filter((z) => z.id?.personRole === PersonType.INVENTOR)),
  },
  COMMON_PANEL_DEFINITION_TO_COMPONENT.recordalData,
  COMMON_PANEL_DEFINITION_TO_COMPONENT.cancellationData,
  COMMON_PANEL_DEFINITION_TO_COMPONENT.objectDecisionsData,
];
