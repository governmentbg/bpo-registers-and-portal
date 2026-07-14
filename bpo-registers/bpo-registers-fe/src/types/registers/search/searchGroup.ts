export type SearchGroupName =
  | "mark_search"
  | "gi_search"
  | "patent_search"
  | "design_search"
  | "eu_patent_search"
  | "utility_model_search"
  | "spc_search"
  | "plant_breed_search"
  | "decision_search"
  | "combined_search"
  | "partnership_search"
  | "agent_search";

export type SearchGroup = { [key: string]: SearchGroupName };
