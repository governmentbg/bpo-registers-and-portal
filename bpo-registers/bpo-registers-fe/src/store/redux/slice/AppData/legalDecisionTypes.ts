import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  commonThunkCondition,
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
} from "@duosoftbg/bpo-components";
import { getLegalDecisionTypes } from "../../../../axios/api/services";

const sliceName = "appData/legalDecisionTypes";
const legalDecisionTypesSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(legalDecisionTypesThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(legalDecisionTypesThunk.fulfilled, (state, action) => {
      const data = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(legalDecisionTypesThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const legalDecisionTypesThunk = createAsyncThunk(
  `${sliceName}/${sliceName}Thunk`,
  async () => {
    const response = await getLegalDecisionTypes()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.legalDecisionTypes);
    },
  }
);

export default legalDecisionTypesSlice.reducer;
