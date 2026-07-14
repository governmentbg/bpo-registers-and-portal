import { PanelToComponentDefinition } from "../../../../../../../types/registers/view/panels/panelsDefinition";
import MainDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/MainDataPanel";
import { PersonType, RegisterType } from "../../../../../../../utils/constants";
import React from "react";
import PatentLikeAbstractPanel from "../../../../../../../views/components/registers/view/ipObjects/patentLike/common/PatentLikeAbstractPanel";
import { isArrayEmpty, isEmpty } from "@duosoftbg/bpo-components";
import PersonDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/PersonDataPanel";
import PublicationDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/PublicationDataPanel";
import TaxonDataPanel from "../../../../../../../views/components/registers/view/ipObjects/patentLike/plantBreed/panels/TaxonDataPanel";
import RelatedObjectsDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/RelatedObjectsDataPanel";
import AttachmentsDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/AttachmentsDataPanel";
import { getObjectRelationshipsByObjectId } from "../../../../../../../axios/api/services";
import { arrayContainsArray } from "../../../../../../../utils/functions";
import { COMMON_PANEL_DEFINITION_TO_COMPONENT } from "../common/CommonPanelDefinitionToComponent";

export const PLANT_BREED_VIEW_PANEL_DEFINITION_TO_COMPONENT: PanelToComponentDefinition = [
  {
    id: "patentLikeMainData",
    label: "l.panel.patentLikeMainData",
    component: (data) => <MainDataPanel data={data} registerType={RegisterType.PLANT_BREED} />,
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
    id: "taxonData",
    label: "l.panel.taxonData",
    component: (data) => <TaxonDataPanel data={data} />,
    defineEmptyPanelFn: (data) => isEmpty(data?.taxon),
  },
  {
    id: "patentLikeAbstractData",
    label: "l.panel.plantBreedAbstractData",
    component: (data) => <PatentLikeAbstractPanel data={data} />,
    defineEmptyPanelFn: (data) => isEmpty(data?.mainAbstract),
  },
  {
    id: "patentLikePublicationData",
    label: `l.panel.publicationData.${RegisterType.PLANT_BREED}`,
    component: (data) => <PublicationDataPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.ipObject?.publications),
  },
  {
    id: "plantAttachmentsData",
    label: "l.panel.plantAttachmentsData",
    component: (data) => <AttachmentsDataPanel data={data} types={["THUMB", "DRAW"]} />,
    defineEmptyPanelFn: (data) =>
      isArrayEmpty(
        data?.ipObject?.attachments?.filter((x) =>
          arrayContainsArray(
            ["THUMB", "DRAW"],
            x?.attachment?.attachmentType?.categories?.map((x) => x?.id)
          )
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
    label: "l.panel.plantBreedAuthorData",
    component: (data) => <PersonDataPanel data={data} personType={PersonType.INVENTOR} />,
    defineEmptyPanelFn: (data) =>
      isArrayEmpty(data?.ipObject?.persons.filter((z) => z.id?.personRole === PersonType.INVENTOR)),
  },
  COMMON_PANEL_DEFINITION_TO_COMPONENT.recordalData,
  COMMON_PANEL_DEFINITION_TO_COMPONENT.cancellationData,
  COMMON_PANEL_DEFINITION_TO_COMPONENT.objectDecisionsData,
];
