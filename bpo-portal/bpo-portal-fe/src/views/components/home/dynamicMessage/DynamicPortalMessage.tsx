import { AsyncCallArgs, GridContainer, isNotEmpty, useAsyncCall } from "@duosoftbg/bpo-components";
import React, { useEffect, useState } from "react";
import { getDynamicPortalMessage } from "../../../../axios/api/services";
import MessageCardContent from "../MessageCardContent";
import { useTranslation } from "react-i18next";
import AnnouncementOutlinedIcon from "@mui/icons-material/AnnouncementOutlined";
import { isObjectEmpty } from "../../../../utils/object";

type HomePageMessageType = {
  descriptionBg: string;
  descriptionEn: string;
};

const DynamicPortalMessage = (homePageMessage: HomePageMessageType) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const [dynamicMessage, setDynamicMessage] = useState(null);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getDynamicPortalMessage(),
      onSuccess: (response) => {
        setDynamicMessage(response);
      },
      onError: () => {
        setDynamicMessage(null);
      },
    };
    asyncCall(asyncCallArgs);
    // eslint-disable-next-line
    }, []);

  return (
    <GridContainer spacing={2}>
      {dynamicMessage && (
        <MessageCardContent
          img={AnnouncementOutlinedIcon}
          title={t("l.dynamicInformation.homePage")}
          descriptionBg={dynamicMessage.descriptionBg}
          descriptionEn={dynamicMessage.descriptionEn}
        />
      )}
      {!isObjectEmpty(homePageMessage) && <MessageCardContent {...homePageMessage} />}
    </GridContainer>
  );
};

export default DynamicPortalMessage;
