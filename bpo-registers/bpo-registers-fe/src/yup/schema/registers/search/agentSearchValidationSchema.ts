import { initializeYup } from "@duosoftbg/bpo-components";
import * as yup from "yup";

export const agentSearchValidationSchema = () => {
  initializeYup(yup);
  return yup.object({});
};

export const partnershipSearchValidationSchema = () => {
  initializeYup(yup);
  return yup.object({});
};
