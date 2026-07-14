import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { REGISTERS_SEARCH_CONFIG } from "../../../../config/registers/search/registersSearchConfig";
import {
  convertArrayToObject,
  deepCopy,
  generateConfigValue,
  LocalStorageActions,
  updateValueInLocalStorage,
} from "@duosoftbg/bpo-components";
import { RegistersConfigName } from "../../../../utils/constants";

const createInitialState = () => {
  let result = [];
  for (const item in REGISTERS_SEARCH_CONFIG) {
    result.push(createInitialGroupValue(item));
  }
  return convertArrayToObject(result, "id");
};

function createInitialGroupValue(group) {
  setConfigDataToLocalStorage(group, RegistersConfigName.SELECTED_FILTERS);
  setConfigDataToLocalStorage(group, RegistersConfigName.SELECTED_TABLE_COLUMNS);

  let initialGroupValue = {
    id: null,
    hasSearchStarted: REGISTERS_SEARCH_CONFIG[group].hasSearchStarted ?? true,
    filtersData: REGISTERS_SEARCH_CONFIG[group].filtersInitialValues,
    selectedFilters: LocalStorageActions.getStoredValues(
      group,
      RegistersConfigName.SELECTED_FILTERS,
      REGISTERS_SEARCH_CONFIG
    ),
    selectedTableColumns: LocalStorageActions.getStoredValues(
      group,
      RegistersConfigName.SELECTED_TABLE_COLUMNS,
      REGISTERS_SEARCH_CONFIG
    ),
  };

  initialGroupValue.id = group;
  return initialGroupValue;
}
function setConfigDataToLocalStorage(group, registerConfigName) {
  let value = generateConfigValue(group, registerConfigName, REGISTERS_SEARCH_CONFIG);
  let storedColumns = LocalStorageActions.getStoredValues(group, registerConfigName, REGISTERS_SEARCH_CONFIG);
  if (!storedColumns) {
    LocalStorageActions.setValue(group, registerConfigName, value);
  }
}

const registersSearchDataSlice = createSlice({
  name: "registersSearchData",
  initialState: createInitialState(),
  reducers: {
    updateSelectedColumnsValue: (state, action: PayloadAction<{ group: string; name: string; value: boolean }>) => {
      const { group, name, value } = action.payload;
      updateValueInLocalStorage({
        group,
        configName: RegistersConfigName.SELECTED_TABLE_COLUMNS,
        config: REGISTERS_SEARCH_CONFIG,
        name,
        value,
      });
      state[group].selectedTableColumns[name] = value;
    },
    updateSelectedFiltersValue: (state, action: PayloadAction<{ group: string; name: string; value: boolean }>) => {
      const { group, name, value } = action.payload;
      updateValueInLocalStorage({
        group,
        configName: RegistersConfigName.SELECTED_FILTERS,
        config: REGISTERS_SEARCH_CONFIG,
        name,
        value,
      });
      state[group].selectedFilters[name] = value;
    },
    overrideFiltersData: (state, action) => {
      const { group, data } = action.payload;
      state[group].filtersData = deepCopy(data);
    },
    updateHasSearchStarted: (state, action) => {
      const { group, start } = action.payload;
      state[group].hasSearchStarted = start;
    },
  },
});

export const { overrideFiltersData, updateHasSearchStarted, updateSelectedFiltersValue, updateSelectedColumnsValue } =
  registersSearchDataSlice.actions;

export default registersSearchDataSlice.reducer;
