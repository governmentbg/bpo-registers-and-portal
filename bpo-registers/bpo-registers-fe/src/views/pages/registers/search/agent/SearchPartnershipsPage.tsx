import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import React from "react";
import SearchPartnerships from "../../../../components/registers/search/agent/SearchPartnerships";

const SearchPartnershipsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.page.partnershipSearch")}>
      <SearchPartnerships />
    </PageWrapper>
  );
};

export default SearchPartnershipsPage;
