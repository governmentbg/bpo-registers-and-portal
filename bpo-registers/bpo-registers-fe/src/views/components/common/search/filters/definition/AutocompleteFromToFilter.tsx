import { SearchFilterGrid, AsyncAutocompleteFormField } from "@duosoftbg/bpo-components";
import { useTranslation } from "react-i18next";
import { useFormContext, useWatch } from "react-hook-form";
import { useEffect } from "react";

const AutocompleteFromToFilter = ({
  from,
  to,
  label,
  fetchDataFn,
  minSearchLength = 3,
  additionalParams = null,
  canFillManually = true,
}) => {
  const { t } = useTranslation();
  const { setValue } = useFormContext();
  const fromValue = useWatch({ name: from });

  useEffect(() => {
    setValue(to, fromValue);
    // eslint-disable-next-line
  }, [fromValue]);  

  return (
    <>
      <SearchFilterGrid>
        <AsyncAutocompleteFormField
          fieldName={from}
          asyncFn={fetchDataFn}
          additionalAsyncFnParams={additionalParams}
          allowManualInput={canFillManually}
          minCharsAutocomplete={minSearchLength}
          label={t(label) + " " + t("l.from")}
          isOptionEqualToValue={(option, val) => option.id === val.is}
          optionListText={(option) => option.id + (option.name ? " - " + option.name : "")}
          optionSelectedText={(option) => option.id}
          transformInputToEndType={(val) => {
            return { id: val.trim(), name: val.trim() };
          }}
        />
      </SearchFilterGrid>
      <SearchFilterGrid>
        <AsyncAutocompleteFormField
          fieldName={to}
          asyncFn={fetchDataFn}
          additionalAsyncFnParams={additionalParams}
          allowManualInput={canFillManually}
          minCharsAutocomplete={minSearchLength}
          label={t(label) + " " + t("l.to")}
          isOptionEqualToValue={(option, val) => option.id === val.is}
          optionListText={(option) => option.id + (option.name ? " - " + option.name : "")}
          optionSelectedText={(option) => option.id}
          transformInputToEndType={(val) => {
            return { id: val.trim(), name: val.trim() };
          }}
        />
      </SearchFilterGrid>
    </>
  );
};
export default AutocompleteFromToFilter;
