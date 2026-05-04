import { BpoSidebar, GlobalBackdrop, THUNK_STATUS, WithChildren } from "@duosoftbg/bpo-components";
import { Box } from "@mui/material";
import { Fragment, useEffect } from "react";
import { Outlet } from "react-router-dom";
import Header from "../components/common/layout/Header";
import useAppDispatch from "../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../hooks/redux/base/useAppSelector";
import { panelsDataThunk } from "../../store/redux/slice/AppData/panelsData";
import { dynamicSidebarData, staticSidebarData } from "../../config/sidebar/registersSidebar";
import Footer from "../components/common/layout/Footer";

const RegistersLayout = (props: WithChildren) => {
  const { children } = props;

  const dispatch = useAppDispatch();

  const panelsData = useAppSelector((state) => {
    return state.AppData.panelsData;
  });

  useEffect(() => {
    dispatch(panelsDataThunk());
  }, [dispatch]);

  const finalSidebarData =
    panelsData.status === THUNK_STATUS.FULFILLED ? dynamicSidebarData(panelsData) : staticSidebarData;

  return (
    <Fragment>
      <Header />
      <BpoSidebar key={panelsData.status} sidebarData={finalSidebarData} />
      <Box>
        {children}
        <Outlet />
      </Box>
      <Footer />
      <GlobalBackdrop />
      {/*<AppModulesPageBlockerDialog />*/}
    </Fragment>
  );
};

export default RegistersLayout;
