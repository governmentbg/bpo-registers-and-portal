import { combineReducers } from "@reduxjs/toolkit";
import markKinds from "./markKinds";
import countries from "./countries";
import ipoAreas from "./ipoAreas";
import agentStatuses from "./agentStatuses";
import representativeTypes from "./representativeTypes";
import objectTypes from "./objectTypes";
import legalDecisionTypes from "./legalDecisionTypes";
import legalDecisionGroundTypes from "./legalDecisionGroundTypes";
import panelsDataReducer from "./panelsData";

const appDataReducers = combineReducers({
  objectTypes: objectTypes,
  legalDecisionTypes: legalDecisionTypes,
  legalDecisionGroundTypes: legalDecisionGroundTypes,
  markKinds: markKinds,
  countries: countries,
  ipoAreas: ipoAreas,
  agentStatuses: agentStatuses,
  representativeTypes: representativeTypes,
  panelsData: panelsDataReducer,
});

export default appDataReducers;
