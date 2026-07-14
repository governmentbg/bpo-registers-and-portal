import { THUNK_STATUS } from "@duosoftbg/bpo-components";
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { getServiceDefinitionsByGroup } from "../../../../axios/api/services";

const initialState = {};

const sliceName = "appData/eServicesData";
const eServicesDataSlice = createSlice({
  name: sliceName,
  initialState: initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(eServicesDataThunk.pending, (state, action) => {
      return { ...state, [action.meta.arg]: { status: THUNK_STATUS.PENDING } };
    });
    builder.addCase(eServicesDataThunk.fulfilled, (state, action) => {
      const data = action.payload;
      return { ...state, [data.id]: { data: data.content, status: THUNK_STATUS.FULFILLED } };
    });
    builder.addCase(eServicesDataThunk.rejected, (state, action) => {
      return { ...state, [action.meta.arg]: { status: THUNK_STATUS.REJECTED } };
    });
  },
});

export const eServicesDataThunk = createAsyncThunk(
  `${sliceName}/eServicesDataThunk`,
  async (serviceGroup: string) => {
    const response = await getServiceDefinitionsByGroup(serviceGroup)();
    return { content: response, id: serviceGroup };
  },
  {
    condition: (args, { getState, extra }) => {
      return (
        !getState()["AppData"]["eServicesData"][args] ||
        !getState()["AppData"]["eServicesData"][args]["status"] ||
        getState()["AppData"]["eServicesData"][args]["status"] !== THUNK_STATUS.FULFILLED
      );
    },
  }
);

export default eServicesDataSlice.reducer;
