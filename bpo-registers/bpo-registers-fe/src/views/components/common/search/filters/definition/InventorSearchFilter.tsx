import NameSearchWithTypeFilter from "./NameSearchWithTypeFilter";
import { GridItem, iTxt, SelectFormField, useAppDispatch } from "@duosoftbg/bpo-components";
import { getI18n } from "react-i18next";
import React, { useEffect } from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { countriesThunk } from "../../../../../../store/redux/slice/AppData/countries";

const InventorSearchFilter = ({ isAuthor = false }) => {
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
        nameField={"inventor"}
        searchTypeField={"inventorPersonSearchType"}
        nameFieldLabelCode={isAuthor ? "l.searchFilter.author.name" : "l.searchFilter.inventor.name"}
        sm={12}
        md={8}
        lg={8}
      />
      <GridItem sm={12} md={4} lg={4}>
        <SelectFormField
          fieldName={"inventorCountry.id"}
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
export default InventorSearchFilter;
