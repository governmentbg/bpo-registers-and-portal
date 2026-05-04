import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/bpo-components";
import { getUserGuidesData } from "../../../../axios/api/services";

const sliceName = "appData/userGuidesData";
const userGuidesDataSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(userGuidesDataThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(userGuidesDataThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(userGuidesDataThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const userGuidesDataThunk = createAsyncThunk(
  `${sliceName}/userGuidesDataThunk`,
  async () => {
    const response = await getUserGuidesData()();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.userGuidesData);
    },
  }
);

export default userGuidesDataSlice.reducer;
