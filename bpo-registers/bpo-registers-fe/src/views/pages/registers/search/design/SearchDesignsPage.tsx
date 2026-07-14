import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import React from "react";
import SearchDesigns from "../../../../components/registers/search/design/SearchDesigns";

const SearchDesignsPage = () => {
  const { t } = useTranslation();

  return <PageWrapper title={t("t.page.designsSearch")}>{<SearchDesigns />}</PageWrapper>;
};

export default SearchDesignsPage;
