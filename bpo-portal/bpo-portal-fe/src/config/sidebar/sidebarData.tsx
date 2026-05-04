import { messagesBg, messagesEn } from "../../i18n";
import { isArrayEmpty, isArrayNotEmpty, ProcessEnvironments, SidebarData } from "@duosoftbg/bpo-components";
import { doesExistUrl } from "../../utils/sidebar";

export const sidebarData: SidebarData[] = [
  {
    id: "sidebar",
    title: "",
    titleEn: "",
    pages: [
      {
        id: "bpo-registers",
        href: `${ProcessEnvironments.PublicUrl}/bpo-registers`,
        title: messagesBg.translation["l.bpo.registers"],
        titleEn: messagesEn.translation["l.bpo.registers"],
      },
    ],
  },
];

function transformHomePageDataToSidebarData(homepageData) {
  const result = [];

  homepageData?.sections.forEach((item) => {
    result.push({
      id: item.id,
      href: `${ProcessEnvironments.PublicUrl}/section/${item.id}`,
      title: item.labelBg,
      titleEn: item.labelEn,
    });
  });

  return [{ ...sidebarData[0], pages: result }];
}

function transformEservicesDataToSidebarData(eServiceData) {
  const result = [];

  eServiceData?.childrenServices.forEach((item) => {
    result.push({
      id: item.id,
      href: `${ProcessEnvironments.PublicUrl}/eservices/${eServiceData.id}/service-definition/${item.id}`,
      title: item.titleBg,
      titleEn: item.titleEn,
    });
  });

  return [{ ...sidebarData[0], pages: result }];
}

function transformAdminConsoleDataToSidebarData(adminConsoleData) {
  if (isArrayEmpty(adminConsoleData.filter((item) => doesExistUrl(item)))) {
    return generateDataTreeAdminConsole(adminConsoleData);
  }
  let newTitleId = "parentData";
  let newConsoleData = [
    {
      id: newTitleId,
      title: null,
      titleEn: null,
      accessRoles: adminConsoleData[0].accessRoles,
      parentId: null,
      children: adminConsoleData.reduce((transformArray, item) => {
        transformArray.push({
          id: item.id,
          title: item.title,
          titleEn: item.titleEn,
          accessRoles: item.accessRoles,
          parentId: newTitleId,
          url: item.url,
          children: item.children,
        });
        return transformArray;
      }, []),
    },
  ];
  return generateDataTreeAdminConsole(newConsoleData);
}

function generateDataTreeAdminConsole(data) {
  return data.map((item) => ({
    id: item.id,
    title: item.title,
    titleEn: item.titleEn,
    accessRoles: item.accessRoles,
    parentId: item.parentId,
    href: isArrayEmpty(item.children) && item.url !== null && item.url !== "" ? item.url : null,
    pages: item.parentId === null && generateDataTreeAdminConsole(item.children),
    children: isArrayNotEmpty(item.children) && item.parentId !== null && generateDataTreeAdminConsole(item.children),
  }));
}

export const dynamicSidebarPath = {
  homeSidebar: { path: "/home", transformFn: transformHomePageDataToSidebarData },
  eservicesSidebar: { path: "/eservices", transformFn: transformEservicesDataToSidebarData },
  adminConsoleSidebar: { path: "/admin-console", transformFn: transformAdminConsoleDataToSidebarData },
};
