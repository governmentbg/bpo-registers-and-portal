import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  modals: {
    view: {
      open: false,
      historyRecord: null,
      viewType: null,
    },
  },
};

const representativeHistoryControlSlice = createSlice({
  name: "representativeHistoryControl",
  initialState: initialState,
  reducers: {
    openViewHistoryModal: (state, action) => {
      const { historyRecord, viewType } = action.payload;
      state.modals.view = { open: true, historyRecord, viewType };
    },
    closeViewHistoryModal: (state) => {
      state.modals.view = { open: false, historyRecord: null, viewType: null };
    },
  },
});

export const RepresentativeHistoryControlActions = representativeHistoryControlSlice.actions;
export default representativeHistoryControlSlice.reducer;
