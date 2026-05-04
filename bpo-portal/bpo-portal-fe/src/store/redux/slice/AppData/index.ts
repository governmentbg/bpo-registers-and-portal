import { combineReducers } from "@reduxjs/toolkit";
import homePageDataReducer from "./homePageData";
import eServicesDataReducer from "./eservicesData";
import adminConsoleDataReducer from "./adminConsoleData";
import breadcrumbDataReducer from "./breadcrumbData";
import userGuidesDataReducer from "./userGuidesData";

const appDataReducers = combineReducers({
  homePageData: homePageDataReducer,
  eServicesData: eServicesDataReducer,
  adminConsoleData: adminConsoleDataReducer,
  breadcrumbData: breadcrumbDataReducer,
  userGuidesData: userGuidesDataReducer,
});

export default appDataReducers;
