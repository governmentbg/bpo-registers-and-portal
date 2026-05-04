import React, { useEffect, useState } from "react";
import { AlertSpg, AsyncCallArgs, BoxSpg, CardSpg, CircularLoader, useAsyncCall } from "@duosoftbg/bpo-components";
import ViewPanelsRendererBody from "./ViewPanelsRendererBody";
import { useTranslation } from "react-i18next";
import CardContent from "@mui/material/CardContent/CardContent";
import Typography from "@mui/material/Typography";

const AsyncViewPanelsWrapper = ({ definitionToComponent, data }) => {
  const { asyncCall } = useAsyncCall();
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const { t } = useTranslation();

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: definitionToComponent.loadPanelNotifier.notifierAsyncFunc(data)(),
      onSuccess: (response) => {
        data[definitionToComponent.loadPanelNotifier.notifierFieldName] = response;
        setLoading(false);
      },
      onError: () => {
        setLoading(false);
        setError(true);
      },
    };
    asyncCall(asyncCallArgs);
    // eslint-disable-next-line
  }, [asyncCall, definitionToComponent.id]);

  if (error) {
    return (
      <BoxSpg>
        <CardSpg style={{ minHeight: 50 }} mb={5}>
          <CardContent style={{ padding: 24 }}>
            <Typography gutterBottom variant="h4" component="h4" color="#8a8a8a">
              {t(definitionToComponent.label)}
            </Typography>
            <AlertSpg marginTop={5} severity="error">
              {t("m.error.serverFetchingError")}
            </AlertSpg>
          </CardContent>
        </CardSpg>
      </BoxSpg>
    );
  }
  if (loading) {
    return (
      <BoxSpg>
        <CardSpg style={{ minHeight: 50 }} mb={5}>
          <CardContent style={{ padding: 24 }}>
            <Typography gutterBottom variant="h4" component="h4" color="#8a8a8a">
              {t(definitionToComponent.label)}
            </Typography>
            <CircularLoader />
          </CardContent>
        </CardSpg>
      </BoxSpg>
    );
  }

  return <ViewPanelsRendererBody data={data} definitionToComponent={definitionToComponent} />;
};

export default AsyncViewPanelsWrapper;
