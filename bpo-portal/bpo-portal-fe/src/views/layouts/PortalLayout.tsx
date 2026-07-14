import { Box } from "@mui/material";
import { Fragment } from "react";
import { Outlet } from "react-router-dom";
import Header from "../components/common/layout/Header";
import Footer from "../components/common/layout/Footer";
import { GlobalBackdrop, WithChildren } from "@duosoftbg/bpo-components";
import DynamicSidebar from "../components/common/sidebar/DynamicSidebar";

type PortalLayoutProps = WithChildren<{
  hideSubHeader?: boolean;
}>;

const PortalLayout = (props: PortalLayoutProps) => {
  const { children, hideSubHeader = false } = props;
  return (
    <Fragment>
      <Header hideSubHeader={hideSubHeader} />
      <DynamicSidebar />

      <Box>
        {children}
        <Outlet />
      </Box>
      <Footer />
      <GlobalBackdrop />
    </Fragment>
  );
};

export default PortalLayout;
