import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/bpo-components";
import { getAllRepresentativeTypes } from "../../../../axios/api/services";

const sliceName = "appData/representativeTypes";
const representativeTypesSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(representativeTypesThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(representativeTypesThunk.fulfilled, (state, action) => {
      const data = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(representativeTypesThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const representativeTypesThunk = createAsyncThunk(
  `${sliceName}/${sliceName}Thunk`,
  async () => {
    const response = await getAllRepresentativeTypes()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.representativeTypes);
    },
  }
);

export default representativeTypesSlice.reducer;
