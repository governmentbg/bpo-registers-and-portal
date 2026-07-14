import { useFormContext, useWatch } from "react-hook-form";
import { SelectFormField, AsyncDataFetcher } from "@duosoftbg/bpo-components";
import { getObjectPublicationNumbers } from "../../../../../../axios/api/services";
import { useEffect } from "react";

const PublicationNumberField = () => {
  const { setValue } = useFormContext();

  const year = useWatch({ name: "publications.publicationYear" });
  const objectRange = useWatch({ name: "objectRange" });

  const publicationNumberField = "publications.publicationNumber";
  const hideField = !year || year === 0;

  useEffect(() => {
    if (hideField) {
      setValue(publicationNumberField, "");
    }
    // eslint-disable-next-line
  }, [year]);

  if (hideField) {
    return null;
  }
  return (
    <AsyncDataFetcher
      asyncFn={getObjectPublicationNumbers}
      initialData={[]}
      loadInitial={true}
      reloadOnComponentEvent={false}
      initialArgs={{ year, objectTypes: objectRange }}
      renderComponent={(asyncState) => (
        <SelectFormField
          fieldName={publicationNumberField}
          labelCode={"l.searchFilter.publicationNumber"}
          selectOptions={asyncState.data.map((num) => {
            return { value: num, text: num };
          })}
          addEmptyOption={true}
        />
      )}
    />
  );
};
export default PublicationNumberField;
