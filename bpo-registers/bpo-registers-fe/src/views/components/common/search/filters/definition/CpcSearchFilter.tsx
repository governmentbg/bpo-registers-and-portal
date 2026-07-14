import { GridItem, RadiosFormField } from "@duosoftbg/bpo-components";
import { CpcSearchType } from "../../../../../../types/registers/search/filter/filterTypes";
import { useFormContext } from "react-hook-form";
import { useTranslation } from "react-i18next";
import { useEffect } from "react";
import { patentClassificationFiltersInitialValues } from "../../../../../../init/registers/search/filter/patentLikeFiltersInitialValues";
import CpcCodeSearchFilter from "./CpcCodeSearchFilter";
import CpcAutocompleteSearchFilter from "./CpcAutocompleteSearchFilter";

const CpcSearchFilter = () => {
  const { t } = useTranslation();
  const { setValue, getValues } = useFormContext();

  const handleTypeSelect = () => {
    if (getValues("cpcSearchType") === CpcSearchType.CODE) {
      setValue("cpcClasses", patentClassificationFiltersInitialValues.cpcClasses);
    } else {
      setValue("cpcCode", patentClassificationFiltersInitialValues.cpcCode);
    }
  };

  // unmount event
  useEffect(() => {
    return () => {
      // Cleanup logic here, e.g., resetting form values or any other necessary cleanup
      setValue("cpcSearchType", patentClassificationFiltersInitialValues.cpcSearchType);
      setValue("cpcClasses", patentClassificationFiltersInitialValues.cpcClasses);
      setValue("cpcCode", patentClassificationFiltersInitialValues.cpcCode);
    };
  }, [setValue]);

  return (
    <>
      <GridItem sm={12} md={12} lg={12}>
        <RadiosFormField
          isInline={true}
          labelCode={"l.searchFilter.cpcSearchType"}
          fieldName={"cpcSearchType"}
          radioOptions={Object.values(CpcSearchType).map((type) => {
            return { value: type.valueOf(), text: t("l.searchFilter.cpcSearchType." + type.valueOf()) };
          })}
          onChange={handleTypeSelect}
        />
      </GridItem>
      <CpcCodeSearchFilter />
      <CpcAutocompleteSearchFilter />
    </>
  );
};
export default CpcSearchFilter;
