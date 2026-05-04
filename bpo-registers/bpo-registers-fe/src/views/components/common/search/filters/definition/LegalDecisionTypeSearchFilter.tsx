import { GridItem, iTxt, ThunkAutocompleteFormField } from "@duosoftbg/bpo-components";
import { useTranslation } from "react-i18next";
import { legalDecisionTypesThunk } from "../../../../../../store/redux/slice/AppData/legalDecisionTypes";

const LegalDecisionTypeSearchFilter = () => {
  const { i18n } = useTranslation();
  return (
    <GridItem sm={6} md={6} lg={6}>
      <ThunkAutocompleteFormField
        multiple={false}
        disableCloseOnSelect={false}
        thunkFn={legalDecisionTypesThunk}
        reduxStateReturn={(state) => state.AppData.legalDecisionTypes}
        fieldName={"documentType"}
        label={"l.searchFilter.decisionType"}
        allowManualInput={false}
        isOptionEqualToValue={(op, value) => op.id === value.id}
        optionSelectedText={(op) => iTxt(i18n.language, op.name, op.nameEn)}
        optionListText={(op) => iTxt(i18n.language, op.name, op.nameEn)}
      />
    </GridItem>
  );
};
export default LegalDecisionTypeSearchFilter;
