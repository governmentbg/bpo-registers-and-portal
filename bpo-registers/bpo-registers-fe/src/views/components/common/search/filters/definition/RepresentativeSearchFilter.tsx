import { GridItem, RadiosFormField } from "@duosoftbg/bpo-components";
import RepresentativeTextSearchFilter from "./RepresentativeTextSearchFilter";
import { RepresentativeSearchType } from "../../../../../../types/registers/search/filter/filterTypes";
import { useFormContext } from "react-hook-form";
import { commonIpObjectFiltersInitialValues } from "../../../../../../init/registers/search/filter/commonInitialValues";
import RepresentativeAutocompleteFilter from "./RepresentativeAutocompleteFilter";
import { useTranslation } from "react-i18next";
import { useEffect } from "react";

const RepresentativeSearchFilter = () => {
  const { t } = useTranslation();
  const { setValue, getValues } = useFormContext();

  const handleTypeSelect = () => {
    if (getValues("representativeSearchType") === RepresentativeSearchType.TEXT) {
      setValue("representatives", commonIpObjectFiltersInitialValues.representatives);
    } else {
      setValue("representativeTypes", commonIpObjectFiltersInitialValues.representativeTypes);
      setValue("representativeName", commonIpObjectFiltersInitialValues.representativeName);
      setValue("representativeNameSearchType", commonIpObjectFiltersInitialValues.representativeNameSearchType);
    }
  };

  // unmount event
  useEffect(() => {
    return () => {
      // Cleanup logic here, e.g., resetting form values or any other necessary cleanup
      setValue("representativeSearchType", commonIpObjectFiltersInitialValues.representativeSearchType);
      setValue("representatives", commonIpObjectFiltersInitialValues.representatives);
      setValue("representativeTypes", commonIpObjectFiltersInitialValues.representativeTypes);
      setValue("representativeName", commonIpObjectFiltersInitialValues.representativeName);
      setValue("representativeNameSearchType", commonIpObjectFiltersInitialValues.representativeNameSearchType);
    };
  }, [setValue]);

  return (
    <>
      <GridItem sm={12} md={12} lg={12}>
        <RadiosFormField
          isInline={true}
          labelCode={"l.searchFilter.representativeSearchType"}
          fieldName={"representativeSearchType"}
          radioOptions={Object.values(RepresentativeSearchType).map((type) => {
            return { value: type.valueOf(), text: t("l.searchFilter.representativeSearchType." + type.valueOf()) };
          })}
          onChange={handleTypeSelect}
        />
      </GridItem>
      <RepresentativeTextSearchFilter />
      <RepresentativeAutocompleteFilter />
    </>
  );
};
export default RepresentativeSearchFilter;
