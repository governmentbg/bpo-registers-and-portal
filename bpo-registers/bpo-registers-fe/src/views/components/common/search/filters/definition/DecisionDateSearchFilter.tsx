import { DateFromToFilter } from "@duosoftbg/bpo-components";
import React from "react";

const DecisionDateSearchFilter = () => {
  return <DateFromToFilter label={"l.searchFilter.decisionDate"} from={`documentDate.from`} to={`documentDate.to`} />;
};
export default DecisionDateSearchFilter;
