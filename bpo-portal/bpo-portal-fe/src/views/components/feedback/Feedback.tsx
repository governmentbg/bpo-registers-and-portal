import {
  AsyncCallArgs,
  BoxSpg,
  CardSpg,
  ErrorMessage,
  useAsyncCall,
  useReactHookForm,
} from "@duosoftbg/bpo-components";
import { useTranslation } from "react-i18next";
import { feedbackDetailsInitialValues } from "../../../init/feedback/feedbackInitialValues";
import { FeedbackDetails } from "../../../types/feedback/feedbackTypes";
import { feedbackValidationSchema } from "../../../yup/feedbackValidationSchema";
import { saveFeedback } from "../../../axios/api/services";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { FormProvider } from "react-hook-form";
import CardContent from "@mui/material/CardContent/CardContent";
import React from "react";
import FeedbackFormFields from "./FeedbackFormFields";
import { FeedbackMessageType } from "../../../utils/constants";
import { useGoogleReCaptcha } from "react-google-recaptcha-v3";

const Feedback = () => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const navigate = useNavigate();
  const { executeRecaptcha } = useGoogleReCaptcha();

  const { methods, handleSubmit } = useReactHookForm<FeedbackDetails>({
    defaultValues: feedbackDetailsInitialValues,
    validationSchema: feedbackValidationSchema,
  });

  const onSubmit = async (values) => {
    const token = await executeRecaptcha("feedback_submit");

    const asyncCreation: AsyncCallArgs = {
      withGlobalBackdrop: true,
      processResponseErrors: true,
      promise: saveFeedback({ ...values }, token),
      reactHooksForm: { methods },
      onSuccess: () => {
        navigate("/home");
        toast.success(t("m.edit.success"));
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
              <FeedbackFormFields feedbackType={FeedbackMessageType.Feedback} />
              <ErrorMessage pointer="recaptchaValidationPointer" />
            </CardContent>
          </CardSpg>
        </form>
      </FormProvider>
    </BoxSpg>
  );
};
export default Feedback;
