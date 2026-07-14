import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import ViewUtilityModel from "../../../../components/registers/view/ipObjects/patentLike/utilityModel/ViewUtilityModel";

const ViewUtilityModelPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper helmetTitle={t("t.page.utilityModelsView")}>
      <ViewUtilityModel />
    </PageWrapper>
  );
};

export default ViewUtilityModelPage;
