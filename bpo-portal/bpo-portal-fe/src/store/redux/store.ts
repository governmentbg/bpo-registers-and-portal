import { configureStore } from "@reduxjs/toolkit";
import { defaultReduxReducers } from "@duosoftbg/bpo-components";
import appDataReducers from "./slice/AppData";

export const store = configureStore({
  reducer: {
    AppData: appDataReducers,
    ...defaultReduxReducers,
  },
  devTools: process.env.REACT_APP_PROFILE !== "production",
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
