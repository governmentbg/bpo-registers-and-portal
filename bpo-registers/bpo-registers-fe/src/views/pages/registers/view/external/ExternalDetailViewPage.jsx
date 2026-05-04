import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import ExternalDetailView from "../../../../components/registers/view/external/ExternalDetailView";

const ExternalDetailViewPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper helmetTitle={t("t.page.externalDetailView")}>
      <ExternalDetailView />
    </PageWrapper>
  );
};
export default ExternalDetailViewPage;
