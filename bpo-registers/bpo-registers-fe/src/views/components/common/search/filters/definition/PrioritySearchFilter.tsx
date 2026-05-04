import {
  DateFromToFilter,
  GridItem,
  InputFormField,
  iTxt,
  SelectFormField,
  useAppDispatch,
} from "@duosoftbg/bpo-components";
import React, { useEffect } from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { countriesThunk } from "../../../../../../store/redux/slice/AppData/countries";
import { getI18n } from "react-i18next";

const PrioritySearchFilter = () => {
  const countries = useAppSelector((state) => {
    return state.AppData.countries;
  });
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(countriesThunk());
  }, [dispatch]);

  if (!countries || !countries.data) {
    return null;
  }
  return (
    <>
      <DateFromToFilter
        label={"l.searchFilter.priorityDate"}
        from={`priority.priorityDate.from`}
        to={`priority.priorityDate.to`}
      />

      <GridItem sm={4} md={4} lg={4}>
        <SelectFormField
          fieldName={"priority.priorityCountry.id"}
          labelCode={"l.searchFilter.country"}
          selectOptions={countries.data.map((country) => {
            return { value: country.id, text: iTxt(getI18n().language, country.name, country.nameEn) };
          })}
          addEmptyOption={true}
        />
      </GridItem>
      <GridItem sm={2} md={2} lg={2}>
        <InputFormField fieldName={"priority.priorityNumber"} labelCode={"l.searchFilter.number"} />
      </GridItem>
    </>
  );
};
export default PrioritySearchFilter;
