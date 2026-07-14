import { useTranslation } from "react-i18next";
import { AsyncCallArgs, TextButton, useAsyncCall } from "@duosoftbg/bpo-components";
import PictureAsPdfIcon from "@mui/icons-material/PictureAsPdf";
import { getIpObjectViewReceiptPdf } from "../../../../axios/api/services";
import i18n from "i18next";

const DownloadViewAsPdfButton = ({ registerType, objectId }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();

  const handleButtonClick = async () => {
    if (objectId && registerType) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: getIpObjectViewReceiptPdf(registerType, objectId.replaceAll("/", "_"), i18n.language),
        withGlobalBackdrop: true,
        onSuccess: (response) => {
          let downloadLink = window.document.createElement("a");
          downloadLink.href = window.URL.createObjectURL(
            new Blob([response.data], { type: response?.headers?.["content-type"] })
          );
          downloadLink.download = "view_" + registerType + "_" + objectId.replaceAll("/", "_");
          document.body.appendChild(downloadLink);
          downloadLink.click();
          document.body.removeChild(downloadLink);
        },
      };
      asyncCall(asyncCallArgs);
    }
  };

  return (
    <TextButton disableRipple startIcon={<PictureAsPdfIcon />} color="primary" onClick={handleButtonClick}>
      {t("l.btn.saveResultPdf")}
    </TextButton>
  );
};
export default DownloadViewAsPdfButton;
