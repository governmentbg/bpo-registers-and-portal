import { ColumnsDefinition } from "../../../../../../types/registers/search/table/columnsDefinition";
import { iTxt } from "@duosoftbg/bpo-components";
import i18n from "i18next";

export const REPRESENTATIVE_LIKE_COLUMNS: ColumnsDefinition = {
  agentCode: {
    id: "agentCode",
    label: "l.agentCode",
    sortable: true,
    getValue: (object) => {
      return object.agent.agentCode;
    },
  },
  agentName: {
    id: "agentName",
    label: "l.agentName",
    sortable: true,
    getValue: (object) => {
      return iTxt(i18n.language, object.name, object.agent.nameEn);
    },
  },
  representativeType: {
    id: "representativeType",
    label: "l.representativeType",
    sortable: false,
    getValue: (object) => {
      return iTxt(
        i18n.language,
        object.agent.representativeType.description,
        object.agent.representativeType.descriptionEn
      );
    },
  },
  partnershipName: {
    id: "partnershipName",
    label: "l.name",
    sortable: true,
    getValue: (object) => {
      return iTxt(i18n.language, object.name, object.agent.nameEn);
    },
  },
  agentSpeciality: {
    id: "agentSpeciality",
    label: "l.agentSpeciality",
    sortable: true,
    getValue: (object) => {
      return iTxt(i18n.language, object.agent.speciality, object.agent.specialityEn);
    },
  },
  ipoArea: {
    id: "ipoArea",
    label: "l.ipoArea",
    sortable: true,
    getValue: (object) => {
      return iTxt(i18n.language, object.agent.agentSpeciality.name, object.agent.agentSpeciality.nameEn);
    },
  },
  agentStatus: {
    id: "agentStatus",
    label: "l.agentStatus",
    sortable: true,
    getValue: (object) => {
      return iTxt(i18n.language, object.agent.agentStatus.name, object.agent.agentStatus.nameEn);
    },
  },
};
