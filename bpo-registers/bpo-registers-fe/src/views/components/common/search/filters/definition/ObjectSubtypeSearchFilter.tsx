import { GridItem, useAsyncCall, AlertSpg, isNotEmpty, CheckboxListFormField, iTxt } from "@duosoftbg/bpo-components";
import { useFormContext } from "react-hook-form";
import { useEffect, useState } from "react";
import { getI18n, useTranslation } from "react-i18next";
import { getObjectSubtypesByObjectRange } from "../../../../../../axios/api/services";
import { UNIVERSAL_FILTERS } from "../../../../../../config/registers/search/filters/definition/slice/universalFilters";

type ObjectSubtypeSearchFilterProps = {
  labelCode?: string;
  xs?: number;
  sm?: number;
  md?: number;
  lg?: number;
};

const ObjectSubtypeSearchFilter = ({
  labelCode = UNIVERSAL_FILTERS.objectSubtype.label,
  xs = 12,
  sm = 12,
  md = 12,
  lg = 12,
}: ObjectSubtypeSearchFilterProps) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const { getValues } = useFormContext();

  const [error, setError] = useState(false);
  const [loading, setLoading] = useState(true);
  const [objectSubtypes, setObjectSubtypes] = useState(null);

  useEffect(() => {
    asyncCall({
      promise: getObjectSubtypesByObjectRange(getValues("objectRange")),
      onSuccess: (response) => {
        setObjectSubtypes(response);
        setLoading(false);
        setError(false);
      },
      onError: () => {
        setError(true);
        setLoading(false);
      },
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  if (error) {
    return (
      <GridItem xs={xs} sm={sm} md={md} lg={lg}>
        <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
      </GridItem>
    );
  }

  if (!loading && isNotEmpty(objectSubtypes)) {
    return (
      <>
        <GridItem xs={xs} sm={sm} md={md} lg={lg}>
          <CheckboxListFormField
            row={true}
            fieldName={"objectSubtype"}
            labelCode={labelCode}
            checkboxOptions={objectSubtypes.map((option) => {
              return {
                value: option.id,
                text: iTxt(getI18n().language, option.description, option.descriptionEn),
              };
            })}
          />
        </GridItem>
      </>
    );
  }

  return null;
};
export default ObjectSubtypeSearchFilter;
