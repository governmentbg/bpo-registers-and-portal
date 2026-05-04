import React from "react";
import { Page403, Page404, SecurityGuard, SecurityRole } from "@duosoftbg/bpo-components";
import PortalLayout from "../views/layouts/PortalLayout";
import HomePage from "../views/pages/HomePage";
import { Navigate } from "react-router-dom";
import ServiceGroupPage from "../views/pages/ServiceGroupPage";
import ServiceDefinitionPage from "../views/pages/ServiceDefinitionPage";
import HomeSectionPage from "../views/pages/HomeSectionPage";
import FeedbackPage from "../views/pages/FeedbackPage";
import IpObjectErrorMessagePage from "../views/pages/IpObjectErrorMessagePage";
import AdminConsolePage from "../views/pages/AdminConsolePage";
import UserGuidesPage from "../views/pages/UserGuidesPage";

const routes = [
  {
    path: "/",
    element: <PortalLayout hideSubHeader />,
    children: [
      { index: true, element: <Navigate to="/home" replace /> },
      { path: "/home", element: <HomePage /> },
    ],
  },
  {
    path: "/",
    element: <PortalLayout />,
    children: [
      { path: "/section/:sectionId", element: <HomeSectionPage /> },
      {
        path: "/eservices/:serviceGroup",
        element: <ServiceGroupPage />,
      },
      {
        path: "/eservices/:serviceGroup/service-definition/:serviceDefinitionId",
        element: <ServiceDefinitionPage />,
      },
      { path: "/feedback", element: <FeedbackPage /> },
      { path: "/error-message", element: <IpObjectErrorMessagePage /> },
      {
        path: "/admin-console",
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.AdminConsoleAccess]}>
            <AdminConsolePage />
          </SecurityGuard>
        ),
      },
      {
        path: "/guides",
        element: <UserGuidesPage />,
      },
    ],
  },
  {
    path: "*",
    element: <PortalLayout />,
    children: [
      {
        path: "*",
        element: <Page404 />,
      },
    ],
  },
];

export default routes;
