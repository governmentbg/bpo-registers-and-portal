import { combineReducers } from "@reduxjs/toolkit";
import registersSearchDataReducer from "./registersSearchData";
export {};

const viewDataReducers = combineReducers({
  registersSearchData: registersSearchDataReducer,
});

export default viewDataReducers;
