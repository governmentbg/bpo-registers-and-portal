import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/bpo-components";
import { getAdminConsoleData } from "../../../../axios/api/services";

const sliceName = "appData/adminConsoleData";
const adminConsoleDataSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(adminConsoleDataThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(adminConsoleDataThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(adminConsoleDataThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const adminConsoleDataThunk = createAsyncThunk(
  `${sliceName}/adminConsoleDataThunk`,
  async () => {
    const response = await getAdminConsoleData()();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.adminConsoleData);
    },
  }
);

export default adminConsoleDataSlice.reducer;
