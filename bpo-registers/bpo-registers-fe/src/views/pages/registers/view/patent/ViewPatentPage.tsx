import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import ViewPatent from "../../../../components/registers/view/ipObjects/patentLike/patent/ViewPatent";

const ViewPatentPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper helmetTitle={t("t.page.patentsView")}>
      <ViewPatent />
    </PageWrapper>
  );
};

export default ViewPatentPage;
