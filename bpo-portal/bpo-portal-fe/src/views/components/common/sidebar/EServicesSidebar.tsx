import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import React, { useEffect } from "react";
import { BpoSidebar, THUNK_STATUS } from "@duosoftbg/bpo-components";
import { dynamicSidebarPath } from "../../../../config/sidebar/sidebarData";
import { eServicesDataThunk } from "../../../../store/redux/slice/AppData/eservicesData";

const EServicesSidebar = ({ serviceGroup }) => {
  const dispatch = useAppDispatch();

  const eServices = useAppSelector((state) => {
    return state.AppData.eServicesData;
  });

  useEffect(() => {
    dispatch(eServicesDataThunk(serviceGroup));
  }, [serviceGroup, dispatch]);

  if (
    THUNK_STATUS.PENDING === eServices[serviceGroup]?.status ||
    THUNK_STATUS.REJECTED === eServices[serviceGroup]?.status
  ) {
    return <BpoSidebar sidebarData={[]} />;
  }

  if (THUNK_STATUS.FULFILLED === eServices[serviceGroup]?.status) {
    return (
      <BpoSidebar
        key={eServices[serviceGroup].status}
        sidebarData={dynamicSidebarPath.eservicesSidebar.transformFn(eServices[serviceGroup].data)}
      />
    );
  }

  return null;
};
export default EServicesSidebar;
