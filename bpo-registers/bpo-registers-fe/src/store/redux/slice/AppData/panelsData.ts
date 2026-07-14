import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/bpo-components";
import { getPanelsData } from "../../../../axios/api/services";

const sliceName = "appData/panelsData";
const panelsDataSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(panelsDataThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(panelsDataThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(panelsDataThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const panelsDataThunk = createAsyncThunk(
  `${sliceName}/panelsDataThunk`,
  async () => {
    const response = await getPanelsData()();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.panelsData);
    },
  }
);

export default panelsDataSlice.reducer;
