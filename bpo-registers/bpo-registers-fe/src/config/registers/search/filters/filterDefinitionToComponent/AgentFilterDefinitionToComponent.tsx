import React from "react";
import AgentCodeSearchFilter from "../../../../../views/components/common/search/filters/definition/AgentCodeSearchFilter";
import AgentSpecialitySearchFilter from "../../../../../views/components/common/search/filters/definition/AgentSpecialitySearchFilter";
import IpoAreaSearchFilter from "../../../../../views/components/common/search/filters/definition/IpoAreaSearchFilter";
import AgentStatusSearchFilter from "../../../../../views/components/common/search/filters/definition/AgentStatusSearchFilter";
import AgentNameSearchFilter from "../../../../../views/components/common/search/filters/definition/AgentNameSearchFilter";
import AgentCitySearchFilter from "../../../../../views/components/common/search/filters/definition/AgentCitySearchFilter";
import { AGENT_FILTERS, PARTNERSHIP_FILTERS } from "../definition/slice/representativeFilters";

export const AGENT_FILTER_DEFINITION_TO_COMPONENT = [
  { id: AGENT_FILTERS.agentName.id, component: <AgentNameSearchFilter /> },
  { id: AGENT_FILTERS.ipoArea.id, component: <IpoAreaSearchFilter /> },
  { id: AGENT_FILTERS.agentCode.id, component: <AgentCodeSearchFilter /> },
  { id: AGENT_FILTERS.status.id, component: <AgentStatusSearchFilter /> },
  { id: AGENT_FILTERS.city.id, component: <AgentCitySearchFilter /> },
  {
    id: AGENT_FILTERS.agentSpeciality.id,
    component: <AgentSpecialitySearchFilter />,
  },
];

export const PARTNERSHIP_FILTER_DEFINITION_TO_COMPONENT = [
  { id: PARTNERSHIP_FILTERS.partnershipName.id, component: <AgentNameSearchFilter label={"l.name"} /> },
  { id: PARTNERSHIP_FILTERS.ipoArea.id, component: <IpoAreaSearchFilter /> },
  { id: PARTNERSHIP_FILTERS.agentCode.id, component: <AgentCodeSearchFilter /> },
  { id: PARTNERSHIP_FILTERS.status.id, component: <AgentStatusSearchFilter /> },
  { id: PARTNERSHIP_FILTERS.city.id, component: <AgentCitySearchFilter /> },
  {
    id: PARTNERSHIP_FILTERS.agentSpeciality.id,
    component: <AgentSpecialitySearchFilter />,
  },
];
