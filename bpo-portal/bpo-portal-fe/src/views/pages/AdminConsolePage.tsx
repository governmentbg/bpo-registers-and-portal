import { useTranslation } from "react-i18next";
import PageWrapper from "../components/common/layout/PageWrapper";
import React from "react";
import AdminConsole from "../components/admin/AdminConsole";

const AdminConsolePage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.page.adminConsole")}>
      <AdminConsole />
    </PageWrapper>
  );
};
export default AdminConsolePage;
