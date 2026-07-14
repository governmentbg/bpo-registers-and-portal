import { GridItem, iTxt, SelectFormField, useAppDispatch } from "@duosoftbg/bpo-components";
import { useEffect } from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { ipoAreasThunk } from "../../../../../../store/redux/slice/AppData/ipoAreas";
import { useTranslation } from "react-i18next";

const IpoAreaSearchFilter = () => {
  const { i18n } = useTranslation();

  const ipoAreas = useAppSelector((state) => {
    return state.AppData.ipoAreas;
  });
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(ipoAreasThunk());
  }, [dispatch]);

  if (!ipoAreas || !ipoAreas.data) {
    return null;
  }
  return (
    <>
      <GridItem sm={6} md={6} lg={6}>
        <SelectFormField
          fieldName={"ipoArea"}
          labelCode={"l.searchFilter.ipoArea"}
          selectOptions={ipoAreas.data.map((area) => {
            return { value: area.id, text: iTxt(i18n.language, area.name, area.nameEn) };
          })}
          addEmptyOption={true}
        />
      </GridItem>
    </>
  );
};
export default IpoAreaSearchFilter;
