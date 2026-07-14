import React from "react";
import { useTranslation } from "react-i18next";
import { AsyncCallArgs, TextButton, useAsyncCall } from "@duosoftbg/bpo-components";
import TableViewIcon from "@mui/icons-material/TableView";
import { getIpObjectSearchReceiptExcel } from "../../../../axios/api/services";
import i18n from "i18next";

const DownloadTableAsExcelButton = ({ registerType, filterObject }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();

  const handleButtonClick = async () => {
    if (filterObject && registerType) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: getIpObjectSearchReceiptExcel(registerType, filterObject, i18n.language),
        withGlobalBackdrop: true,
        onSuccess: (response) => {
          let downloadLink = window.document.createElement("a");
          downloadLink.href = window.URL.createObjectURL(
            new Blob([response.data], { type: response?.headers?.["content-type"] })
          );
          downloadLink.download = "search_" + registerType + ".xlsx";
          document.body.appendChild(downloadLink);
          downloadLink.click();
          document.body.removeChild(downloadLink);
        },
      };
      asyncCall(asyncCallArgs);
    }
  };

  return (
    <TextButton disableRipple startIcon={<TableViewIcon />} color="primary" onClick={handleButtonClick}>
      {t("l.btn.saveResultExcel")}
    </TextButton>
  );
};

export default DownloadTableAsExcelButton;
