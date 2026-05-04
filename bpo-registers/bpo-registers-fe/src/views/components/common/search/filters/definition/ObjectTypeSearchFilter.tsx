import { GridItem, iTxt, SelectFormField, useAppDispatch } from "@duosoftbg/bpo-components";
import { useEffect } from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { useTranslation } from "react-i18next";
import { objectTypesThunk } from "../../../../../../store/redux/slice/AppData/objectTypes";

const ObjectTypeSearchFilter = () => {
  const { i18n } = useTranslation();

  const objectTypes = useAppSelector((state) => {
    return state.AppData.objectTypes;
  });
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(objectTypesThunk());
  }, [dispatch]);

  if (!objectTypes || !objectTypes.data) {
    return null;
  }
  return (
    <GridItem sm={6} md={6} lg={6}>
      <SelectFormField
        fieldName={"objectType"}
        labelCode={"l.searchFilter.objectType"}
        selectOptions={objectTypes.data.map((x) => {
          return { value: x.id, text: iTxt(i18n.language, x.description, x.descriptionEn) };
        })}
        addEmptyOption={true}
      />
    </GridItem>
  );
};
export default ObjectTypeSearchFilter;
