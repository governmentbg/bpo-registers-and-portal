import { GridItem, SelectFormField, ThunkStateDataFetcher, iTxt } from "@duosoftbg/bpo-components";
import { markKindsThunk } from "../../../../../../store/redux/slice/AppData/markKinds";
import { useTranslation } from "react-i18next";

const MarkKindSearchFilter = () => {
  const { i18n } = useTranslation();

  return (
    <>
      <GridItem sm={6} md={6} lg={6}>
        <ThunkStateDataFetcher
          thunkFn={markKindsThunk}
          reduxStateReturn={(state) => state.AppData.markKinds}
          renderComponent={(thunkState) => (
            <SelectFormField
              fieldName={"markKind.id"}
              labelCode={"l.markKind"}
              selectOptions={thunkState.data.map((kind) => {
                return { value: kind.id, text: iTxt(i18n.language, kind.description, kind.descriptionEn) };
              })}
              addEmptyOption={true}
            />
          )}
        />
      </GridItem>
    </>
  );
};
export default MarkKindSearchFilter;
