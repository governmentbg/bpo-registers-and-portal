import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/bpo-components";
import { getCountries } from "../../../../axios/api/services";

const sliceName = "appData/countries";
const countriesSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(countriesThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(countriesThunk.fulfilled, (state, action) => {
      const data = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(countriesThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const countriesThunk = createAsyncThunk(
  `${sliceName}/${sliceName}Thunk`,
  async () => {
    const response = await getCountries()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.countries);
    },
  }
);

export default countriesSlice.reducer;
