import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import React from "react";
import SearchPatents from "../../../../components/registers/search/patent/SearchPatents";

const SearchPatentsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.page.patentsSearch")}>
      <SearchPatents />
    </PageWrapper>
  );
};

export default SearchPatentsPage;
