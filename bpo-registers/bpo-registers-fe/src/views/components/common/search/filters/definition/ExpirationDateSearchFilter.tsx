import React from "react";
import { DateFromToFilter } from "@duosoftbg/bpo-components";

const ExpirationDateSearchFilter = () => {
  return (
    <DateFromToFilter
      label={"l.searchFilter.expirationDate"}
      from={`expirationDate.from`}
      to={`expirationDate.to`}
      futurePeriod
    />
  );
};
export default ExpirationDateSearchFilter;
