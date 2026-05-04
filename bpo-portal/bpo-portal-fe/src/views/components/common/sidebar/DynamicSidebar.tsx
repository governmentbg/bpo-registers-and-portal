import { useLocation, useParams } from "react-router-dom";
import { dynamicSidebarPath } from "../../../../config/sidebar/sidebarData";
import AdminConsoleSidebar from "./AdminConsoleSidebar";
import EServicesSidebar from "./EServicesSidebar";
import HomeSidebar from "./HomeSidebar";

const DynamicSidebar = () => {
  const location = useLocation();
  const params = useParams();

  if (location.pathname.startsWith(dynamicSidebarPath.eservicesSidebar.path)) {
    return <EServicesSidebar serviceGroup={params?.serviceGroup} />;
  }
  if (location.pathname.startsWith(dynamicSidebarPath.adminConsoleSidebar.path)) {
    return <AdminConsoleSidebar />;
  }

  return <HomeSidebar />;
};

export default DynamicSidebar;
