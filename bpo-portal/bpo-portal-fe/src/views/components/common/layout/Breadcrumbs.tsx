import { AppBreadcrumbs, BreadcrumbData } from "@duosoftbg/bpo-components";
import { breadcrumbDataThunk } from "../../../../store/redux/slice/AppData/breadcrumbData";

export const breadcrumbData: BreadcrumbData = [
  { path: "/", name: "t.page.homePage", isHomePage: true },
  { path: "/section/:sectionId", name: "t.page.section", dynamicKey: "sectionId" },
  {
    path: "/eservices/:serviceGroup",
    name: "t.page.services",
    dynamicKey: "serviceGroup",
    parentOverride: [
      {
        path: "/",
        name: "t.page.homePage",
        isExternal: false,
      },
      {
        path: "/section/services",
        name: "services",
        dynamicKey: "services",
        isExternal: false,
      },
    ],
  },
  {
    path: "/eservices/:serviceGroup/service-definition/:serviceDefinitionId",
    name: "t.page.serviceDefinition",
    dynamicKey: "serviceDefinitionId",
  },
  { path: "/guides", name: "t.page.userGuides" },
  { path: "/feedback", name: "t.page.feedback" },
  { path: "/error-message", name: "t.page.ipObjectErrorMessage" },
  { path: "/admin-console", name: "t.page.adminConsole" },
];

const Breadcrumbs = () => {
  return (
    <AppBreadcrumbs
      data={[...breadcrumbData]}
      dynamicThunkFn={breadcrumbDataThunk}
      reduxStateReturn={(state) => state.AppData.breadcrumbData}
    />
  );
};

export default Breadcrumbs;
