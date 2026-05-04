import { PanelToComponentDefinition } from "../../../../../../../types/registers/view/panels/panelsDefinition";
import MainDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/MainDataPanel";
import React from "react";
import { PersonType, RegisterType } from "../../../../../../../utils/constants";
import RelatedObjectsDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/RelatedObjectsDataPanel";
import { isArrayEmpty, isEmpty } from "@duosoftbg/bpo-components";
import PatentLikeCpcPanel from "../../../../../../../views/components/registers/view/ipObjects/patentLike/common/PatentLikeCpcPanel";
import PatentLikeIpcPanel from "../../../../../../../views/components/registers/view/ipObjects/patentLike/common/PatentLikeIpcPanel";
import PublicationDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/PublicationDataPanel";
import PatentLikeAbstractPanel from "../../../../../../../views/components/registers/view/ipObjects/patentLike/common/PatentLikeAbstractPanel";
import AttachmentsDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/AttachmentsDataPanel";
import PersonDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/PersonDataPanel";
import { getObjectRelationshipsByObjectId } from "../../../../../../../axios/api/services";
import { COMMON_PANEL_DEFINITION_TO_COMPONENT } from "../common/CommonPanelDefinitionToComponent";

export const EU_PATENT_VIEW_PANEL_DEFINITION_TO_COMPONENT: PanelToComponentDefinition = [
  {
    id: "patentLikeMainData",
    label: "l.panel.patentLikeMainData",
    component: (data) => <MainDataPanel data={data} registerType={RegisterType.EU_PATENT} />,
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
    label: `l.panel.publicationData.${RegisterType.EU_PATENT}`,
    component: (data) => <PublicationDataPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.ipObject?.publications),
  },
  {
    id: "patentLikeAbstractData",
    label: "l.panel.patentLikeAbstractData",
    component: (data) => <PatentLikeAbstractPanel data={data} />,
    defineEmptyPanelFn: (data) => isEmpty(data?.mainAbstract),
  },
  {
    id: "patentLikeAttachmentsData",
    label: "l.panel.patentLikeAttachmentsData",
    component: (data) => <AttachmentsDataPanel data={data} types={["DC"]} />,
    defineEmptyPanelFn: (data) =>
      isArrayEmpty(
        data?.ipObject?.attachments?.filter((x) =>
          x?.attachment?.attachmentType?.categories?.map((x) => x?.id).includes("DC")
        )
      ),
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
