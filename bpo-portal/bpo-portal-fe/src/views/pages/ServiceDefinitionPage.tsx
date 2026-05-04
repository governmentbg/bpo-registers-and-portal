import { CardSpg } from "@duosoftbg/bpo-components";
import CardContent from "@mui/material/CardContent";
import { useState } from "react";
import { useTranslation } from "react-i18next";
import { useParams } from "react-router-dom";
import PageWrapper from "../components/common/layout/PageWrapper";
import ServiceDefinition from "../components/eservices/ServiceDefinition";

const ServiceDefinitionPage = () => {
  useTranslation();
  const params = useParams();

  const [title, setTitle] = useState("");

  return (
    <PageWrapper title={title}>
      <CardSpg my={4} style={{ overflow: "visible" }}>
        <CardContent style={{ position: "relative" }}>
          <ServiceDefinition serviceDefinitionId={params?.serviceDefinitionId} setTitle={setTitle} />
        </CardContent>
      </CardSpg>
    </PageWrapper>
  );
};
export default ServiceDefinitionPage;
