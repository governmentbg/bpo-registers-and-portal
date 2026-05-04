import { createSlice } from "@reduxjs/toolkit";
import { GlobalPanelControl } from "../../../../utils/constants";

const initialState = {
  open: GlobalPanelControl.OPEN,
};

const globalPanelsControlSlice = createSlice({
  name: "globalPanelsControl",
  initialState: initialState,
  reducers: {
    openAll: (state) => {
      state.open = GlobalPanelControl.OPEN;
    },
    closeAll: (state) => {
      state.open = GlobalPanelControl.CLOSED;
    },
    manual: (state) => {
      state.open = GlobalPanelControl.MANUAL;
    },
  },
});

export const GlobalPanelsControlActions = globalPanelsControlSlice.actions;
export default globalPanelsControlSlice.reducer;
