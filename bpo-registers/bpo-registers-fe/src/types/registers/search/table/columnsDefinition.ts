type ColumnName =
  | "filingDate"
  | "objectId"
  | "title"
  | "name"
  | "agentCode"
  | "agentName"
  | "agentSpeciality"
  | "ipoArea"
  | "partnershipName"
  | "representativeType"
  | "agentStatus"
  | "registrationNumber"
  | "niceClasses"
  | "mainOwner"
  | "statusId"
  | "documentDate"
  | "documentNumber"
  | "documentType"
  | "image";

export type ColumnsDefinition = {
  [key in ColumnName]?: {
    id: ColumnName;
    label: string;
    sortable: boolean;
    getValue: any;
    type?: string;
  };
};
