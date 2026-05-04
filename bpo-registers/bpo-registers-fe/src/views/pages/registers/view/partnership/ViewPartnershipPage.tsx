import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import ViewPartnership from "../../../../components/registers/view/representatives/partnership/ViewPartnership";

const ViewPartnershipPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper helmetTitle={t("t.page.partnershipView")}>
      <ViewPartnership />
    </PageWrapper>
  );
};

export default ViewPartnershipPage;
