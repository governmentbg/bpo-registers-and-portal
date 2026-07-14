import { ColumnsDefinition } from "../../../../../../types/registers/search/table/columnsDefinition";
import { iTxt } from "@duosoftbg/bpo-components";
import i18n from "i18next";

export const DECISION_COLUMNS: ColumnsDefinition = {
  objectId: {
    id: "objectId",
    label: "l.objectId",
    sortable: true,
    getValue: (object) => {
      return object.objectId;
    },
  },
  documentDate: {
    id: "documentDate",
    label: "l.documentDate",
    sortable: true,
    getValue: (object) => {
      return object.documentDate;
    },
  },
  documentNumber: {
    id: "documentNumber",
    label: "l.documentNumber",
    sortable: true,
    getValue: (object) => {
      return object.documentNumber;
    },
  },
  title: {
    id: "title",
    label: "l.title",
    sortable: true,
    getValue: (object) => {
      return object.title;
    },
  },
  documentType: {
    id: "documentType",
    label: "l.documentType",
    sortable: true,
    getValue: (object) => {
      return iTxt(i18n.language, object.documentType.name, object.documentType.nameEn);
    },
  },
};
