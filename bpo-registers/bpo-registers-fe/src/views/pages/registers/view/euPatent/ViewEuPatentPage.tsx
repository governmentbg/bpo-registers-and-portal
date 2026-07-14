import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import ViewEuPatent from "../../../../components/registers/view/ipObjects/patentLike/euPatent/ViewEuPatent";

const ViewEuPatentPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper helmetTitle={t("t.page.euPatentsView")}>
      <ViewEuPatent />
    </PageWrapper>
  );
};

export default ViewEuPatentPage;
