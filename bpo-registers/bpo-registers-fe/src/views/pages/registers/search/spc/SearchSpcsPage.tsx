import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import React from "react";
import SearchSpcs from "../../../../components/registers/search/spc/SearchSpcs";

const SearchSpcsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.page.spcsSearch")}>
      <SearchSpcs />
    </PageWrapper>
  );
};

export default SearchSpcsPage;
