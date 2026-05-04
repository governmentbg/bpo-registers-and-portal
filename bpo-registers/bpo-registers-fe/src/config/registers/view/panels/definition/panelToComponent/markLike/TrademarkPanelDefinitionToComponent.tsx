import { PanelToComponentDefinition } from "../../../../../../../types/registers/view/panels/panelsDefinition";
import { PersonType, RegisterType } from "../../../../../../../utils/constants";
import React from "react";
import RelatedObjectsDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/RelatedObjectsDataPanel";
import { isArrayEmpty, isEmpty } from "@duosoftbg/bpo-components";
import PublicationDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/PublicationDataPanel";
import AttachmentsDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/AttachmentsDataPanel";
import PersonDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/PersonDataPanel";
import MainDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/MainDataPanel";
import MediaPanel from "../../../../../../../views/components/registers/view/ipObjects/common/MediaPanel";
import ColoursPanel from "../../../../../../../views/components/registers/view/ipObjects/markLike/trademark/panels/ColoursPanel";
import ViennaClassesPanel from "../../../../../../../views/components/registers/view/ipObjects/markLike/common/ViennaClassesPanel";
import NiceClassesPanel from "../../../../../../../views/components/registers/view/ipObjects/markLike/common/NiceClassesPanel";
import DescriptionPanel from "../../../../../../../views/components/registers/view/ipObjects/markLike/trademark/panels/DescriptionPanel";
import { COMMON_PANEL_DEFINITION_TO_COMPONENT } from "../common/CommonPanelDefinitionToComponent";
import { getObjectRelationshipsByObjectId } from "../../../../../../../axios/api/services";

export const TRADEMARK_VIEW_PANEL_DEFINITION_TO_COMPONENT: PanelToComponentDefinition = [
  {
    id: "markMainData",
    label: "l.panel.markMainData",
    component: (data) => <MainDataPanel data={data} registerType={RegisterType.MARK} />,
  },
  {
    id: "markRelatedObjects",
    label: "l.panel.relatedObjects",
    component: (data) => <RelatedObjectsDataPanel data={data} registerType={RegisterType.MARK} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.relationshipsView),
    loadPanelNotifier: {
      notifierFieldName: "relationshipsView",
      notifierAsyncFunc: (data) => {
        return () => getObjectRelationshipsByObjectId(data.id);
      },
    },
  },
  {
    id: "markPublicationData",
    label: `l.panel.publicationData.${RegisterType.MARK}`,
    component: (data) => <PublicationDataPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.ipObject?.publications),
  },
  {
    id: "markDrawingsData",
    label: `l.panel.drawingsData.${RegisterType.MARK}`,
    component: (data) => (
      <MediaPanel registerType={RegisterType.MARK} data={data} attachments={data?.markAttachments} types={["DRAW"]} />
    ),
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.markAttachments) && isEmpty(data?.ipObject?.title),
  },
  {
    id: "markColoursData",
    label: "l.panel.coloursData",
    component: (data) => (
      <ColoursPanel
        colours={data?.markAttachments
          ?.filter((x) => x.colorDescription)
          ?.map((x) => {
            return x?.colorDescription;
          })}
      />
    ),
    // defineEmptyPanelFn: (data) =>
    //   isArrayEmpty(
    //     data?.markAttachments
    //       ?.filter((x) => x?.colorDescription)
    //       ?.map((x) => {
    //         return x?.colorDescription;
    //       })
    //   ),
  },
  {
    id: "markDescriptionData",
    label: "l.panel.descriptionData",
    component: (data) => <DescriptionPanel description={data?.markDescription} />,
  },
  {
    id: "markAttachmentsData",
    label: "l.panel.markAttachmentsData",
    component: (data) => <AttachmentsDataPanel data={data} types={["DC"]} />,
    defineEmptyPanelFn: (data) =>
      isArrayEmpty(
        data?.ipObject?.attachments?.filter((x) =>
          x?.attachment?.attachmentType?.categories?.map((x) => x?.id).includes("DC")
        )
      ),
  },
  {
    id: "markViennaClassesData",
    label: "l.panel.viennaClassesData",
    component: (data) => (
      <ViennaClassesPanel
        classes={data?.markAttachments?.map((x) => {
          return x?.viennaClasses?.map((y) => {
            return y?.id?.categoryId;
          });
        })}
      />
    ),
    defineEmptyPanelFn: (data) =>
      isArrayEmpty(
        data?.markAttachments?.map((x) => {
          return x?.viennaClasses?.map((y) => {
            return y?.id?.categoryId;
          });
        })
      ),
  },
  {
    id: "markNiceClassesData",
    label: "l.panel.niceClassesData",
    component: (data) => <NiceClassesPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.niceClasses),
  },
  {
    id: "markApplicantOwnerData",
    label: "l.panel.markApplicantOwnerData",
    component: (data) => <PersonDataPanel data={data} personType={PersonType.OWNER} />,
    defineEmptyPanelFn: (data) =>
      isArrayEmpty(data?.ipObject?.persons.filter((z) => z.id?.personRole === PersonType.OWNER)),
  },
  {
    id: "markRepresentativeData",
    label: `l.panel.representativeData.${RegisterType.MARK}`,
    component: (data) => <PersonDataPanel data={data} personType={PersonType.REPRESENTATIVE} />,
    defineEmptyPanelFn: (data) =>
      isArrayEmpty(data?.ipObject?.persons.filter((z) => z.id?.personRole === PersonType.REPRESENTATIVE)),
  },
  {
    ...COMMON_PANEL_DEFINITION_TO_COMPONENT.recordalData,
    label: `l.panel.recordalData.${RegisterType.MARK}`,
  },
  COMMON_PANEL_DEFINITION_TO_COMPONENT.oppositionData,
  COMMON_PANEL_DEFINITION_TO_COMPONENT.cancellationData,
  COMMON_PANEL_DEFINITION_TO_COMPONENT.objectDecisionsData,
];
