import PageWrapper from "../components/common/layout/PageWrapper";
import React from "react";
import { useTranslation } from "react-i18next";
import IpObjectErrorMessage from "../components/feedback/error_message/IpObjectErrorMessage";
import { GoogleReCaptchaProvider } from "react-google-recaptcha-v3";

const IpObjectErrorMessagePage = () => {
  const { t } = useTranslation();
  return (
    <PageWrapper title={t("t.page.ipObjectErrorMessage")}>
      <GoogleReCaptchaProvider reCaptchaKey={`${process.env.REACT_APP_RECAPTCHA_SITE_KEY}`}>
        <IpObjectErrorMessage />
      </GoogleReCaptchaProvider>
    </PageWrapper>
  );
};
export default IpObjectErrorMessagePage;
