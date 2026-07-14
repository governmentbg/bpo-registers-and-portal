import React from "react";
import { Page404 } from "@duosoftbg/bpo-components";
import RegistersLayout from "../views/layouts/RegistersLayout";
import SearchMarksPage from "../views/pages/registers/search/mark/SearchMarksPage";
import SearchGeoIndicationsPage from "../views/pages/registers/search/geoIndication/SearchGeoIndicationsPage";
import SearchPatentsPage from "../views/pages/registers/search/patent/SearchPatentsPage";
import SearchUtilityModelsPage from "../views/pages/registers/search/utilityModel/SearchUtilityModelsPage";
import SearchPlantsBreedsPage from "../views/pages/registers/search/plantBreed/SearchPlantsBreedsPage";
import SearchSpcsPage from "../views/pages/registers/search/spc/SearchSpcsPage";
import SearchTopologyPage from "../views/pages/registers/search/topology/SearchTopologyPage";
import SearchEuPatentsPage from "../views/pages/registers/search/euPatent/SearchEuPatentsPage";
import SearchAgentsPage from "../views/pages/registers/search/agent/SearchAgentsPage";
import ViewPartnershipPage from "../views/pages/registers/view/partnership/ViewPartnershipPage";
import ViewAgentPage from "../views/pages/registers/view/agent/ViewAgentPage";
import ViewPatentPage from "../views/pages/registers/view/patent/ViewPatentPage";
import ViewEuPatentPage from "../views/pages/registers/view/euPatent/ViewEuPatentPage";
import ViewSpcPage from "../views/pages/registers/view/spc/ViewSpcPage";
import ViewUtilityModelPage from "../views/pages/registers/view/utilityModel/ViewUtilityModelPage";
import ViewPlantBreedPage from "../views/pages/registers/view/plantBreed/ViewPlantBreedPage";
import SearchPartnershipsPage from "../views/pages/registers/search/agent/SearchPartnershipsPage";
import ViewTrademarkPage from "../views/pages/registers/view/trademark/ViewTrademarkPage";
import ViewGeoIndicationPage from "../views/pages/registers/view/geoIndication/ViewGeoIndicationPage";
import SearchDesignsPage from "../views/pages/registers/search/design/SearchDesignsPage";
import ViewDesignPage from "../views/pages/registers/view/design/ViewDesignPage";
import SearchCombinedPage from "../views/pages/registers/search/combined/SearchCombinedPage";
import SearchDecisionsPage from "../views/pages/registers/search/decision/SearchDecisionsPage";
import ViewDecisionPage from "../views/pages/registers/view/decision/ViewDecisionPage";
import ExternalDetailViewPage from "../views/pages/registers/view/external/ExternalDetailViewPage";

const routes = [
  {
    path: "/",
    element: <RegistersLayout />,
    children: [
      {
        path: "marks",
        element: <SearchMarksPage />,
      },
      {
        path: "marks/view/:id",
        element: <ViewTrademarkPage />,
      },
      {
        path: "combined-search/marks/view/:id",
        element: <ViewTrademarkPage />,
      },
      {
        path: "patents",
        element: <SearchPatentsPage />,
      },
      {
        path: "patents/view/:id",
        element: <ViewPatentPage />,
      },
      {
        path: "combined-search/patents/view/:id",
        element: <ViewPatentPage />,
      },
      {
        path: "designs",
        element: <SearchDesignsPage />,
      },
      {
        path: "designs/view/:id",
        element: <ViewDesignPage />,
      },
      {
        path: "combined-search/designs/view/:id",
        element: <ViewDesignPage />,
      },
      {
        path: "agents",
        element: <SearchAgentsPage />,
      },
      {
        path: "partnerships",
        element: <SearchPartnershipsPage />,
      },
      {
        path: "eu-patents",
        element: <SearchEuPatentsPage />,
      },
      {
        path: "eu-patents/view/:id",
        element: <ViewEuPatentPage />,
      },
      {
        path: "combined-search/eu-patents/view/:id",
        element: <ViewEuPatentPage />,
      },
      {
        path: "spc",
        element: <SearchSpcsPage />,
      },
      {
        path: "topologies",
        element: <SearchTopologyPage />,
      },
      {
        path: "spc/view/:id",
        element: <ViewSpcPage />,
      },
      {
        path: "combined-search/spc/view/:id",
        element: <ViewSpcPage />,
      },
      {
        path: "utility-models",
        element: <SearchUtilityModelsPage />,
      },
      {
        path: "utility-models/view/:id",
        element: <ViewUtilityModelPage />,
      },
      {
        path: "combined-search/utility-models/view/:id",
        element: <ViewUtilityModelPage />,
      },
      {
        path: "plants-breeds",
        element: <SearchPlantsBreedsPage />,
      },
      {
        path: "plants-breeds/view/:id",
        element: <ViewPlantBreedPage />,
      },
      {
        path: "combined-search/plants-breeds/view/:id",
        element: <ViewPlantBreedPage />,
      },
      {
        path: "geo-indications",
        element: <SearchGeoIndicationsPage />,
      },
      {
        path: "geo-indications/view/:id",
        element: <ViewGeoIndicationPage />,
      },
      {
        path: "combined-search/geo-indications/view/:id",
        element: <ViewGeoIndicationPage />,
      },
      {
        path: "agents/view/:id",
        element: <ViewAgentPage />,
      },
      {
        path: "partnerships/view/:id",
        element: <ViewPartnershipPage />,
      },
      {
        path: "legal-decisions",
        element: <SearchDecisionsPage />,
      },
      {
        path: "legal-decisions/view/:id",
        element: <ViewDecisionPage />,
      },
      {
        path: "combined-search",
        element: <SearchCombinedPage />,
      },
      {
        path: "/:language/rd",
        element: <ExternalDetailViewPage />,
      },
    ],
  },
  {
    path: "*",
    element: <RegistersLayout />,
    children: [
      {
        path: "*",
        element: <Page404 />,
      },
    ],
  },
];

export default routes;
