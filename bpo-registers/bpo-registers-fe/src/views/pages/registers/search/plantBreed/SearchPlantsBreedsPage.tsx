import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import React from "react";
import SearchPlantsBreeds from "../../../../components/registers/search/plantBreed/SearchPlantsBreeds";

const SearchPlantsBreedsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.page.plantsBreedsSearch")}>
      <SearchPlantsBreeds />
    </PageWrapper>
  );
};

export default SearchPlantsBreedsPage;
