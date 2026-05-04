import { isArrayEmpty } from "@duosoftbg/bpo-components";

export const doesExistUrl = (item) => {
  return isArrayEmpty(item.children) && item.url !== null && item.url !== "";
};
