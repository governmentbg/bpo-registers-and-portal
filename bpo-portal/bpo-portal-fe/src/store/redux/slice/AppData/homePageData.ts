import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/bpo-components";
import { getHomePageData } from "../../../../axios/api/services";

const sliceName = "appData/homePageData";
const homePageDataSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(homePageDataThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(homePageDataThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(homePageDataThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const homePageDataThunk = createAsyncThunk(
  `${sliceName}/homePageDataThunk`,
  async () => {
    const response = await getHomePageData()();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.homePageData);
    },
  }
);

export default homePageDataSlice.reducer;
