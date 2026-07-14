import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import ViewAgent from "../../../../components/registers/view/representatives/agent/ViewAgent";

const ViewAgentPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper helmetTitle={t("t.page.agentView")}>
      <ViewAgent />
    </PageWrapper>
  );
};

export default ViewAgentPage;
