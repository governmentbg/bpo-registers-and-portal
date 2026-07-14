import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  modals: {
    view: {
      open: false,
      person: null,
      objectId: null,
    },
  },
};

const personRelatedObjectsControlSlice = createSlice({
  name: "personRelatedObjectsControl",
  initialState: initialState,
  reducers: {
    openModal: (state, action) => {
      const { person, objectId } = action.payload;
      state.modals.view = { open: true, person, objectId };
    },
    closeModal: (state) => {
      state.modals.view = { open: false, person: null, objectId: null };
    },
  },
});

export const PersonRelatedObjectsControlActions = personRelatedObjectsControlSlice.actions;
export default personRelatedObjectsControlSlice.reducer;
