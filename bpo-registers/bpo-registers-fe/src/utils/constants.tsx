import { useTranslation } from "react-i18next";
import { Box } from "@mui/material";
import React from "react";
import { concatNotEmptyBy, isNotEmpty } from "@duosoftbg/bpo-components";

export const YesMessage = () => {
  const { t } = useTranslation();
  return <Box style={{ color: "green" }}>{t("m.yes")}</Box>;
};

export const NoMessage = () => {
  const { t } = useTranslation();
  return <Box style={{ color: "red" }}>{t("m.no")}</Box>;
};

export enum ObjectType {
  GEO_INDICATION = "Г",
  PATENT = "P",
  SPC = "S",
  UTILITY_MODEL = "U",
  PLANT_BREED = "С",
  EU_PATENT = "T",
  DESIGN = "Д",
  MARK = "N",
  SINGLE_DESIGN = "Е",
  DIVISIONAL_MARK = "D",
}

export const RegistersConfigName = {
  SELECTED_FILTERS: "selectedFilters",
  SELECTED_TABLE_COLUMNS: "selectedTableColumns",
};

export const RegisterType = {
  MARK: "MARK",
  PATENT: "PATENT",
  EU_PATENT: "EU_PATENT",
  GEO_INDICATION: "GEO_INDICATION",
  UTILITY_MODEL: "UTILITY_MODEL",
  PLANT_BREED: "PLANT_BREED",
  SPC: "SPC",
  DESIGN: "DESIGN",
  COMBINED: "COMBINED",
  DECISIONS: "DECISIONS",
  AGENT: "AGENT",
  PARTNERSHIP: "PARTNERSHIP",
};

export const RepresentativeType = {
  AGENT: ["AGENT"],
  PARTNERSHIP: ["PARTNERSHIP", "COMPANY"],
};

export const RecordalDetailType = {
  OPPONENT: "OPPONENT",
  STATUS: "STATUS",
  DECISION: "DECISION",
  CLAIMANT: "CLAIMANT",
};

export const getRegisterTypeByObjectType = (objectType) => {
  switch (objectType) {
    case ObjectType.PATENT:
      return RegisterType.PATENT;
    case ObjectType.UTILITY_MODEL:
      return RegisterType.UTILITY_MODEL;
    case ObjectType.EU_PATENT:
      return RegisterType.EU_PATENT;
    case ObjectType.PLANT_BREED:
      return RegisterType.PLANT_BREED;
    case ObjectType.SPC:
      return RegisterType.SPC;
    case ObjectType.GEO_INDICATION:
      return RegisterType.GEO_INDICATION;
    case ObjectType.DESIGN:
    case ObjectType.SINGLE_DESIGN:
      return RegisterType.DESIGN;
    case ObjectType.MARK:
    case ObjectType.DIVISIONAL_MARK:
      return RegisterType.MARK;
    default:
      return null;
  }
};

export const PersonType = {
  OWNER: "OWNER",
  INVENTOR: "INVENTOR",
  REPRESENTATIVE: "REPRESENTATIVE",
};

export const GlobalPanelControl = {
  OPEN: "OPEN",
  MANUAL: "MANUAL",
  CLOSED: "CLOSED",
};

export const isAgentCaDataNotEmpty = (person) => {
  return isNotEmpty(
    concatNotEmptyBy(",")(
      person?.agent?.address?.addressStreetCa,
      person?.agent?.address?.addressStreetCaEn,
      person?.agent?.address?.zipCodeCa,
      person?.agent?.address?.phoneCa,
      person?.agent?.address?.emailCa,
      person?.agent?.address?.faxCa,
      person?.agent?.address?.cityNameCa,
      person?.agent?.address?.cityNameCaEn
    )
  );
};

export const RelationshipType = {
  SPC_NONE: "SPC_NONE",
  D_NONE: "Д_NONE",
  OP_NONE: "ОП_NONE",
  RD_NONE: "РД_NONE",
  REP_NONE: "РЕП_NONE",
  RNM_NONE: "РНМ_NONE",
  RNP_NONE: "РНП_NONE",
  RPM_NONE: "РПМ_NONE",
  TM_NONE: "ТМ_NONE",
  TP_NONE: "ТП_NONE",
  TM_EM: "ТМ_EM",
  TM_WO: "ТМ_WO",
  TP_WO: "ТП_WO",
  TP_EP: "ТП_EP",
  RSP_NONE: "РСП_NONE",
  ZAM_WO: "ЗАМ_WO",
  MR_WO: "МР_WO",
  SEN_NONE: "СТР_NONE",
};

export const MarkKind = {
  A: "A",
  C: "C",
  D: "D",
  E: "E",
  F: "F",
  H: "H",
  M: "M",
  I: "I",
  P: "P",
  S: "S",
  T: "T",
  U: "U",
};

export const CellType = {
  IMAGE: "IMAGE",
  FLAG: "FLAG",
  TEXT: "TEXT",
};
