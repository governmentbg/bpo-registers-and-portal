import { AgentFilterDetails, PartnershipFilterDetails } from "../../../../types/registers/search/filter/filterTypes";
import { commonAgentFiltersInitialValues } from "./commonInitialValues";
import { RepresentativeType } from "../../../../utils/constants";

export const agentsFiltersInitialValues: AgentFilterDetails = {
  ...commonAgentFiltersInitialValues,
  representativeTypeList: RepresentativeType.AGENT,
  agentSpeciality: "",
};

export const partnershipsFiltersInitialValues: PartnershipFilterDetails = {
  ...commonAgentFiltersInitialValues,
  representativeTypeList: RepresentativeType.PARTNERSHIP,
};
