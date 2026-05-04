import { axiosClient } from "./axiosClient";
import qs from "qs";
import { ApiEndpoints } from "./endpoints";

const commonFilterFunction = async (url, params, withIndices = true) => {
  const response = await axiosClient.get(url, {
    params,
    paramsSerializer: (params) => {
      return qs.stringify(params, { indices: withIndices });
    },
  });
  return response.data;
};

export const getServiceDefinitionsByGroup = (serviceGroup) => async () => {
  const response = await axiosClient.get(
    ApiEndpoints.panels.services.byServiceGroup.replace("{serviceGroup}", serviceGroup)
  );

  return response.data;
};

export const getServiceDefinitionById = (serviceDefinitionId) => async () => {
  const response = await axiosClient.get(
    ApiEndpoints.panels.services.serviceDefinitions.byId.replace("{serviceDefinitionId}", serviceDefinitionId)
  );

  return response.data;
};

export const getHomePageData = () => async () => {
  const response = await axiosClient.get(ApiEndpoints.homeData);

  return response.data;
};

export const getDynamicPortalMessage = () => async () => {
  const response = await axiosClient.get(ApiEndpoints.dynamicPortalMessage);

  return response.data;
};

export const saveFeedback = (feedback, recaptchaToken) => async () => {
  const response = await axiosClient.post(ApiEndpoints.feedback, {
    feedback: feedback,
    recaptchaToken: recaptchaToken,
  });
  return response.data;
};

export const getAdminConsoleData = () => async () => {
  const response = await axiosClient.get(ApiEndpoints.admin.adminConsole);

  return response.data;
};

export const getDynamicBreadcrumbData = () => async () => {
  const response = await axiosClient.get(ApiEndpoints.breadcrumb);

  return response.data;
};

export const getUserGuidesData = () => async () => {
  const response = await axiosClient.get(ApiEndpoints.guides.getAll);

  return response.data;
};

export const getUserGuidesFileDataById = (userGuideId) => async () => {
  const response = await axiosClient.get(ApiEndpoints.guides.getFileById.replace("{fileId}", userGuideId), {
    responseType: "blob",
  });
  return response;
};
