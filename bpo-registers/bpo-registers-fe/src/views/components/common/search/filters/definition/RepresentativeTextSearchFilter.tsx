import NameSearchWithTypeFilter from "./NameSearchWithTypeFilter";
import { GridItem, ThunkAutocompleteFormField, iTxt } from "@duosoftbg/bpo-components";
import { useWatch } from "react-hook-form";
import { RepresentativeSearchType } from "../../../../../../types/registers/search/filter/filterTypes";
import { useTranslation } from "react-i18next";
import { representativeTypesThunk } from "../../../../../../store/redux/slice/AppData/representativeTypes";

const RepresentativeTextSearchFilter = () => {
  const searchType = useWatch({ name: "representativeSearchType" });
  const { i18n } = useTranslation();

  if (RepresentativeSearchType.TEXT !== searchType) {
    return null;
  }

  return (
    <>
      <GridItem sm={12} md={12} lg={12}>
        <ThunkAutocompleteFormField
          multiple={true}
          disableCloseOnSelect={true}
          thunkFn={representativeTypesThunk}
          reduxStateReturn={(state) => state.AppData.representativeTypes}
          fieldName={"representativeTypes"}
          label={"l.searchFilter.representativeTypes"}
          allowManualInput={false}
          isOptionEqualToValue={(op, value) => op.id === value.id}
          optionSelectedText={(op) => iTxt(i18n.language, op.description, op.descriptionEn)}
          optionListText={(op) => iTxt(i18n.language, op.description, op.descriptionEn)}
        />
      </GridItem>
      <NameSearchWithTypeFilter
        nameField={"representativeName"}
        searchTypeField={"representativeNameSearchType"}
        nameFieldLabelCode={"l.searchFilter.representative.name"}
        sm={12}
        md={12}
        lg={12}
      />
    </>
  );
};
export default RepresentativeTextSearchFilter;
