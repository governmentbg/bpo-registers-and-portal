import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  commonThunkCondition,
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
} from "@duosoftbg/bpo-components";
import { getLegalDecisionGroundTypes } from "../../../../axios/api/services";

const sliceName = "appData/legalDecisionGroundTypes";
const legalDecisionGroundTypesSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(legalDecisionGroundTypesThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(legalDecisionGroundTypesThunk.fulfilled, (state, action) => {
      const data = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(legalDecisionGroundTypesThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const legalDecisionGroundTypesThunk = createAsyncThunk(
  `${sliceName}/${sliceName}Thunk`,
  async () => {
    const response = await getLegalDecisionGroundTypes()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.legalDecisionGroundTypes);
    },
  }
);

export default legalDecisionGroundTypesSlice.reducer;
