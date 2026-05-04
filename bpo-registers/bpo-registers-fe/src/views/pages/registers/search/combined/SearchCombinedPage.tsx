import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import React from "react";
import SearchCombined from "../../../../components/registers/search/combined/SearchCombined";

const SearchCombinedPage = () => {
  const { t } = useTranslation();

  return <PageWrapper title={t("t.page.combinedSearch")}>{<SearchCombined />}</PageWrapper>;
};

export default SearchCombinedPage;
