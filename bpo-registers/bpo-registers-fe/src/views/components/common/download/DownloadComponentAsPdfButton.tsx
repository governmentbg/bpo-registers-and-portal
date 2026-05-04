import { useTranslation } from "react-i18next";
import { saveComponentAsPdf, TextButton } from "@duosoftbg/bpo-components";
import PictureAsPdfIcon from "@mui/icons-material/PictureAsPdf";

const DownloadComponentAsPdfButton = ({
  componentToDownloadRef,
  fileName,
  beforeScreenShot = undefined,
  waitBeforeScreenshot = 0,
  heading = null,
}) => {
  const { t } = useTranslation();

  const handleButtonClick = async () => {
    if (beforeScreenShot) {
      beforeScreenShot();
      await new Promise((f) => setTimeout(f, waitBeforeScreenshot));
    }
    saveComponentAsPdf(componentToDownloadRef, fileName, true, true, heading);
  };

  return (
    <TextButton disableRipple startIcon={<PictureAsPdfIcon />} color="primary" onClick={handleButtonClick}>
      {t("l.btn.saveResultPdf")}
    </TextButton>
  );
};
export default DownloadComponentAsPdfButton;
