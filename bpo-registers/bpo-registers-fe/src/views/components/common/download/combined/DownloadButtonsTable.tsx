import React from "react";
import DownloadTableAsExcelButton from "../DownloadTableAsExcelButton";
import { SEARCH_GROUP } from "../../../../../config/registers/search/registersSearchConfig";
import { format } from "date-fns";
import DownloadSearchAsPdfButton from "../DownloadSearchAsPdfButton";
import { useStore } from "react-redux";

const DownloadButtonsTable = ({ searchGroup, registerType }) => {
  const store = useStore();
  const filtersData = store.getState().SearchData.registersSearchData[searchGroup].filtersData;

  return (
    <div className={"docx-hidable"} style={{ float: "right" }}>
      <DownloadSearchAsPdfButton registerType={registerType} filterObject={filtersData} />
      <DownloadTableAsExcelButton registerType={registerType} filterObject={filtersData} />
    </div>
  );
};

export const getHeadingBySearchGroup = (searchGroup, t, appendDateTime = true) => {
  let result;
  switch (searchGroup) {
    case SEARCH_GROUP.MARK_SEARCH:
      result = t("t.page.marksSearch");
      break;
    case SEARCH_GROUP.GEO_INDICATION_SEARCH:
      result = t("t.page.geoIndicationsSearch");
      break;
    case SEARCH_GROUP.PATENT_SEARCH:
      result = t("t.page.patentsSearch");
      break;
    case SEARCH_GROUP.EU_PATENT_SEARCH:
      result = t("t.page.europeanPatentsSearch");
      break;
    case SEARCH_GROUP.UTILITY_MODEL_SEARCH:
      result = t("t.page.utilityModelsSearch");
      break;
    case SEARCH_GROUP.PLANT_BREED_SEARCH:
      result = t("t.page.plantsBreedsSearch");
      break;
    case SEARCH_GROUP.SPC_SEARCH:
      result = t("t.page.spcsSearch");
      break;
    case SEARCH_GROUP.AGENTS_SEARCH:
      result = t("t.page.agentsSearch");
      break;
    case SEARCH_GROUP.PARTNERSHIP_SEARCH:
      result = t("t.page.partnershipSearch");
      break;
    default:
      return null;
  }

  if (appendDateTime) {
    result += format(new Date(), " dd.MM.yyyy HH:mm:ss");
  }
  return result;
};

export default DownloadButtonsTable;
