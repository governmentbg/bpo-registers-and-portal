import { useRedirect } from "@duosoftbg/bpo-components";
import { useTranslation } from "react-i18next";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBell } from "@fortawesome/free-solid-svg-icons";
import { Button } from "@mui/material";
import React from "react";

const ErrorReportButton = ({ objectType, applicationNumber }) => {
  const { t } = useTranslation();
  const { redirect } = useRedirect();
  return (
    <Button
      className={"docx-hidable"}
      variant="text"
      component="span"
      startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faBell} />}
      onClick={() => redirect(urlComposition(objectType, applicationNumber))}
    >
      {t("l.btn.error.report")}
    </Button>
  );
};

const urlComposition = (objectType, applicationNumber) => {
  return baseUrl.concat("?objectType=", objectType, "&applicationNumber=", applicationNumber);
};

const baseUrl = `${process.env.REACT_APP_BPO_PORTAL_URL}/error-message`;

export default ErrorReportButton;
