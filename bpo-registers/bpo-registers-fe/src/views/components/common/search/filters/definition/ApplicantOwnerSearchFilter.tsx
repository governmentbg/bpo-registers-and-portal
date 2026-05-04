import NameSearchWithTypeFilter from "./NameSearchWithTypeFilter";
import { GridItem, iTxt, SelectFormField, useAppDispatch } from "@duosoftbg/bpo-components";
import { getI18n } from "react-i18next";
import React, { useEffect } from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { countriesThunk } from "../../../../../../store/redux/slice/AppData/countries";

const ApplicantOwnerSearchFilter = () => {
  const countries = useAppSelector((state) => {
    return state.AppData.countries;
  });
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(countriesThunk());
  }, [dispatch]);
  return (
    <>
      <NameSearchWithTypeFilter
        nameField={"applicantOwner"}
        searchTypeField={"applicantOwnerPersonSearchType"}
        nameFieldLabelCode={"l.searchFilter.applicantOwner.name"}
        sm={12}
        md={8}
        lg={8}
      />
      <GridItem sm={12} md={4} lg={4}>
        <SelectFormField
          fieldName={"applicantOwnerCountry.id"}
          labelCode={"l.searchFilter.country"}
          selectOptions={countries.data.map((country) => {
            return { value: country.id, text: iTxt(getI18n().language, country.name, country.nameEn) };
          })}
          addEmptyOption={true}
        />
      </GridItem>
    </>
  );
};
export default ApplicantOwnerSearchFilter;
