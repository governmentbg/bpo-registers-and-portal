import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import ViewPlantBreed from "../../../../components/registers/view/ipObjects/patentLike/plantBreed/ViewPlantBreed";

const ViewPlantBreedPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper helmetTitle={t("t.page.plantsBreedsView")}>
      <ViewPlantBreed />
    </PageWrapper>
  );
};

export default ViewPlantBreedPage;
