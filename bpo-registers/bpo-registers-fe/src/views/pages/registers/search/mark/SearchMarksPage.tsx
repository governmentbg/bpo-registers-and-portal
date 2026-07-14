import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import React from "react";
import SearchMarks from "../../../../components/registers/search/mark/SearchMarks";

const SearchMarksPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.page.marksSearch")}>
      <SearchMarks />
    </PageWrapper>
  );
};

export default SearchMarksPage;
