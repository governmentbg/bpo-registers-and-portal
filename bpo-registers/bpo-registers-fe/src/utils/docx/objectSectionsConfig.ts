import { PersonType, RegisterType } from "../constants";
import { getJsonTableHeadersByViewSectionType, ViewSectionType } from "./jsonMappings";
import {
  convertSectionDataCustom,
  convertSectionDataImage,
  convertSectionDataTableJson,
  convertSectionDataTextBlock,
  convertSectionDataTextImageConsecutive,
  DocumentSectionType,
} from "./docxJsonConvertor";
import { CustomSection } from "./customSectionGenerator";

export const convertViewJsonToDocxJsonByRegisterType = (registerType, data, t, componentRef) => {
  switch (registerType) {
    case RegisterType.MARK:
      return convertJsonMark(t, componentRef, data);
    case RegisterType.GEO_INDICATION:
      return convertJsonGeo(t, componentRef, data);
    case RegisterType.PATENT:
      return convertJsonPatent(t, componentRef, data);
    case RegisterType.UTILITY_MODEL:
      return convertJsonUtilityModel(t, componentRef, data);
    case RegisterType.EU_PATENT:
      return convertJsonEuPatent(t, componentRef, data);
    case RegisterType.PLANT_BREED:
      return convertJsonPlantBreed(t, componentRef, data);
    case RegisterType.SPC:
      return convertJsonSPC(t, componentRef, data);
    case RegisterType.DESIGN:
      return convertJsonDesign(t, componentRef, data);
    case RegisterType.AGENT:
      return convertJsonAgent(t, componentRef, data);
    case RegisterType.PARTNERSHIP:
      return convertJsonPartnership(t, componentRef, data);
  }
};

const convertJsonMark = (t, componentRef, data) => {
  let mainData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.markMainData"),
    ".main-data-panel",
    DocumentSectionType.TEXT
  );
  let relatedObjectsData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.relatedObjects"),
    ".related-objects-data-panel",
    DocumentSectionType.GRID
  );
  let publicationData = convertSectionDataTextBlock(
    componentRef,
    t(`l.panel.publicationData.${RegisterType.MARK}`),
    ".publication-data-panel",
    DocumentSectionType.GRID
  );
  let imageData = convertSectionDataImage(
    componentRef,
    t(`l.panel.drawingsData.${RegisterType.MARK}`),
    ".media-data-panel",
    DocumentSectionType.IMAGE
  );
  let coloursData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.coloursData"),
    ".colours-data-panel",
    DocumentSectionType.TEXT
  );
  let descriptionData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.descriptionData"),
    ".description-data-panel",
    DocumentSectionType.TEXT
  );
  let attachmentData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.markAttachmentsData"),
    ".attachments-data-panel",
    DocumentSectionType.GRID
  );
  let viennaClassesData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.viennaClassesData"),
    ".vienna-classes-data-panel",
    DocumentSectionType.TEXT
  );
  let niceClassesData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.niceClassesData"),
    ".nice-classes-data-panel",
    DocumentSectionType.TEXT
  );
  let ownersData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.markApplicantOwnerData"),
    ".person-data-panel-" + PersonType.OWNER,
    DocumentSectionType.GRID
  );
  let representativesData = convertSectionDataTextBlock(
    componentRef,
    t(`l.panel.representativeData.${RegisterType.MARK}`),
    ".person-data-panel-" + PersonType.REPRESENTATIVE,
    DocumentSectionType.GRID
  );
  let priorityData = convertSectionDataTextBlock(
    componentRef,
    t(`l.panel.priorityData.${RegisterType.MARK}`),
    ".priority-data-panel",
    DocumentSectionType.GRID
  );
  let recordalsData = convertSectionDataTableJson(
    t(`l.panel.recordalData.${RegisterType.MARK}`),
    data?.recordals,
    getJsonTableHeadersByViewSectionType(ViewSectionType.RECORDALS, t),
    DocumentSectionType.TABLE
  );
  let oppositionsData = convertSectionDataTableJson(
    t("l.panel.oppositionData"),
    data?.oppositions,
    getJsonTableHeadersByViewSectionType(ViewSectionType.OPPOSITIONS, t),
    DocumentSectionType.TABLE
  );
  let cancellationsData = convertSectionDataTableJson(
    t("l.panel.cancellationData"),
    data?.cancellations,
    getJsonTableHeadersByViewSectionType(ViewSectionType.CANCELLATIONS, t),
    DocumentSectionType.TABLE
  );
  return [
    mainData,
    priorityData,
    relatedObjectsData,
    publicationData,
    imageData,
    coloursData,
    descriptionData,
    attachmentData,
    viennaClassesData,
    niceClassesData,
    ownersData,
    representativesData,
    recordalsData,
    oppositionsData,
    cancellationsData,
    convertDecisionsToDataTableJson(t, data),
  ];
};

const convertDecisionsToDataTableJson = (t, data) => {
  return convertSectionDataTableJson(
    t(`l.panel.objectDecisionsData`),
    data?.decisions,
    getJsonTableHeadersByViewSectionType(ViewSectionType.OBJECT_DECISIONS, t),
    DocumentSectionType.TABLE
  );
};

const convertJsonGeo = (t, componentRef, data) => {
  let mainData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.geoMainData"),
    ".main-data-panel",
    DocumentSectionType.TEXT
  );
  let relatedObjectsData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.relatedObjects"),
    ".related-objects-data-panel",
    DocumentSectionType.GRID
  );
  let publicationData = convertSectionDataTextBlock(
    componentRef,
    t(`l.panel.publicationData.${RegisterType.GEO_INDICATION}`),
    ".publication-data-panel",
    DocumentSectionType.GRID
  );
  let imageData = convertSectionDataImage(
    componentRef,
    t(`l.panel.drawingsData.${RegisterType.GEO_INDICATION}`),
    ".media-data-panel",
    DocumentSectionType.IMAGE
  );
  let viennaClassesData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.viennaClassesData"),
    ".vienna-classes-data-panel",
    DocumentSectionType.TEXT
  );
  let niceClassesData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.niceClassesData"),
    ".nice-classes-data-panel",
    DocumentSectionType.TEXT
  );
  let ownersData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.markApplicantOwnerData"),
    ".person-data-panel-" + PersonType.OWNER,
    DocumentSectionType.GRID
  );
  let representativesData = convertSectionDataTextBlock(
    componentRef,
    t(`l.panel.representativeData.${RegisterType.GEO_INDICATION}`),
    ".person-data-panel-" + PersonType.REPRESENTATIVE,
    DocumentSectionType.GRID
  );
  let priorityData = convertSectionDataTextBlock(
    componentRef,
    t(`l.panel.priorityData.${RegisterType.GEO_INDICATION}`),
    ".priority-data-panel",
    DocumentSectionType.GRID
  );
  let recordalsData = convertSectionDataTableJson(
    t(`l.panel.recordalData.${RegisterType.GEO_INDICATION}`),
    data?.recordals,
    getJsonTableHeadersByViewSectionType(ViewSectionType.RECORDALS, t),
    DocumentSectionType.TABLE
  );
  let oppositionsData = convertSectionDataTableJson(
    t("l.panel.oppositionData"),
    data?.oppositions,
    getJsonTableHeadersByViewSectionType(ViewSectionType.OPPOSITIONS, t),
    DocumentSectionType.TABLE
  );
  let cancellationsData = convertSectionDataTableJson(
    t("l.panel.cancellationData"),
    data?.cancellations,
    getJsonTableHeadersByViewSectionType(ViewSectionType.CANCELLATIONS, t),
    DocumentSectionType.TABLE
  );
  return [
    mainData,
    priorityData,
    relatedObjectsData,
    publicationData,
    imageData,
    viennaClassesData,
    niceClassesData,
    ownersData,
    representativesData,
    recordalsData,
    oppositionsData,
    cancellationsData,
    convertDecisionsToDataTableJson(t, data),
  ];
};

const convertJsonPatent = (t, componentRef, data) => {
  return [...convertJsonUtilityModel(t, componentRef, data)];
};

const convertJsonUtilityModel = (t, componentRef, data) => {
  let mainData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeMainData"),
    ".main-data-panel",
    DocumentSectionType.TEXT
  );
  let relatedObjectsData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.relatedObjects"),
    ".related-objects-data-panel",
    DocumentSectionType.GRID
  );
  let citationsData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeCitations"),
    ".citation-data-panel",
    DocumentSectionType.GRID
  );
  let cpcData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeCpcData"),
    ".cpc-data-panel",
    DocumentSectionType.LIST
  );
  let ipcData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeIpcData"),
    ".ipc-data-panel",
    DocumentSectionType.LIST
  );
  let publicationData = convertSectionDataTextBlock(
    componentRef,
    t(`l.panel.publicationData.${RegisterType.PATENT}`),
    ".publication-data-panel",
    DocumentSectionType.GRID
  );
  let abstractData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeAbstractData"),
    ".abstract-data-panel",
    DocumentSectionType.TEXT
  );
  let attachmentData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeAttachmentsData"),
    ".attachments-data-panel",
    DocumentSectionType.GRID
  );
  let ownersData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeApplicantOwnerData"),
    ".person-data-panel-" + PersonType.OWNER,
    DocumentSectionType.GRID
  );
  let representativesData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.representativeData"),
    ".person-data-panel-" + PersonType.REPRESENTATIVE,
    DocumentSectionType.GRID
  );
  let inventorsData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeInventorData"),
    ".person-data-panel-" + PersonType.INVENTOR,
    DocumentSectionType.GRID
  );
  let priorityData = convertSectionDataTextBlock(
    componentRef,
    t(`l.panel.priorityData.${RegisterType.PATENT}`),
    ".priority-data-panel",
    DocumentSectionType.GRID
  );
  let recordalsData = convertSectionDataTableJson(
    t("l.panel.recordalData"),
    data?.recordals,
    getJsonTableHeadersByViewSectionType(ViewSectionType.RECORDALS, t),
    DocumentSectionType.TABLE
  );
  let cancellationsData = convertSectionDataTableJson(
    t("l.panel.cancellationData"),
    data?.cancellations,
    getJsonTableHeadersByViewSectionType(ViewSectionType.CANCELLATIONS, t),
    DocumentSectionType.TABLE
  );
  return [
    mainData,
    priorityData,
    relatedObjectsData,
    citationsData,
    cpcData,
    ipcData,
    publicationData,
    abstractData,
    attachmentData,
    ownersData,
    representativesData,
    inventorsData,
    recordalsData,
    cancellationsData,
    convertDecisionsToDataTableJson(t, data),
  ];
};

const convertJsonDesign = (t, componentRef, data) => {
  let mainData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeMainData"),
    ".main-data-panel",
    DocumentSectionType.TEXT
  );
  let priorityData = convertSectionDataTextBlock(
    componentRef,
    t(`l.panel.priorityData.${RegisterType.PATENT}`),
    ".priority-data-panel",
    DocumentSectionType.GRID
  );
  let singleDesignsData = convertSectionDataTextImageConsecutive(
    componentRef,
    t(`l.panel.singleDesignsData`),
    ".single-designs-data-panel",
    ".docx-element-divider",
    ".docx-subheading",
    DocumentSectionType.IMAGE_CONSECUTIVE
  );
  let relatedObjectsData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.relatedObjects"),
    ".related-objects-data-panel",
    DocumentSectionType.GRID
  );
  let publicationData = convertSectionDataTextBlock(
    componentRef,
    t(`l.panel.publicationData.${RegisterType.PATENT}`),
    ".publication-data-panel",
    DocumentSectionType.GRID
  );
  let ownersData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeApplicantOwnerData"),
    ".person-data-panel-" + PersonType.OWNER,
    DocumentSectionType.GRID
  );
  let representativesData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.representativeData"),
    ".person-data-panel-" + PersonType.REPRESENTATIVE,
    DocumentSectionType.GRID
  );
  let inventorsData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeInventorData"),
    ".person-data-panel-" + PersonType.INVENTOR,
    DocumentSectionType.GRID
  );
  let recordalsData = convertSectionDataTableJson(
    t("l.panel.recordalData"),
    data?.recordals,
    getJsonTableHeadersByViewSectionType(ViewSectionType.RECORDALS, t),
    DocumentSectionType.TABLE
  );
  let cancellationsData = convertSectionDataTableJson(
    t("l.panel.cancellationData"),
    data?.cancellations,
    getJsonTableHeadersByViewSectionType(ViewSectionType.CANCELLATIONS, t),
    DocumentSectionType.TABLE
  );
  return [
    mainData,
    priorityData,
    singleDesignsData,
    relatedObjectsData,
    publicationData,
    ownersData,
    representativesData,
    inventorsData,
    recordalsData,
    cancellationsData,
    convertDecisionsToDataTableJson(t, data),
  ];
};

const convertJsonEuPatent = (t, componentRef, data) => {
  let mainData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeMainData"),
    ".main-data-panel",
    DocumentSectionType.TEXT
  );
  let relatedObjectsData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.relatedObjects"),
    ".related-objects-data-panel",
    DocumentSectionType.GRID
  );
  let cpcData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeCpcData"),
    ".cpc-data-panel",
    DocumentSectionType.LIST
  );
  let ipcData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeIpcData"),
    ".ipc-data-panel",
    DocumentSectionType.LIST
  );
  let publicationData = convertSectionDataTextBlock(
    componentRef,
    t(`l.panel.publicationData.${RegisterType.EU_PATENT}`),
    ".publication-data-panel",
    DocumentSectionType.GRID
  );
  let abstractData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeAbstractData"),
    ".abstract-data-panel",
    DocumentSectionType.TEXT
  );
  let attachmentData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeAttachmentsData"),
    ".attachments-data-panel",
    DocumentSectionType.GRID
  );
  let ownersData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeApplicantOwnerData"),
    ".person-data-panel-" + PersonType.OWNER,
    DocumentSectionType.GRID
  );
  let representativesData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.representativeData"),
    ".person-data-panel-" + PersonType.REPRESENTATIVE,
    DocumentSectionType.GRID
  );
  let inventorsData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeInventorData"),
    ".person-data-panel-" + PersonType.INVENTOR,
    DocumentSectionType.GRID
  );
  let priorityData = convertSectionDataTextBlock(
    componentRef,
    t(`l.panel.priorityData.${RegisterType.EU_PATENT}`),
    ".priority-data-panel",
    DocumentSectionType.GRID
  );
  let recordalsData = convertSectionDataTableJson(
    t("l.panel.recordalData"),
    data?.recordals,
    getJsonTableHeadersByViewSectionType(ViewSectionType.RECORDALS, t),
    DocumentSectionType.TABLE
  );
  let cancellationsData = convertSectionDataTableJson(
    t("l.panel.cancellationData"),
    data?.cancellations,
    getJsonTableHeadersByViewSectionType(ViewSectionType.CANCELLATIONS, t),
    DocumentSectionType.TABLE
  );
  return [
    mainData,
    priorityData,
    relatedObjectsData,
    cpcData,
    ipcData,
    publicationData,
    abstractData,
    attachmentData,
    ownersData,
    representativesData,
    inventorsData,
    recordalsData,
    cancellationsData,
    convertDecisionsToDataTableJson(t, data),
  ];
};

const convertJsonPlantBreed = (t, componentRef, data) => {
  let mainData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeMainData"),
    ".main-data-panel",
    DocumentSectionType.TEXT
  );
  let relatedObjectsData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.relatedObjects"),
    ".related-objects-data-panel",
    DocumentSectionType.GRID
  );
  let taxonData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.taxonData"),
    ".taxon-data-panel",
    DocumentSectionType.GRID
  );
  let abstractData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.plantBreedAbstractData"),
    ".abstract-data-panel",
    DocumentSectionType.TEXT
  );
  let publicationData = convertSectionDataTextBlock(
    componentRef,
    t(`l.panel.publicationData.${RegisterType.PLANT_BREED}`),
    ".publication-data-panel",
    DocumentSectionType.GRID
  );
  let attachmentsData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.plantAttachmentsData"),
    ".attachments-data-panel",
    DocumentSectionType.GRID
  );
  let ownersData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeApplicantOwnerData"),
    ".person-data-panel-" + PersonType.OWNER,
    DocumentSectionType.GRID
  );
  let representativesData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.representativeData"),
    ".person-data-panel-" + PersonType.REPRESENTATIVE,
    DocumentSectionType.GRID
  );
  let inventorsData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.plantBreedAuthorData"),
    ".person-data-panel-" + PersonType.INVENTOR,
    DocumentSectionType.GRID
  );
  let priorityData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.priorityData"),
    ".priority-data-panel",
    DocumentSectionType.GRID
  );
  let recordalsData = convertSectionDataTableJson(
    t("l.panel.recordalData"),
    data?.recordals,
    getJsonTableHeadersByViewSectionType(ViewSectionType.RECORDALS, t),
    DocumentSectionType.TABLE
  );
  let cancellationsData = convertSectionDataTableJson(
    t("l.panel.cancellationData"),
    data?.cancellations,
    getJsonTableHeadersByViewSectionType(ViewSectionType.CANCELLATIONS, t),
    DocumentSectionType.TABLE
  );
  return [
    mainData,
    priorityData,
    relatedObjectsData,
    taxonData,
    abstractData,
    publicationData,
    attachmentsData,
    ownersData,
    representativesData,
    inventorsData,
    recordalsData,
    cancellationsData,
    convertDecisionsToDataTableJson(t, data),
  ];
};

const convertJsonSPC = (t, componentRef, data) => {
  let mainData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeMainData"),
    ".main-data-panel",
    DocumentSectionType.TEXT
  );
  let relatedObjectsData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.relatedObjects"),
    ".related-objects-data-panel",
    DocumentSectionType.GRID
  );
  let cpcData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeCpcData"),
    ".cpc-data-panel",
    DocumentSectionType.LIST
  );
  let ipcData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeIpcData"),
    ".ipc-data-panel",
    DocumentSectionType.LIST
  );
  let publicationData = convertSectionDataTextBlock(
    componentRef,
    t(`l.panel.publicationData.${RegisterType.SPC}`),
    ".publication-data-panel",
    DocumentSectionType.GRID
  );
  let ownersData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeApplicantOwnerData"),
    ".person-data-panel-" + PersonType.OWNER,
    DocumentSectionType.GRID
  );
  let representativesData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.representativeData"),
    ".person-data-panel-" + PersonType.REPRESENTATIVE,
    DocumentSectionType.GRID
  );
  let inventorsData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.patentLikeInventorData"),
    ".person-data-panel-" + PersonType.INVENTOR,
    DocumentSectionType.GRID
  );
  let priorityData = convertSectionDataTextBlock(
    componentRef,
    t(`l.panel.priorityData.${RegisterType.SPC}`),
    ".priority-data-panel",
    DocumentSectionType.GRID
  );
  let recordalsData = convertSectionDataTableJson(
    t("l.panel.recordalData"),
    data?.recordals,
    getJsonTableHeadersByViewSectionType(ViewSectionType.RECORDALS, t),
    DocumentSectionType.TABLE
  );
  let cancellationsData = convertSectionDataTableJson(
    t("l.panel.cancellationData"),
    data?.cancellations,
    getJsonTableHeadersByViewSectionType(ViewSectionType.CANCELLATIONS, t),
    DocumentSectionType.TABLE
  );
  return [
    mainData,
    priorityData,
    relatedObjectsData,
    cpcData,
    ipcData,
    publicationData,
    ownersData,
    representativesData,
    inventorsData,
    recordalsData,
    cancellationsData,
    convertDecisionsToDataTableJson(t, data),
  ];
};

const convertJsonAgent = (t, componentRef, data) => {
  let mainData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.agentMainData"),
    ".main-data-panel",
    DocumentSectionType.TEXT
  );
  let relationsData = convertSectionDataTableJson(
    t("l.panel.agentPartnerships"),
    data?.agentRelations,
    getJsonTableHeadersByViewSectionType(ViewSectionType.AGENT_RELATIONS, t),
    DocumentSectionType.TABLE
  );
  let historyData = convertSectionDataCustom(t("t.panel.history"), data?.agentHistory, CustomSection.AGENT_HISTORY);
  return [mainData, relationsData, historyData];
};

const convertJsonPartnership = (t, componentRef, data) => {
  let mainData = convertSectionDataTextBlock(
    componentRef,
    t("l.panel.partnershipMainData"),
    ".main-data-panel",
    DocumentSectionType.TEXT
  );
  let relationsData = convertSectionDataTableJson(
    t("t.panel.agents"),
    data?.agentRelations,
    getJsonTableHeadersByViewSectionType(ViewSectionType.PARTNERSHIP_RELATIONS, t),
    DocumentSectionType.TABLE
  );
  let historyData = convertSectionDataCustom(
    t("t.panel.history"),
    data?.agentHistory,
    CustomSection.PARTNERSHIP_HISTORY
  );
  return [mainData, relationsData, historyData];
};
