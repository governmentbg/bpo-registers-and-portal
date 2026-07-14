import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import React from "react";
import SearchEuPatents from "../../../../components/registers/search/euPatents/SearchEuPatents";

const SearchEuPatentsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.page.europeanPatentsSearch")}>
      <SearchEuPatents />
    </PageWrapper>
  );
};

export default SearchEuPatentsPage;
