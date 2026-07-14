import { ColumnsDefinition } from "../../../../../../types/registers/search/table/columnsDefinition";

export const UNIVERSAL_COLUMNS: ColumnsDefinition = {
  objectId: {
    id: "objectId",
    label: "l.objectId",
    sortable: true,
    getValue: (object) => {
      return object.objectId;
    },
  },
  filingDate: {
    id: "filingDate",
    label: "l.filingDate",
    sortable: true,
    getValue: (object) => {
      return object.filingDate;
    },
  },
  registrationNumber: {
    id: "registrationNumber",
    label: `l.registrationNumber`,
    sortable: true,
    getValue: (object) => {
      return object.registrationNbr;
    },
  },
  name: {
    id: "name",
    label: "l.name",
    sortable: true,
    getValue: (object) => {
      return object.name;
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
  statusId: {
    id: "statusId",
    label: "l.object.status",
    sortable: true,
    getValue: (object) => {
      return object.statusName;
    },
  },
};
