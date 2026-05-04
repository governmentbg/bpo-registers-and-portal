import { GridItem, InputFormField, RadiosFormField } from "@duosoftbg/bpo-components";
import { TextSearchType } from "../../../../../../types/registers/search/filter/filterTypes";
import { useTranslation } from "react-i18next";

const NameSearchWithTypeFilter = ({
  nameField,
  searchTypeField,
  nameFieldLabelCode,
  xs = 12,
  sm = 3,
  md = 3,
  lg = 3,
}) => {
  const { t } = useTranslation();

  return (
    <>
      <GridItem xs={xs} sm={sm} md={md} lg={lg}>
        <InputFormField fieldName={nameField} labelCode={nameFieldLabelCode} />
        <RadiosFormField
          isInline={true}
          fieldName={searchTypeField}
          labelCode={""}
          radioOptions={Object.keys(TextSearchType).map((key) => {
            return { value: key.valueOf(), text: t(`l.text.search.type.${key.valueOf()}`) };
          })}
        />
      </GridItem>
    </>
  );
};
export default NameSearchWithTypeFilter;
