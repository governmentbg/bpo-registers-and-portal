import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/bpo-components";
import { getIpoAreas } from "../../../../axios/api/services";

const sliceName = "appData/ipoAreas";
const ipoAreasSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(ipoAreasThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(ipoAreasThunk.fulfilled, (state, action) => {
      const data = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(ipoAreasThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const ipoAreasThunk = createAsyncThunk(
  `${sliceName}/${sliceName}Thunk`,
  async () => {
    const response = await getIpoAreas()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.ipoAreas);
    },
  }
);

export default ipoAreasSlice.reducer;
