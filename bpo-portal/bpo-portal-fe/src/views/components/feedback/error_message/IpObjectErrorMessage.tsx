import { FormProvider } from "react-hook-form";
import {
  AsyncCallArgs,
  BoxSpg,
  CardSpg,
  ErrorMessage,
  useAsyncCall,
  useReactHookForm,
} from "@duosoftbg/bpo-components";
import CardContent from "@mui/material/CardContent/CardContent";
import FeedbackFormFields from "../FeedbackFormFields";
import React, { useEffect } from "react";
import { useSearchParams } from "react-router-dom";
import { FeedbackDetails } from "../../../../types/feedback/feedbackTypes";
import { errorMessageInitialValues } from "../../../../init/error_message/errorMessageInitialValues";
import { feedbackValidationSchema } from "../../../../yup/feedbackValidationSchema";
import { saveFeedback } from "../../../../axios/api/services";
import IpObjectReadonlySection from "./IpObjectReadonlySection";
import { FeedbackMessageType, IpDetailPageUrlByType } from "../../../../utils/constants";
import { useRedirect } from "@duosoftbg/bpo-components";
import { useGoogleReCaptcha } from "react-google-recaptcha-v3";

const IpObjectErrorMessage = () => {
  const { asyncCall } = useAsyncCall();
  const [searchParams] = useSearchParams();
  const { redirect } = useRedirect();
  const { executeRecaptcha } = useGoogleReCaptcha();

  const { methods, handleSubmit } = useReactHookForm<FeedbackDetails>({
    defaultValues: errorMessageInitialValues,
    validationSchema: feedbackValidationSchema,
  });

  const objectType = searchParams.get("objectType");
  const applicationNumber = searchParams.get("applicationNumber");

  const detailPageUrl = `${process.env.REACT_APP_BPO_REGISTERS_URL}${
    IpDetailPageUrlByType[objectType]
  }${applicationNumber.replaceAll("/", "_")}?&sentMessage=true`;

  useEffect(() => {
    methods.setValue("objectType", objectType);
    methods.setValue("applicationNumber", applicationNumber);
  }, [methods, objectType, applicationNumber]);

  const onSubmit = async (values) => {
    const token = await executeRecaptcha("error_message_submit");

    const asyncCreation: AsyncCallArgs = {
      withGlobalBackdrop: true,
      processResponseErrors: true,
      promise: saveFeedback({ ...values }, token),
      reactHooksForm: { methods },
      onSuccess: () => {
        redirect(detailPageUrl);
      },
    };
    asyncCall(asyncCreation);
  };

  return (
    <BoxSpg>
      <FormProvider {...methods}>
        <form onSubmit={handleSubmit(onSubmit)}>
          <CardSpg my={4} style={{ overflow: "visible" }}>
            <CardContent style={{ position: "relative" }}>
              <IpObjectReadonlySection />
              <FeedbackFormFields feedbackType={FeedbackMessageType.IpObjectError} />
              <ErrorMessage pointer="recaptchaValidationPointer" />
            </CardContent>
          </CardSpg>
        </form>
      </FormProvider>
    </BoxSpg>
  );
};

export default IpObjectErrorMessage;
