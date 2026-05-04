import { combineReducers } from "@reduxjs/toolkit";
import niceClassesSearchFilterControlReducer from "./niceClassesSearchFilterControl";
import globalPanelsControlReducer from "./globalPanelsControl";
import representativeHistoryControlReducer from "./representativeHistoryControl";
import agentAddressesControlReducer from "./agentAddressesControl";
import personRelatedObjectsControlReducer from "./personRelatedObjectsControl";

const componentsControlReducers = combineReducers({
  niceClassesSearchFilterControl: niceClassesSearchFilterControlReducer,
  globalPanelsControl: globalPanelsControlReducer,
  representativeHistoryControl: representativeHistoryControlReducer,
  agentAddressesControl: agentAddressesControlReducer,
  personRelatedObjectsControl: personRelatedObjectsControlReducer,
});

export default componentsControlReducers;
