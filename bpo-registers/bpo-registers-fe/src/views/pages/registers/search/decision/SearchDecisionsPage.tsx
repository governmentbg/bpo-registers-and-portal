import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import React from "react";
import SearchDecisions from "../../../../components/registers/search/decision/SearchDecisions";

const SearchDecisionsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.page.decisionsSearch")}>
      <SearchDecisions />
    </PageWrapper>
  );
};

export default SearchDecisionsPage;
