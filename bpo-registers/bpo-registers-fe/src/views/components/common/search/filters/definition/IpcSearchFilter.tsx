import { GridItem, RadiosFormField } from "@duosoftbg/bpo-components";
import { IpcSearchType } from "../../../../../../types/registers/search/filter/filterTypes";
import { useFormContext } from "react-hook-form";
import { useTranslation } from "react-i18next";
import { useEffect } from "react";
import IpcCodeSearchFilter from "./IpcCodeSearchFilter";
import IpcAutocompleteSearchFilter from "./IpcAutocompleteSearchFilter";
import { patentClassificationFiltersInitialValues } from "../../../../../../init/registers/search/filter/patentLikeFiltersInitialValues";

const IpcSearchFilter = () => {
  const { t } = useTranslation();
  const { setValue, getValues } = useFormContext();

  const handleTypeSelect = () => {
    if (getValues("ipcSearchType") === IpcSearchType.CODE) {
      setValue("ipcClasses", patentClassificationFiltersInitialValues.ipcClasses);
    } else {
      setValue("ipcCode", patentClassificationFiltersInitialValues.ipcCode);
    }
  };

  // unmount event
  useEffect(() => {
    return () => {
      // Cleanup logic here, e.g., resetting form values or any other necessary cleanup
      setValue("ipcSearchType", patentClassificationFiltersInitialValues.ipcSearchType);
      setValue("ipcClasses", patentClassificationFiltersInitialValues.ipcClasses);
      setValue("ipcCode", patentClassificationFiltersInitialValues.ipcCode);
    };
  }, [setValue]);

  return (
    <>
      <GridItem sm={12} md={12} lg={12}>
        <RadiosFormField
          isInline={true}
          labelCode={"l.searchFilter.ipcSearchType"}
          fieldName={"ipcSearchType"}
          radioOptions={Object.values(IpcSearchType).map((type) => {
            return { value: type.valueOf(), text: t("l.searchFilter.ipcSearchType." + type.valueOf()) };
          })}
          onChange={handleTypeSelect}
        />
      </GridItem>
      <IpcCodeSearchFilter />
      <IpcAutocompleteSearchFilter />
    </>
  );
};
export default IpcSearchFilter;
