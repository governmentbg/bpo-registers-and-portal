import React, { useEffect, useRef } from "react";
import { SEARCH_GROUP } from "../../../../../config/registers/search/registersSearchConfig";
import { useStore } from "react-redux";
import { CardSpg, DividerSpg, isNotEmpty, useReactHookForm } from "@duosoftbg/bpo-components";
import useSearchTableControl from "../../../../../hooks/registers/search/useSearchTableControl";
import { FormProvider } from "react-hook-form";
import { CardContent } from "@mui/material";
import SearchFiltersDialog from "../../../common/search/filters/dialog/SearchFiltersDialog";
import RegisterList from "../common/registerListTable/RegisterList";
import RegisterListTable from "../common/registerListTable/RegisterListTable";
import { CombinedSearchFilterDetails } from "../../../../../types/registers/search/filter/filterTypes";
import { combinedSearchValidationSchema } from "../../../../../yup/schema/registers/search/patentLikeSearchValidationSchema";
import { searchCombined } from "../../../../../axios/api/services";
import DownloadButtonsTable from "../../../common/download/combined/DownloadButtonsTable";
import { RegisterType } from "../../../../../utils/constants";
import { combinedFiltersInitialValues } from "../../../../../init/registers/search/filter/combinedFiltersInitialValues";
import CombinedFilters from "./filters/CombinedFilters";

const SearchCombined = () => {
  const store = useStore();
  const searchGroup = SEARCH_GROUP.COMBINED_SEARCH;

  const { methods, handleSubmit } = useReactHookForm<CombinedSearchFilterDetails>({
    defaultValues: combinedFiltersInitialValues,
    validationSchema: combinedSearchValidationSchema,
  });

  useEffect(() => {
    const filtersData = store.getState().SearchData.registersSearchData[searchGroup].filtersData;
    if (isNotEmpty(filtersData)) {
      methods.reset(filtersData);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const control = useSearchTableControl({
    searchGroup,
    methods,
    initialValues: combinedFiltersInitialValues,
    filterData: searchCombined,
    callAfterResetFilters: true,
  });

  const componentRef = useRef();

  return (
    <>
      <FormProvider {...methods}>
        <form onSubmit={handleSubmit(() => control.handleSubmitFilters())}>
          <CardSpg my={4} style={{ overflow: "visible" }}>
            <CardContent style={{ position: "relative" }} ref={componentRef}>
              <DownloadButtonsTable searchGroup={searchGroup} registerType={RegisterType.COMBINED} />
              <SearchFiltersDialog searchGroup={SEARCH_GROUP.COMBINED_SEARCH}></SearchFiltersDialog>
              <DividerSpg my={2} />
              <CombinedFilters control={control} />
              <RegisterList isLoading={control.isLoading}>
                <RegisterListTable
                  total={control.total}
                  records={control.records}
                  blockTable={control.blockTable}
                  onPageOrOrderChange={control.handlePageOrOrderChange}
                  searchGroup={searchGroup}
                />
              </RegisterList>
            </CardContent>
          </CardSpg>
        </form>
      </FormProvider>
    </>
  );
};

export default SearchCombined;
