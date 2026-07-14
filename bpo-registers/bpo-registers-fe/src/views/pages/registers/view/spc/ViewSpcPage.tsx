import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import ViewSpc from "../../../../components/registers/view/ipObjects/patentLike/spc/ViewSpc";

const ViewSpcPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper helmetTitle={t("t.page.spcsView")}>
      <ViewSpc />
    </PageWrapper>
  );
};

export default ViewSpcPage;
