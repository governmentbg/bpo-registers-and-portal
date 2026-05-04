import { useFormContext, useWatch } from "react-hook-form";
import { AsyncDataFetcher, iTxt, SelectFormField } from "@duosoftbg/bpo-components";
import { getObjectPublicationSections } from "../../../../../../axios/api/services";
import { useTranslation } from "react-i18next";
import { useEffect } from "react";

const PublicationSectionField = () => {
  const { i18n } = useTranslation();
  const { setValue } = useFormContext();

  const year = useWatch({ name: "publications.publicationYear" });
  const number = useWatch({ name: "publications.publicationNumber" });
  const dateFrom = useWatch({ name: "publications.publicationDate.from" });
  const dateTo = useWatch({ name: "publications.publicationDate.to" });
  const objectRange = useWatch({ name: "objectRange" });

  const publicationSectionField = "publications.publicationSection";
  const hideField = (!year || year === 0) && (!dateFrom || dateFrom === "") && (!dateTo || dateTo === "");

  useEffect(() => {
    if (hideField) {
      setValue(publicationSectionField, "");
    }
    // eslint-disable-next-line
    }, [year, dateFrom, dateTo, number]);


  if (hideField) {
    return null;
  }
  return (
    <AsyncDataFetcher
      asyncFn={getObjectPublicationSections}
      initialData={[]}
      loadInitial={true}
      reloadOnComponentEvent={false}
      initialArgs={{ year, number, objectTypes: objectRange }}
      renderComponent={(asyncState) => (
        <SelectFormField
          fieldName={publicationSectionField}
          labelCode={"l.searchFilter.publicationSection"}
          selectOptions={asyncState.data.map((sec) => {
            return { value: sec.id, text: iTxt(i18n.language, sec.nmsection, sec.nmsectionEn) };
          })}
          addEmptyOption={true}
        />
      )}
    />
  );
};
export default PublicationSectionField;
