import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import React from "react";
import SearchAgents from "../../../../components/registers/search/agent/SearchAgents";

const SearchAgentsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.page.agentsSearch")}>
      <SearchAgents />
    </PageWrapper>
  );
};

export default SearchAgentsPage;
