import { useTranslation } from "react-i18next";
import {
  AddRecordButton,
  BoxSpg,
  GridItem,
  isNotEmpty,
  RadiosFormField,
  useExternalFormField,
} from "@duosoftbg/bpo-components";
import { SearchOperatorType } from "../../../../../../types/registers/search/filter/filterTypes";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import { NiceClassesSearchFilterControlActions } from "../../../../../../store/redux/slice/ComponentsControl/niceClassesSearchFilterControl";
import NiceClassSearchFilterListDialog from "./dialog/NiceClassSearchFilterListDialog";
import React from "react";
import { Chip } from "@mui/material";
import NameSearchWithTypeFilter from "./NameSearchWithTypeFilter";

const NiceClassSearchFilter = ({ onlyGoods = false, xs = 12, sm = 12, md = 12, lg = 12 }) => {
  const { t } = useTranslation();

  return (
    <>
      <GridItem xs={xs} sm={sm} md={md} lg={lg}>
        <NiceClassReadonlyFormField onlyGoods={onlyGoods} />
        <RadiosFormField
          isInline={true}
          fieldName={"niceClasses.niceClassCodeType"}
          labelCode={""}
          radioOptions={Object.keys(SearchOperatorType).map((key) => {
            return { value: key.valueOf(), text: t(`l.search.operator.type.${key.valueOf()}`) };
          })}
        />
      </GridItem>
      <NameSearchWithTypeFilter
        nameField={"niceClasses.specification.text"}
        nameFieldLabelCode={"l.searchFilter.niceClasses.specification"}
        searchTypeField={"niceClasses.specification.searchType"}
        xs={xs}
        sm={sm}
        md={md}
        lg={lg}
      />
      <NiceClassSearchFilterListDialog />
    </>
  );
};

const NiceClassReadonlyFormField = ({ onlyGoods }) => {
  const dispatch = useAppDispatch();
  const niceClassCodes = useExternalFormField({ key: "niceClassCodesListKey", pointer: "niceClasses.niceClassCodes" });

  const handleEditList = () => {
    dispatch(NiceClassesSearchFilterControlActions.openEditListModal({ niceClassCodes, onlyGoods }));
  };

  return (
    <>
      <AddRecordButton labelCode={"l.select.nice.classes.btn"} onClick={handleEditList} />
      {isNotEmpty(niceClassCodes) && (
        <>
          {niceClassCodes.map((niceClass) => (
            <BoxSpg component={"span"} marginLeft={1} key={niceClass}>
              <Chip label={niceClass} />
            </BoxSpg>
          ))}
        </>
      )}
    </>
  );
};
export default NiceClassSearchFilter;
