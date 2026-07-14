import { iTxt } from "@duosoftbg/bpo-components";
import i18n from "i18next";

export const ViewSectionType = {
  RECORDALS: "RECORDALS",
  OPPOSITIONS: "OPPOSITIONS",
  CANCELLATIONS: "CANCELLATIONS",
  AGENT_RELATIONS: "AGENT_RELATIONS",
  PARTNERSHIP_RELATIONS: "PARTNERSHIP_RELATIONS",
  OBJECT_DECISIONS: "OBJECT_DECISIONS",
};

export const getJsonTableHeadersByViewSectionType = (viewSectionType, t) => {
  let decisionsHeaders = [
    {
      label: t("l.table.head.decision.docType"),
      key: iTxt(i18n.language, "documentType.name", "documentType.nameEn"),
    },
    { label: t("l.table.head.decision.docNumber"), key: "documentNumber" },
    { label: t("l.table.head.decision.docDate"), key: "documentDate" },
    { label: t("l.table.head.decision.link"), key: "attachment.fileName" },
  ];

  let recordalsHeaders = [
    { label: t("l.table.head.recordalNumber"), key: "recordalNumber" },
    {
      label: t("l.table.head.recordalType"),
      key: iTxt(i18n.language, "recordalType.description", "recordalType.descriptionEn"),
    },
    { label: t("l.table.head.recordalDate"), key: "registrationDate" },
    { label: t("l.table.head.invalidationDate"), key: "invalidationDate" },
    { label: "extraData", key: "details" },
    { label: "extraData.label", key: iTxt(i18n.language, "type.description", "type.descriptionEn") },
    { label: "extraData.text", key: "description" },
  ];

  let oppositionsHeaders = [
    {
      label: t("l.table.head.opposition.recordalType"),
      key: iTxt(i18n.language, "recordalType.description", "recordalType.descriptionEn"),
    },
    { label: t("l.table.head.recordalNumber"), key: "recordalNumber" },
    { label: t("l.table.head.status"), key: iTxt(i18n.language, "status.bpoOnlineStatus", "status.bpoOnlineStatusEn") },
    { label: "extraData", key: "details" },
    { label: "extraData.label", key: iTxt(i18n.language, "type.description", "type.descriptionEn") },
    { label: "extraData.text", key: "description" },
  ];

  let cancellationsHeaders = [
    {
      label: t("l.table.head.cancellation.recordalType"),
      key: iTxt(i18n.language, "recordalType.description", "recordalType.descriptionEn"),
    },
    { label: t("l.table.head.recordalNumber"), key: "recordalNumber" },
    { label: t("l.table.head.status"), key: iTxt(i18n.language, "status.bpoOnlineStatus", "status.bpoOnlineStatusEn") },
    { label: "extraData", key: "details" },
    { label: "extraData.label", key: iTxt(i18n.language, "type.description", "type.descriptionEn") },
    { label: "extraData.text", key: "description" },
  ];

  let agentRelationsHeaders = [
    {
      label: t("l.table.head.partnershipName"),
      key: iTxt(i18n.language, "name", "agent.nameEn"),
    },
    { label: t("l.table.head.agentCode"), key: "agent.agentCode" },
    {
      label: t("l.table.head.representativeType"),
      key: iTxt(i18n.language, "agent.representativeType.description", "agent.representativeType.descriptionEn"),
    },
    {
      label: t("l.table.head.ipoArea"),
      key: iTxt(i18n.language, "agent.agentSpeciality.name", "agent.agentSpeciality.nameEn"),
    },
  ];

  let partnershipRelationsHeaders = [
    {
      label: t("l.table.head.agentName"),
      key: iTxt(i18n.language, "name", "agent.nameEn"),
    },
    { label: t("l.table.head.agentCode"), key: "agent.agentCode" },
    {
      label: t("l.table.head.agentSpeciality"),
      key: iTxt(i18n.language, "agent.speciality", "agent.specialityEn"),
    },
    {
      label: t("l.table.head.ipoArea"),
      key: iTxt(i18n.language, "agent.agentSpeciality.name", "agent.agentSpeciality.nameEn"),
    },
  ];

  switch (viewSectionType) {
    case ViewSectionType.RECORDALS:
      return recordalsHeaders;
    case ViewSectionType.OPPOSITIONS:
      return oppositionsHeaders;
    case ViewSectionType.CANCELLATIONS:
      return cancellationsHeaders;
    case ViewSectionType.AGENT_RELATIONS:
      return agentRelationsHeaders;
    case ViewSectionType.PARTNERSHIP_RELATIONS:
      return partnershipRelationsHeaders;
    case ViewSectionType.OBJECT_DECISIONS:
      return decisionsHeaders;
  }
};
