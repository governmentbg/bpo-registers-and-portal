import { RegisterType } from "./constants";

const urlsConfig = {
  [RegisterType.PATENT]: {
    view: `/patents/view/{objectId}`,
  },
  [RegisterType.EU_PATENT]: {
    view: `/eu-patents/view/{objectId}`,
  },
  [RegisterType.UTILITY_MODEL]: {
    view: `/utility-models/view/{objectId}`,
  },
  [RegisterType.SPC]: {
    view: `/spc/view/{objectId}`,
  },
  [RegisterType.PLANT_BREED]: {
    view: `/plants-breeds/view/{objectId}`,
  },
  [RegisterType.MARK]: {
    view: `/marks/view/{objectId}`,
  },
  [RegisterType.GEO_INDICATION]: {
    view: `/geo-indications/view/{objectId}`,
  },
  [RegisterType.DESIGN]: {
    view: `/designs/view/{objectId}`,
  },
  [RegisterType.DECISIONS]: {
    view: `/legal-decisions/view/{objectId}`,
  },
  [RegisterType.AGENT]: {
    view: `/agents/view/{objectId}`,
  },
  [RegisterType.PARTNERSHIP]: {
    view: `/partnerships/view/{objectId}`,
  },
};

export const generateUrl = (registerType, objectId, accessType, appendPrefix = false, isCombinedSearch = false) => {
  return (
    (appendPrefix ? "/bpo-registers" : "") +
    (isCombinedSearch ? "/combined-search" : "") +
    urlsConfig[registerType][accessType].replace("{objectId}", objectId)
  );
};

export const openInNewTab = (url: string): void => {
  const newWindow = window.open(url, "_blank", "noopener,noreferrer");
  if (newWindow) newWindow.opener = null;
};
