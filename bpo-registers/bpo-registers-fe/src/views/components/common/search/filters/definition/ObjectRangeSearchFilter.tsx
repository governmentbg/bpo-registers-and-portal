import { CheckboxListFormField, GridItem, iTxt } from "@duosoftbg/bpo-components";
import { getI18n } from "react-i18next";
import { ObjectType } from "../../../../../../utils/constants";

type ObjectRangeSearchFilterProps = {
  xs?: number;
  sm?: number;
  md?: number;
  lg?: number;
};

const ObjectRangeSearchFilter = ({ xs = 12, sm = 12, md = 12, lg = 12 }: ObjectRangeSearchFilterProps) => {
  const objectRange = [
    { id: ObjectType.MARK, description: "Марки", descriptionEn: "Trade marks" },
    { id: ObjectType.GEO_INDICATION, description: "Географски означения", descriptionEn: "Geographical indications" },
    { id: ObjectType.PATENT, description: "Национални патенти", descriptionEn: "Patents" },
    { id: ObjectType.EU_PATENT, description: "Европейски Патенти", descriptionEn: "European patents" },
    { id: ObjectType.UTILITY_MODEL, description: "Полезни модели", descriptionEn: "Utility models" },
    { id: ObjectType.PLANT_BREED, description: "Сортове и породи", descriptionEn: "Plants and breeds" },
    { id: ObjectType.SPC, description: "SPC", descriptionEn: "SPC" },
    { id: ObjectType.DESIGN, description: "Дизайни", descriptionEn: "Designs" },
  ];

  return (
    <>
      <GridItem xs={xs} sm={sm} md={md} lg={lg}>
        <CheckboxListFormField
          row={true}
          fieldName={"objectRange"}
          labelCode={"l.searchFilter.objectRange"}
          checkboxOptions={objectRange.map((option) => {
            return {
              value: option.id,
              text: iTxt(getI18n().language, option.description, option.descriptionEn),
            };
          })}
        />
      </GridItem>
    </>
  );
};
export default ObjectRangeSearchFilter;
