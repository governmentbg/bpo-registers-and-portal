import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import React from "react";
import SearchTopology from "../../../../components/registers/search/topology/SearchTopology";

const SearchTopologyPage = () => {
  const { t } = useTranslation();
  return (
    <PageWrapper title={t("t.page.topologySearch")}>
      <SearchTopology />
    </PageWrapper>
  );
};

export default SearchTopologyPage;
