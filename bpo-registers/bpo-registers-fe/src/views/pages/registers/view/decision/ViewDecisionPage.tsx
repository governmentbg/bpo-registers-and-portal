import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import ViewDecision from "../../../../components/registers/view/decision/ViewDecision";

const ViewDecisionPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper helmetTitle={t("t.page.decisionsView")}>
      <ViewDecision />
    </PageWrapper>
  );
};

export default ViewDecisionPage;
