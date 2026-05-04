import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  modals: {
    view: {
      open: false,
      person: null,
    },
  },
};

const agentAddressesControlSlice = createSlice({
  name: "agentAddressesControl",
  initialState: initialState,
  reducers: {
    openModal: (state, action) => {
      const { person } = action.payload;
      state.modals.view = { open: true, person };
    },
    closeModal: (state) => {
      state.modals.view = { open: false, person: null };
    },
  },
});

export const AgentAddressesControlActions = agentAddressesControlSlice.actions;
export default agentAddressesControlSlice.reducer;
