import { initializeYup } from "@duosoftbg/bpo-components";
import * as yup from "yup";

export const markSearchValidationSchema = () => {
  initializeYup(yup);
  return yup.object({});
};

export const geoIndicationSearchValidationSchema = () => {
  initializeYup(yup);
  return yup.object({});
};
