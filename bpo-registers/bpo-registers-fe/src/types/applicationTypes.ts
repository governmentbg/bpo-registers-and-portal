export interface ApplicationTypesDetails {
  id: string;
  name: string;
  isActive: boolean;
}

export interface ApplicationTypesFilterDetails {
  id: string;
  name: string;
  isActive: string;
  page: number;
  pageSize: number;
  order: string;
  orderBy: string;
}
