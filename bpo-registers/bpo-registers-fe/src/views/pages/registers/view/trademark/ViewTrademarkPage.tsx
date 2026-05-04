import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import ViewTrademark from "../../../../components/registers/view/ipObjects/markLike/trademark/ViewTrademark";

const ViewTrademarkPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper helmetTitle={t("t.page.marksView")}>
      <ViewTrademark />
    </PageWrapper>
  );
};

export default ViewTrademarkPage;
