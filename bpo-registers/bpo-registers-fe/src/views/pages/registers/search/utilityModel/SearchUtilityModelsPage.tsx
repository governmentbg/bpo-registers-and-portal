import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import React from "react";
import SearchUtilityModels from "../../../../components/registers/search/utilityModel/SearchUtilityModels";

const SearchUtilityModelsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.page.utilityModelsSearch")}>
      <SearchUtilityModels />
    </PageWrapper>
  );
};

export default SearchUtilityModelsPage;
