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
import { PlantBreedFilterDetails } from "../../../../../types/registers/search/filter/filterTypes";
import { plantBreedSearchValidationSchema } from "../../../../../yup/schema/registers/search/patentLikeSearchValidationSchema";
import PlantBreedFilters from "./filters/PlantBreedFilters";
import { plantBreedFiltersInitialValues } from "../../../../../init/registers/search/filter/patentLikeFiltersInitialValues";
import { searchPlantsBreeds } from "../../../../../axios/api/services";
import DownloadButtonsTable from "../../../common/download/combined/DownloadButtonsTable";
import { RegisterType } from "../../../../../utils/constants";

const SearchPlantsBreeds = () => {
  const store = useStore();
  const searchGroup = SEARCH_GROUP.PLANT_BREED_SEARCH;

  const { methods, handleSubmit } = useReactHookForm<PlantBreedFilterDetails>({
    defaultValues: plantBreedFiltersInitialValues,
    validationSchema: plantBreedSearchValidationSchema,
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
    initialValues: plantBreedFiltersInitialValues,
    filterData: searchPlantsBreeds,
    callAfterResetFilters: true,
  });

  const componentRef = useRef();

  return (
    <>
      <FormProvider {...methods}>
        <form onSubmit={handleSubmit(() => control.handleSubmitFilters())}>
          <CardSpg my={4} style={{ overflow: "visible" }}>
            <CardContent style={{ position: "relative" }} ref={componentRef}>
              <DownloadButtonsTable searchGroup={searchGroup} registerType={RegisterType.PLANT_BREED} />
              <SearchFiltersDialog searchGroup={SEARCH_GROUP.PLANT_BREED_SEARCH}></SearchFiltersDialog>
              <DividerSpg my={2} />
              <PlantBreedFilters control={control} />
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

export default SearchPlantsBreeds;
