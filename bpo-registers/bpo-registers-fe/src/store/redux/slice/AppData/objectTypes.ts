import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  commonThunkCondition,
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
} from "@duosoftbg/bpo-components";
import { getObjectTypes } from "../../../../axios/api/services";

const sliceName = "appData/objectTypes";
const objectTypesSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(objectTypesThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(objectTypesThunk.fulfilled, (state, action) => {
      const data = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(objectTypesThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const objectTypesThunk = createAsyncThunk(
  `${sliceName}/${sliceName}Thunk`,
  async () => {
    const response = await getObjectTypes()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.objectTypes);
    },
  }
);

export default objectTypesSlice.reducer;
