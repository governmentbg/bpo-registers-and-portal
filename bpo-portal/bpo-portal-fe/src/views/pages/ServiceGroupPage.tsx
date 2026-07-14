import { useTranslation } from "react-i18next";
import { useParams } from "react-router-dom";
import PageWrapper from "../components/common/layout/PageWrapper";
import ServiceGroup from "../components/eservices/ServiceGroup";

const ServiceGroupPage = () => {
  const params = useParams();
  const { t } = useTranslation();

  return (
    <PageWrapper title={""} helmetTitle={t("t.page.services")}>
      <ServiceGroup serviceGroup={params?.serviceGroup} />
    </PageWrapper>
  );
};
export default ServiceGroupPage;
