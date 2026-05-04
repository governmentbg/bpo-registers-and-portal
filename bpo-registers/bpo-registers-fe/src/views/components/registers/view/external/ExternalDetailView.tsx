import { AlertSpg, AsyncCallArgs, BoxSpg, CircularLoader, isNotEmpty, useAsyncCall } from "@duosoftbg/bpo-components";
import { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useSearchParams, useNavigate, useParams } from "react-router-dom";
import { getObjectIdByAlternateKey } from "../../../../../axios/api/services";
import { generateUrl } from "../../../../../utils/urls";
import { getRegisterTypeByObjectType } from "../../../../../utils/constants";
import i18n from "i18next";

function getExternalKey(key) {
  if (key && key.includes("-")) {
    return key.substring(0, key.indexOf("-"));
  }

  return key;
}

const ExternalDetailView = () => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const navigate = useNavigate();

  const params = useParams();
  const language = params?.language;

  let [searchParams] = useSearchParams();

  const key = searchParams.get("key");

  const [object, setObject] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getObjectIdByAlternateKey(getExternalKey(key)),
      onSuccess: (response) => {
        setObject(response);
        setLoading(false);
      },
      onError: () => {
        setObject(null);
        setError(true);
        setLoading(false);
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line
  }, [key]);

  useEffect(() => {
    if (isNotEmpty(object)) {
      i18n
        .changeLanguage(language)
        .then(() =>
          navigate(generateUrl(getRegisterTypeByObjectType(object.objectType), object.id.replaceAll("/", "_"), "view"))
        );
    }
  }, [navigate, object, language]);

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

  return null;
};

export default ExternalDetailView;
