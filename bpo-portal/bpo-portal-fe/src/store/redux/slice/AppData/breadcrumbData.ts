import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/bpo-components";
import { getDynamicBreadcrumbData } from "../../../../axios/api/services";

const sliceName = "appData/breadcrumbData";
const breadcrumbDataSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(breadcrumbDataThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(breadcrumbDataThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(breadcrumbDataThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const breadcrumbDataThunk = createAsyncThunk(
  `${sliceName}/breadcrumbDataThunk`,
  async () => {
    const response = await getDynamicBreadcrumbData()();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.breadcrumbData);
    },
  }
);

export default breadcrumbDataSlice.reducer;
