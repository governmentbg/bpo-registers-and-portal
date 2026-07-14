import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import React, { useEffect } from "react";
import { homePageDataThunk } from "../../../../store/redux/slice/AppData/homePageData";
import { BpoSidebar, THUNK_STATUS } from "@duosoftbg/bpo-components";
import { dynamicSidebarPath } from "../../../../config/sidebar/sidebarData";

const HomeSidebar = () => {
  const dispatch = useAppDispatch();

  const homePageData = useAppSelector((state) => {
    return state.AppData.homePageData;
  });

  useEffect(() => {
    dispatch(homePageDataThunk());
  }, [dispatch]);

  if (THUNK_STATUS.PENDING === homePageData.status || THUNK_STATUS.REJECTED === homePageData.status) {
    return <BpoSidebar sidebarData={[]} />;
  }

  if (THUNK_STATUS.FULFILLED === homePageData.status) {
    return (
      <BpoSidebar
        key={homePageData.status}
        sidebarData={dynamicSidebarPath.homeSidebar.transformFn(homePageData.data)}
      />
    );
  }

  return null;
};

export default HomeSidebar;
