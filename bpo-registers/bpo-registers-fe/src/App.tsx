import { Helmet, HelmetProvider } from "react-helmet-async";
import routes from "./routes/routes";
import { useRoutes } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { AppThemeProvider, ToastProvider } from "@duosoftbg/bpo-components";
import React from "react";

const App = () => {
  const content = useRoutes(routes);
  const { t } = useTranslation();

  return (
    <HelmetProvider>
      <Helmet
        defaultTitle={t("t.app.defaultTitle")}
        titleTemplate={t("t.app.titleTemplate")}
        title={t("t.page.registers")}
      />
      <AppThemeProvider>
        <ToastProvider />
        {content}
      </AppThemeProvider>
    </HelmetProvider>
  );
};

export default App;
