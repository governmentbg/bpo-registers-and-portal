import { createAxiosClient } from "@duosoftbg/bpo-components";

export const axiosClientRegisters = createAxiosClient({
  baseUrl: `${process.env.REACT_APP_REGISTERS_API_URL}/api/v1`,
});

export const axiosClientCore = createAxiosClient({
  baseUrl: `${process.env.REACT_APP_PUBLIC_CORE_API_URL}/api/v1`,
});

export const axiosClientPortal = createAxiosClient({
  baseUrl: `${process.env.REACT_APP_BPO_PORTAL_API_URL}/api/v1`,
});
