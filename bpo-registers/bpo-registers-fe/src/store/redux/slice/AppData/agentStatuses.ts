import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/bpo-components";
import { getAgentStatuses } from "../../../../axios/api/services";

const sliceName = "appData/agentStatuses";
const agentStatusesSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(agentStatusesThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(agentStatusesThunk.fulfilled, (state, action) => {
      const data = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(agentStatusesThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const agentStatusesThunk = createAsyncThunk(
  `${sliceName}/${sliceName}Thunk`,
  async () => {
    const response = await getAgentStatuses()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.agentStatuses);
    },
  }
);

export default agentStatusesSlice.reducer;
