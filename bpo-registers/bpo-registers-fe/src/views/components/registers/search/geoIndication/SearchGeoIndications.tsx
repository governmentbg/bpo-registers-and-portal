import React, { useEffect, useRef } from "react";
import { SEARCH_GROUP } from "../../../../../config/registers/search/registersSearchConfig";
import { useStore } from "react-redux";
import { CardSpg, DividerSpg, isNotEmpty, useReactHookForm } from "@duosoftbg/bpo-components";
import { geoIndicationsFiltersInitialValues } from "../../../../../init/registers/search/filter/markLikeFiltersInitialValues";
import { GeoIndicationFilterDetails } from "../../../../../types/registers/search/filter/filterTypes";
import { geoIndicationSearchValidationSchema } from "../../../../../yup/schema/registers/search/markLikeSearchValidationSchema";
import useSearchTableControl from "../../../../../hooks/registers/search/useSearchTableControl";
import { searchGeoIndications } from "../../../../../axios/api/services";
import { FormProvider } from "react-hook-form";
import { CardContent } from "@mui/material";
import SearchFiltersDialog from "../../../common/search/filters/dialog/SearchFiltersDialog";
import RegisterList from "../common/registerListTable/RegisterList";
import RegisterListTable from "../common/registerListTable/RegisterListTable";
import GeoIndicationFilters from "./filters/GeoIndicationFilters";
import DownloadButtonsTable from "../../../common/download/combined/DownloadButtonsTable";
import { RegisterType } from "../../../../../utils/constants";

const SearchGeoIndications = () => {
  const store = useStore();
  const searchGroup = SEARCH_GROUP.GEO_INDICATION_SEARCH;

  const { methods, handleSubmit } = useReactHookForm<GeoIndicationFilterDetails>({
    defaultValues: geoIndicationsFiltersInitialValues,
    validationSchema: geoIndicationSearchValidationSchema,
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
    initialValues: geoIndicationsFiltersInitialValues,
    filterData: searchGeoIndications,
    callAfterResetFilters: true,
  });

  const componentRef = useRef();

  return (
    <>
      <FormProvider {...methods}>
        <form onSubmit={handleSubmit(() => control.handleSubmitFilters())}>
          <CardSpg my={4} style={{ overflow: "visible" }}>
            <CardContent style={{ position: "relative" }} ref={componentRef}>
              <DownloadButtonsTable searchGroup={searchGroup} registerType={RegisterType.GEO_INDICATION} />
              <SearchFiltersDialog searchGroup={searchGroup}></SearchFiltersDialog>
              <DividerSpg my={2} />
              <GeoIndicationFilters control={control} />
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

export default SearchGeoIndications;
