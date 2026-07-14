import React, { useEffect, useRef } from "react";
import { SEARCH_GROUP } from "../../../../../config/registers/search/registersSearchConfig";
import { useStore } from "react-redux";
import { CardSpg, DividerSpg, isNotEmpty, useReactHookForm } from "@duosoftbg/bpo-components";
import useSearchTableControl from "../../../../../hooks/registers/search/useSearchTableControl";
import { FormProvider } from "react-hook-form";
import { CardContent } from "@mui/material";
import SearchFiltersDialog from "../../../common/search/filters/dialog/SearchFiltersDialog";
import RegisterList from "../common/registerListTable/RegisterList";
import { PartnershipFilterDetails } from "../../../../../types/registers/search/filter/filterTypes";
import { partnershipsFiltersInitialValues } from "../../../../../init/registers/search/filter/agentsFilterInitialValues";
import { partnershipSearchValidationSchema } from "../../../../../yup/schema/registers/search/agentSearchValidationSchema";
import { searchAgents } from "../../../../../axios/api/services";
import AgentRegisterListTable from "./common/AgentRegisterListTable";
import AgentAddressDialog from "./dialog/AgentAddressDialog";
import PartnershipFilters from "./filters/PartnershipFilters";
import { RegisterType } from "../../../../../utils/constants";
import DownloadButtonsTable from "../../../common/download/combined/DownloadButtonsTable";

const SearchPartnerships = () => {
  const store = useStore();
  const searchGroup = SEARCH_GROUP.PARTNERSHIP_SEARCH;

  const { methods, handleSubmit } = useReactHookForm<PartnershipFilterDetails>({
    defaultValues: partnershipsFiltersInitialValues,
    validationSchema: partnershipSearchValidationSchema,
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
    initialValues: partnershipsFiltersInitialValues,
    filterData: searchAgents,
    callAfterResetFilters: true,
  });

  const componentRef = useRef();

  return (
    <>
      <AgentAddressDialog />
      <FormProvider {...methods}>
        <form onSubmit={handleSubmit(() => control.handleSubmitFilters())}>
          <CardSpg my={4} style={{ overflow: "visible" }}>
            <CardContent style={{ position: "relative" }} ref={componentRef}>
              <DownloadButtonsTable searchGroup={searchGroup} registerType={RegisterType.AGENT} />
              <SearchFiltersDialog searchGroup={SEARCH_GROUP.PARTNERSHIP_SEARCH}></SearchFiltersDialog>
              <DividerSpg my={2} />
              <PartnershipFilters control={control} />
              <RegisterList isLoading={control.isLoading}>
                <AgentRegisterListTable
                  total={control.total}
                  records={control.records}
                  blockTable={control.blockTable}
                  onPageOrOrderChange={control.handlePageOrOrderChange}
                  searchGroup={searchGroup}
                  registerType={RegisterType.PARTNERSHIP}
                />
              </RegisterList>
            </CardContent>
          </CardSpg>
        </form>
      </FormProvider>
    </>
  );
};

export default SearchPartnerships;
