import { DateFromToFilter } from "@duosoftbg/bpo-components";
import React from "react";

const FilingDateSearchFilter = () => {
  return <DateFromToFilter label={"l.searchFilter.filingDate"} from={`filingDate.from`} to={`filingDate.to`} />;
};
export default FilingDateSearchFilter;
