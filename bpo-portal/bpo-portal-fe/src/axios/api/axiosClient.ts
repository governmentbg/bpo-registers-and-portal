import { createAxiosClient, keycloakInitObject } from "@duosoftbg/bpo-components";

export const axiosClient = createAxiosClient({
  baseUrl: `${process.env.REACT_APP_BPO_PORTAL_API_URL}/api/v1`,
  keycloak: keycloakInitObject,
});
