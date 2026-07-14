import { initializeYup } from "@duosoftbg/bpo-components";
import * as yup from "yup";

export const decisionSearchValidationSchema = () => {
  initializeYup(yup);
  return yup.object({});
};
