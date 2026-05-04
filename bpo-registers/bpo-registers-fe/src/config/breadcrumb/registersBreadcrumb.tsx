import { BreadcrumbData, InitialBreadcrumb, iTxt } from "@duosoftbg/bpo-components";
import i18n from "i18next";

export const staticBreadcrumbData: BreadcrumbData = [
  { path: "/", name: "t.page.home", isHomePage: true },
  { path: "/marks", name: "t.page.marksSearch" },
  { path: "/marks/view", name: "t.page.marksView" },
  { path: "/combined-search/marks/view", name: "t.page.marksView" },
  { path: "/patents", name: "t.page.patentsSearch" },
  { path: "/patents/view", name: "t.page.patentsView" },
  { path: "/combined-search/patents/view", name: "t.page.patentsView" },
  { path: "/designs", name: "t.page.designsSearch" },
  { path: "/designs/view", name: "t.page.designsView" },
  { path: "/combined-search/designs/view", name: "t.page.designsView" },
  { path: "/eu-patents", name: "t.page.europeanPatentsSearch" },
  { path: "/eu-patents/view", name: "t.page.euPatentsView" },
  { path: "/combined-search/eu-patents/view", name: "t.page.euPatentsView" },
  { path: "/topologies", name: "t.page.topologySearch" },
  { path: "/spc", name: "t.page.spcsSearch" },
  { path: "/spc/view", name: "t.page.spcsView" },
  { path: "/combined-search/spc/view", name: "t.page.spcsView" },
  { path: "/utility-models", name: "t.page.utilityModelsSearch" },
  { path: "/utility-models/view", name: "t.page.utilityModelsView" },
  { path: "/combined-search/utility-models/view", name: "t.page.utilityModelsView" },
  { path: "/plants-breeds", name: "t.page.plantsBreedsSearch" },
  { path: "/plants-breeds/view", name: "t.page.plantsBreedsView" },
  { path: "/combined-search/plants-breeds/view", name: "t.page.plantsBreedsView" },
  { path: "/geo-indications", name: "t.page.geoIndicationsSearch" },
  { path: "/geo-indications/view", name: "t.page.geoIndicationsView" },
  { path: "/combined-search/geo-indications/view", name: "t.page.geoIndicationsView" },
  { path: "/combined-search", name: "t.page.combinedSearch" },
  { path: "/agents", name: "t.page.agentsSearch" },
  { path: "/partnerships", name: "t.page.partnershipSearch" },
  { path: "/agents/view", name: "t.page.agentView", queryParamSuffix: "id" },
  { path: "/partnerships", name: "t.page.partnershipSearch" },
  { path: "/partnerships/view", name: "t.page.partnershipView", queryParamSuffix: "id" },
  { path: "/legal-decisions", name: "t.page.decisionsSearch" },
  { path: "/legal-decisions/view", name: "t.page.decisionsView" },
  { path: "/en/rd", name: "t.page.externalDetailView" },
];

export const dynamicBreadcrumbData = (panel: any): BreadcrumbData => {
  let array: BreadcrumbData = [];
  panel.forEach((item: any) => {
    array.push({
      path: String(item.action).substring(String(item.action).lastIndexOf("/")),
      name: iTxt(i18n.language, item.titleBg, item.titleEn),
    });
    array.push({
      path: String(item.action).substring(String(item.action).lastIndexOf("/")) + "/view",
      name: iTxt(i18n.language, "Преглед на " + item.titleBg, item.titleEn + " view"),
    });
    array.push({
      path: "/combined-search" + String(item.action).substring(String(item.action).lastIndexOf("/")) + "/view",
      name: iTxt(i18n.language, "Преглед на " + item.titleBg, item.titleEn + " view"),
    });
  });
  return array;
};

export const initialBreadcrumbData = (data: any): InitialBreadcrumb => [
  { path: `${process.env.REACT_APP_BPO_PORTAL_URL}`, name: "t.page.home", external: true },
  {
    path: `${process.env.REACT_APP_BPO_PORTAL_URL}/section/register`,
    name: iTxt(i18n.language, data.labelBg || "t.page.registers", data.labelEn || "t.page.registers"),
    external: true,
  },
];
