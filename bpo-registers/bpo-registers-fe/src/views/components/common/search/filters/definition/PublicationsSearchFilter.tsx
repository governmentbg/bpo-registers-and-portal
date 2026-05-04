import { DateFromToFilter, GridItem, RadiosFormField, YearFormField } from "@duosoftbg/bpo-components";
import PublicationNumberField from "../fields/PublicationNumberField";
import PublicationSectionField from "../fields/PublicationSectionField";
import { useFormContext, useWatch } from "react-hook-form";
import { PublicationSearchType } from "../../../../../../types/registers/search/filter/filterTypes";
import { commonIpObjectFiltersInitialValues } from "../../../../../../init/registers/search/filter/commonInitialValues";
import { useTranslation } from "react-i18next";
import React from "react";

const PublicationsSearchFilter = () => {
  const { t } = useTranslation();
  const { setValue, getValues } = useFormContext();

  const handleTypeSelect = () => {
    if (getValues("publications.publicationSearchType") === PublicationSearchType.JOURNAL_NBR) {
      setValue("publications.publicationDate", commonIpObjectFiltersInitialValues.publications.publicationDate);
    } else {
      setValue("publications.publicationYear", commonIpObjectFiltersInitialValues.publications.publicationYear);
      setValue("publications.publicationNumber", commonIpObjectFiltersInitialValues.publications.publicationNumber);
    }
    setValue("publications.publicationSection", commonIpObjectFiltersInitialValues.publications.publicationSection);
  };

  return (
    <>
      <GridItem sm={12} md={12} lg={12}>
        <RadiosFormField
          isInline={true}
          labelCode={"l.searchFilter.publicationSearchType"}
          fieldName={"publications.publicationSearchType"}
          radioOptions={Object.values(PublicationSearchType).map((type) => {
            return { value: type.valueOf(), text: t("l.searchFilter.publicationSearchType." + type.valueOf()) };
          })}
          onChange={handleTypeSelect}
        />
      </GridItem>
      <JournalNbrFilter />
      <PublicationDateFilter />
    </>
  );
};

const JournalNbrFilter = () => {
  const searchType = useWatch({ name: "publications.publicationSearchType" });

  if (PublicationSearchType.JOURNAL_NBR !== searchType) {
    return null;
  }

  return (
    <>
      <GridItem sm={4} md={4} lg={4}>
        <YearFormField fieldName={"publications.publicationYear"} labelCode={"l.searchFilter.publicationYear"} />
      </GridItem>
      <GridItem sm={4} md={4} lg={4}>
        <PublicationNumberField />
      </GridItem>
      <GridItem sm={4} md={4} lg={4}>
        <PublicationSectionField />
      </GridItem>
    </>
  );
};

const PublicationDateFilter = () => {
  const searchType = useWatch({ name: "publications.publicationSearchType" });

  if (PublicationSearchType.PUBLICATION_DATE !== searchType) {
    return null;
  }

  return (
    <>
      <DateFromToFilter
        sm={4}
        md={4}
        lg={4}
        label={"l.searchFilter.publicationDate"}
        from={`publications.publicationDate.from`}
        to={`publications.publicationDate.to`}
      />
      <GridItem sm={4} md={4} lg={4}>
        <PublicationSectionField />
      </GridItem>
    </>
  );
};
export default PublicationsSearchFilter;
