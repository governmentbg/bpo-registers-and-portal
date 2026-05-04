import {
  AlertSpg,
  AsyncCallArgs,
  BoxSpg,
  CircularTextLoader,
  DividerSpg,
  isNotEmpty,
  useAsyncCall,
} from "@duosoftbg/bpo-components";
import React, { ReactElement, useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { RelatedObjectsDialog } from "../ipObjects/common/PersonDataPanel";

interface ViewProps {
  serviceFn: () => () => Promise<any>;
  panelsRenderer: (data: any) => ReactElement;
  summary?: (data: any) => ReactElement;
}

const View = ({ serviceFn, panelsRenderer, summary = undefined }: ViewProps) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [notFound, setNotFound] = useState(false);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: serviceFn(),
      onSuccess: (response) => {
        setData(response);
        setLoading(false);
      },
      onError: () => {
        setNotFound(true);
        setLoading(false);
      },
    };
    asyncCall(asyncCallArgs);
  }, [asyncCall, serviceFn]);

  if (notFound) {
    return <AlertSpg severity="error">{t("m.generic.error.not.found")}</AlertSpg>;
  }

  if (loading) {
    return (
      <BoxSpg>
        <DividerSpg mb={4.5} />
        <CircularTextLoader />
      </BoxSpg>
    );
  }

  if (isNotEmpty(data)) {
    return (
      <>
        <RelatedObjectsDialog />
        {summary && summary(data)}
        {panelsRenderer(data)}
      </>
    );
  }

  return null;
};

export default View;
