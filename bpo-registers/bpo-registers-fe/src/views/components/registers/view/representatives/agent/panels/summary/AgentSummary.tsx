import useAppDispatch from "../../../../../../../../hooks/redux/base/useAppDispatch";
import { getI18n, useTranslation } from "react-i18next";
import { BoxSpg, CardSpg, DividerSpg, iTxt } from "@duosoftbg/bpo-components";
import { Button, CardContent, Typography } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMaximize, faMinimize } from "@fortawesome/free-solid-svg-icons";
import { GlobalPanelsControlActions } from "../../../../../../../../store/redux/slice/ComponentsControl/globalPanelsControl";
import React from "react";
import DownloadButtonsView from "../../../../../../common/download/combined/DownloadButtonsView";
import { RegisterType } from "../../../../../../../../utils/constants";

const AgentSummary = ({ data, componentToDownloadRef = null }) => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation();

  const agent = data?.agent;

  return (
    <BoxSpg>
      <CardSpg style={{ minHeight: 50 }} mb={5}>
        <CardContent style={{ padding: 24 }}>
          <Typography align={"center"} fontSize={18}>
            {t("l.agent")}: {iTxt(getI18n().language, agent.name, agent.agent?.nameEn)} - {agent.agent?.agentCode}
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
          <DownloadButtonsView
            data={data}
            registerType={RegisterType.AGENT}
            componentRef={componentToDownloadRef}
            objectId={data?.agent?.agent?.agentCode}
          />
        </CardContent>
      </CardSpg>
    </BoxSpg>
  );
};
export default AgentSummary;
