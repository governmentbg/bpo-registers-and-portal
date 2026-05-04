import { initializeYup } from "@duosoftbg/bpo-components";
import * as yup from "yup";
import { object } from "yup";
import i18n from "i18next";

export const feedbackValidationSchema = () => {
  initializeYup(yup);
  return yup.object({
    firstName: yup.string().required(),
    lastName: yup.string().required(),
    description: yup.string().required(),
    email: yup.string().email(i18n.t("validation.field.invalid.email")).required(),
    messageType: yup.string().required(),
    attachments: yup
      .array()
      .optional()
      .of(
        yup.object().shape({
          fileStoreEntry: object().shape({
            fullPath: yup.string().required(),
          }),
        })
      ),
  });
};
