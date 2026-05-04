import { useTranslation } from "react-i18next";
import { BoxSpg, CardSpg, DividerSpg } from "@duosoftbg/bpo-components";
import { Button, CardContent, Typography } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMaximize, faMinimize } from "@fortawesome/free-solid-svg-icons";
import React, { useEffect } from "react";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import { GlobalPanelsControlActions } from "../../../../../../store/redux/slice/ComponentsControl/globalPanelsControl";
import { format } from "date-fns";
import DownloadButtonsView from "../../../../common/download/combined/DownloadButtonsView";
import ErrorReportButton from "../../common/ErrorReportButton";
import SuccessfullyErrorReport from "../../../../common/alerts/SuccessfullyErrorReport";

const IpObjectSummary = ({ data, registerType, componentToDownloadRef = null }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(GlobalPanelsControlActions.openAll());
  }, [data, dispatch]);

  return (
    <BoxSpg>
      <SuccessfullyErrorReport text={t("m.errorMessage.successReport")} />
      <CardSpg style={{ minHeight: 50 }} mb={5}>
        <CardContent style={{ padding: 24 }}>
          <Typography align={"center"} fontSize={18}>
            {t(`l.application.${registerType}`) + " №" + data?.ipObject?.id?.split("/")[3]}
          </Typography>
          <Typography align={"center"} fontSize={18}>
            {t("l.reference.to") + " "}
            {format(new Date(), "dd.MM.yyyy HH:mm")}
          </Typography>
          <DividerSpg my={2} />
          <Button
            className={"docx-hidable"}
            variant="text"
            component="span"
            startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faMaximize} />}
            onClick={() => dispatch(GlobalPanelsControlActions.openAll())}
          >
            {t("l.btn.expand.all")}
          </Button>
          <Button
            className={"docx-hidable"}
            variant="text"
            component="span"
            startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faMinimize} />}
            onClick={() => dispatch(GlobalPanelsControlActions.closeAll())}
          >
            {t("l.btn.collapse.all")}
          </Button>
          <ErrorReportButton objectType={data?.ipObject?.objectType?.id} applicationNumber={data?.id} />
          <DownloadButtonsView
            data={data}
            registerType={registerType}
            componentRef={componentToDownloadRef}
            objectId={data?.id}
          />
        </CardContent>
      </CardSpg>
    </BoxSpg>
  );
};
export default IpObjectSummary;
