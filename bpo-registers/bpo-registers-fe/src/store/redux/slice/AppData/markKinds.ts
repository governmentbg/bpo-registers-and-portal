import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/bpo-components";
import { getMarkKinds } from "../../../../axios/api/services";

const sliceName = "appData/markKinds";
const markKindsSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(markKindsThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(markKindsThunk.fulfilled, (state, action) => {
      const data = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(markKindsThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const markKindsThunk = createAsyncThunk(
  `${sliceName}/${sliceName}Thunk`,
  async () => {
    const response = await getMarkKinds()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.markKinds);
    },
  }
);

export default markKindsSlice.reducer;
