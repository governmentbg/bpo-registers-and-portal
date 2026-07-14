import React from "react";
import { useTranslation } from "react-i18next";
import { TextButton } from "@duosoftbg/bpo-components";
import LibraryBooks from "@mui/icons-material/LibraryBooks";
import { format } from "date-fns";
import { generateDocx } from "../../../../utils/docx/docxGenerator";
import { convertViewJsonToDocxJsonByRegisterType } from "../../../../utils/docx/objectSectionsConfig";

const DownloadViewAsDocxButton = ({ data, registerType, componentRef, objectId }) => {
  const { t } = useTranslation();

  if (!data || data.length === 0) {
    return null;
  }

  return (
    <TextButton
      disableRipple
      startIcon={<LibraryBooks />}
      color="primary"
      onClick={() =>
        generateDocx(
          convertViewJsonToDocxJsonByRegisterType(registerType, data, t, componentRef),
          "view-" + registerType + "_" + format(new Date(), "yyyy_MM_dd_HH_mm_ss"),
          objectId,
          t(`l.title.docx.${registerType}`),
          t
        )
      }
    >
      {t("l.btn.saveResultDocx")}
    </TextButton>
  );
};

export default DownloadViewAsDocxButton;
