import { useTranslation } from "react-i18next";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import ViewGeoIndication from "../../../../components/registers/view/ipObjects/markLike/geoIndication/ViewGeoIndication";

const ViewGeoIndicationPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper helmetTitle={t("t.page.geoIndicationsView")}>
      <ViewGeoIndication />
    </PageWrapper>
  );
};

export default ViewGeoIndicationPage;
