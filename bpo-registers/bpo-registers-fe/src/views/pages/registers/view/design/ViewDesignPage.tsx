import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import ViewDesign from "../../../../components/registers/view/ipObjects/patentLike/design/ViewDesign";

const ViewDesignPage = () => {
  const { t } = useTranslation();

  return <PageWrapper helmetTitle={t("t.page.designsView")}>{<ViewDesign />}</PageWrapper>;
};

export default ViewDesignPage;
