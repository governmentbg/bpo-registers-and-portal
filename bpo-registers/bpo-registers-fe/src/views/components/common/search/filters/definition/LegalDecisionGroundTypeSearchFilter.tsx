import { GridItem, iTxt, ThunkAutocompleteFormField } from "@duosoftbg/bpo-components";
import { useTranslation } from "react-i18next";
import { legalDecisionGroundTypesThunk } from "../../../../../../store/redux/slice/AppData/legalDecisionGroundTypes";
import SearchOperatorTypeField from "../fields/SearchOperatorTypeField";
import { useEffect } from "react";
import { useFormContext } from "react-hook-form";
import { decisionFiltersInitialValues } from "../../../../../../init/registers/search/filter/decisionFiltersInitialValues";

const LegalDecisionGroundTypeSearchFilter = () => {
  const { i18n } = useTranslation();
  const { setValue } = useFormContext();

  useEffect(() => {
    return () => {
      setValue("legalGroundTypesOperatorType", decisionFiltersInitialValues.legalGroundTypesOperatorType);
    };
  }, [setValue]);

  return (
    <GridItem sm={6} md={6} lg={6}>
      <ThunkAutocompleteFormField
        multiple={true}
        disableCloseOnSelect={true}
        thunkFn={legalDecisionGroundTypesThunk}
        reduxStateReturn={(state) => state.AppData.legalDecisionGroundTypes}
        fieldName={"legalGroundTypes"}
        label={"l.searchFilter.decisionGroundType"}
        allowManualInput={false}
        isOptionEqualToValue={(op, value) => op.id === value.id}
        optionSelectedText={(op) => iTxt(i18n.language, op.name, op.nameEn)}
        optionListText={(op) => iTxt(i18n.language, op.name, op.nameEn)}
      />
      <SearchOperatorTypeField searchOperatorTypeField={"legalGroundTypesOperatorType"} />
    </GridItem>
  );
};
export default LegalDecisionGroundTypeSearchFilter;
