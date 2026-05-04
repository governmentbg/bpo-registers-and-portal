import PageWrapper from "../components/common/layout/PageWrapper";
import React from "react";
import Feedback from "../components/feedback/Feedback";
import { useTranslation } from "react-i18next";
import { GoogleReCaptchaProvider } from "react-google-recaptcha-v3";
import { AlertSpg } from "@duosoftbg/bpo-components";
import { Typography } from "@mui/material";

const FeedbackPage = () => {
  const { t } = useTranslation();
  return (
    <PageWrapper title={t("t.page.feedback")}>
      <GoogleReCaptchaProvider reCaptchaKey={`${process.env.REACT_APP_RECAPTCHA_SITE_KEY}`}>
        <AlertSpg severity={"info"} sx={{ textAlign: "justify" }}>
          <Typography component={"span"}>{t("m.feedback.general.info")}</Typography>
          <Typography component={"span"} fontWeight={"bold"}>
            {t("m.feedback.general.info.warn")}
          </Typography>
        </AlertSpg>
        <Feedback />
      </GoogleReCaptchaProvider>
    </PageWrapper>
  );
};
export default FeedbackPage;
