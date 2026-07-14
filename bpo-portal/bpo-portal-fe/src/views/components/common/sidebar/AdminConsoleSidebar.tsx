import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import React, { useEffect } from "react";
import { BpoSidebar, THUNK_STATUS } from "@duosoftbg/bpo-components";
import { dynamicSidebarPath } from "../../../../config/sidebar/sidebarData";
import { adminConsoleDataThunk } from "../../../../store/redux/slice/AppData/adminConsoleData";

const AdminConsoleSidebar = () => {
  const dispatch = useAppDispatch();

  const adminConsoleData = useAppSelector((state) => {
    return state.AppData.adminConsoleData;
  });

  useEffect(() => {
    dispatch(adminConsoleDataThunk());
  }, [dispatch]);

  if (THUNK_STATUS.PENDING === adminConsoleData?.status || THUNK_STATUS.REJECTED === adminConsoleData?.status) {
    return <BpoSidebar sidebarData={[]} />;
  }

  if (THUNK_STATUS.FULFILLED === adminConsoleData?.status) {
    return (
      <BpoSidebar
        key={adminConsoleData.status}
        sidebarData={dynamicSidebarPath.adminConsoleSidebar.transformFn(adminConsoleData.data)}
      />
    );
  }

  return null;
};
export default AdminConsoleSidebar;
