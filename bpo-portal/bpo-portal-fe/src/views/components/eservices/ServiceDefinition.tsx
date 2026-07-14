import {
  AlertSpg,
  AsyncCallArgs,
  BoxSpg,
  CardSpg,
  CircularLoader,
  iTxt,
  MediumButton,
  useAsyncCall,
  useRedirect,
} from "@duosoftbg/bpo-components";
import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { getServiceDefinitionById } from "../../../axios/api/services";
import i18n from "i18next";
import { Typography } from "@mui/material";
import DOMPurify from "dompurify";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faList, faPlus } from "@fortawesome/free-solid-svg-icons";

const ServiceDefinition = ({ serviceDefinitionId, setTitle }) => {
  const { asyncCall } = useAsyncCall();
  const { t } = useTranslation();

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [content, setContent] = useState(null);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getServiceDefinitionById(serviceDefinitionId),
      onSuccess: (response) => {
        setContent(response);
        setLoading(false);
      },
      onError: () => {
        setError(true);
        setLoading(false);
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line
    }, [serviceDefinitionId]);

  if (loading) {
    return (
      <BoxSpg>
        <CircularLoader />
      </BoxSpg>
    );
  }

  if (error) {
    return (
      <BoxSpg>
        <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
      </BoxSpg>
    );
  }

  if (content) {
    setTitle(iTxt(i18n.language, content.titleBg, content.titleEn, true));
    return <ServiceDefinitionContent content={content} />;
  }

  return null;
};

const ServiceDefinitionContent = ({ content }) => {
  const description = iTxt(i18n.language, content?.descriptionBg, content?.descriptionEn, true);

  return (
    <>
      <CardSpg my={4}>
        <BoxSpg margin={4}>
          <div dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(description) }}></div>
        </BoxSpg>
      </CardSpg>
      <ServiceDefinitionActions actionNew={content?.actionNew} actionView={content?.actionView} />
    </>
  );
};

const ServiceDefinitionActions = ({ actionNew, actionView }) => {
  const { t } = useTranslation();
  const { redirect } = useRedirect();

  return (
    <BoxSpg mt={4}>
      <Typography>
        <MediumButton
          startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faPlus} />}
          variant="contained"
          color="primary"
          onClick={() => redirect(actionNew)}
        >
          {t("l.btn.newRequest")}
        </MediumButton>
        <MediumButton
          ml={2}
          startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faList} />}
          variant="outlined"
          color="primary"
          onClick={() => redirect(actionView)}
        >
          {t("l.btn.myApplications")}
        </MediumButton>
      </Typography>
    </BoxSpg>
  );
};

export default ServiceDefinition;
