const ApiEndpoints = {
  homeData: "/home-page-data",
  dynamicPortalMessage: "/portal-texts/dynamic",
  breadcrumb: "/breadcrumbs",
  panels: {
    services: {
      byServiceGroup: "/panels/services/{serviceGroup}",
      serviceDefinitions: {
        byId: "/service-definitions/{serviceDefinitionId}",
      },
    },
  },
  feedback: "/feedback",
  admin: {
    adminConsole: "/admin-panel-data",
  },
  guides: {
    getAll: "/portal-attachments",
    getFileById: "/portal-attachments/file/{fileId}",
  },
};

export { ApiEndpoints };
