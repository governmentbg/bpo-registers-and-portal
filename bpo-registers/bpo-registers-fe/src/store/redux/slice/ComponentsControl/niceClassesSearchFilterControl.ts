import { createSlice } from "@reduxjs/toolkit";
export {};

const initialState = {
  modals: {
    editList: {
      open: false,
      niceClassCodes: null,
      onlyGoods: false,
    },
  },
};

const niceClassesSearchFilterControlSlice = createSlice({
  name: "niceClassesSearchFilterControl",
  initialState: initialState,
  reducers: {
    openEditListModal: (state, action) => {
      const { niceClassCodes, onlyGoods } = action.payload;
      state.modals.editList = { open: true, niceClassCodes, onlyGoods };
    },
    closeEditListModal: (state, action) => {
      state.modals.editList = { open: false, niceClassCodes: null, onlyGoods: false };
    },
  },
});

export const NiceClassesSearchFilterControlActions = niceClassesSearchFilterControlSlice.actions;
export default niceClassesSearchFilterControlSlice.reducer;
