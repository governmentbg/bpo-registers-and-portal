import React from "react";
import PageWrapper from "../components/common/layout/PageWrapper";
import { useTranslation } from "react-i18next";
import UserGuides from "../components/userGuides/UserGuides";

const UserGuidesPage = () => {
  const { t } = useTranslation();
  return (
    <PageWrapper title={""} helmetTitle={t("t.page.userGuides")}>
      <UserGuides />
    </PageWrapper>
  );
};

export default UserGuidesPage;
