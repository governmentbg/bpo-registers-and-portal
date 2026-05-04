import { PanelToComponentDefinition } from "../../../../../../../types/registers/view/panels/panelsDefinition";
import { PersonType, RegisterType } from "../../../../../../../utils/constants";
import React from "react";
import RelatedObjectsDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/RelatedObjectsDataPanel";
import { isArrayEmpty } from "@duosoftbg/bpo-components";
import PublicationDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/PublicationDataPanel";
import PersonDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/PersonDataPanel";
import MainDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/MainDataPanel";
import MediaPanel from "../../../../../../../views/components/registers/view/ipObjects/common/MediaPanel";
import ViennaClassesPanel from "../../../../../../../views/components/registers/view/ipObjects/markLike/common/ViennaClassesPanel";
import NiceClassesPanel from "../../../../../../../views/components/registers/view/ipObjects/markLike/common/NiceClassesPanel";
import { getObjectRelationshipsByObjectId } from "../../../../../../../axios/api/services";
import { COMMON_PANEL_DEFINITION_TO_COMPONENT } from "../common/CommonPanelDefinitionToComponent";

export const GEO_INDICATION_VIEW_PANEL_DEFINITION_TO_COMPONENT: PanelToComponentDefinition = [
  {
    id: "geoMainData",
    label: "l.panel.geoMainData",
    component: (data) => <MainDataPanel data={data} registerType={RegisterType.GEO_INDICATION} />,
  },
  {
    id: "geoRelatedObjects",
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
    id: "geoPublicationData",
    label: `l.panel.publicationData.${RegisterType.GEO_INDICATION}`,
    component: (data) => <PublicationDataPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.ipObject?.publications),
  },
  {
    id: "geoDrawingsData",
    label: `l.panel.drawingsData.${RegisterType.GEO_INDICATION}`,
    component: (data) => (
      <MediaPanel
        registerType={RegisterType.GEO_INDICATION}
        attachments={data?.ipObject?.attachments}
        types={["DRAW"]}
      />
    ),
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.ipObject?.attachments),
  },
  {
    id: "geoViennaClassesData",
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
    id: "geoNiceClassesData",
    label: "l.panel.niceClassesData",
    component: (data) => <NiceClassesPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.niceClasses),
  },
  {
    id: "geoApplicantOwnerData",
    label: "l.panel.markApplicantOwnerData",
    component: (data) => <PersonDataPanel data={data} personType={PersonType.OWNER} />,
    defineEmptyPanelFn: (data) =>
      isArrayEmpty(data?.ipObject?.persons.filter((z) => z.id?.personRole === PersonType.OWNER)),
  },
  {
    id: "geoRepresentativeData",
    label: `l.panel.representativeData.${RegisterType.GEO_INDICATION}`,
    component: (data) => <PersonDataPanel data={data} personType={PersonType.REPRESENTATIVE} />,
    defineEmptyPanelFn: (data) =>
      isArrayEmpty(data?.ipObject?.persons.filter((z) => z.id?.personRole === PersonType.REPRESENTATIVE)),
  },
  {
    ...COMMON_PANEL_DEFINITION_TO_COMPONENT.recordalData,
    label: `l.panel.recordalData.${RegisterType.GEO_INDICATION}`,
  },
  COMMON_PANEL_DEFINITION_TO_COMPONENT.oppositionData,
  COMMON_PANEL_DEFINITION_TO_COMPONENT.cancellationData,
  COMMON_PANEL_DEFINITION_TO_COMPONENT.objectDecisionsData,
];
