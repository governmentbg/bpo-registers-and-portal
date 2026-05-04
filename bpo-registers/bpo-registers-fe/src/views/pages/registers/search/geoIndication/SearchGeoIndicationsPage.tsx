import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import React from "react";
import SearchGeoIndications from "../../../../components/registers/search/geoIndication/SearchGeoIndications";

const SearchGeoIndicationsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.page.geoIndicationsSearch")}>
      <SearchGeoIndications />
    </PageWrapper>
  );
};

export default SearchGeoIndicationsPage;
