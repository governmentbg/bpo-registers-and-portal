import React from "react";
import { useTranslation } from "react-i18next";
import { AsyncCallArgs, TextButton, useAsyncCall } from "@duosoftbg/bpo-components";
import TableViewIcon from "@mui/icons-material/TableView";
import { getPersonObjectsReceiptExcel } from "../../../../axios/api/services";
import i18n from "i18next";

const DownloadPersonObjectsAsExcelButton = ({ personName, objectId }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();

  const handleButtonClick = async () => {
    if (personName && objectId) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: getPersonObjectsReceiptExcel(personName, objectId, i18n.language),
        withGlobalBackdrop: true,
        onSuccess: (response) => {
          let downloadLink = window.document.createElement("a");
          downloadLink.href = window.URL.createObjectURL(
            new Blob([response.data], { type: response?.headers?.["content-type"] })
          );
          downloadLink.download = "person_object_relations_receipt.xlsx";
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

export default DownloadPersonObjectsAsExcelButton;
