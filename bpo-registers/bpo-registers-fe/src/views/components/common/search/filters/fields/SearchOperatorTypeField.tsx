import { SearchOperatorType } from "../../../../../../types/registers/search/filter/filterTypes";
import { RadiosFormField } from "@duosoftbg/bpo-components";
import { useTranslation } from "react-i18next";

const SearchOperatorTypeField = ({ searchOperatorTypeField }) => {
  const { t } = useTranslation();

  return (
    <RadiosFormField
      isInline={true}
      fieldName={searchOperatorTypeField}
      labelCode={""}
      radioOptions={Object.keys(SearchOperatorType).map((key) => {
        return { value: key.valueOf(), text: t(`l.operator.search.type.${key.valueOf()}`) };
      })}
    />
  );
};
export default SearchOperatorTypeField;
