import { AppBreadcrumbs, THUNK_STATUS } from "@duosoftbg/bpo-components";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import {
  dynamicBreadcrumbData,
  initialBreadcrumbData,
  staticBreadcrumbData,
} from "../../../../config/breadcrumb/registersBreadcrumb";

const Breadcrumbs = () => {
  const panelsData = useAppSelector((state) => {
    return state.AppData.panelsData;
  });

  const finalBreadcrumbData =
    panelsData.status === THUNK_STATUS.FULFILLED
      ? dynamicBreadcrumbData(panelsData.data.childrenPanels)
      : staticBreadcrumbData;

  const finalInitialBreadcrumbData =
    panelsData.status === THUNK_STATUS.FULFILLED ? initialBreadcrumbData(panelsData.data) : [];

  return <AppBreadcrumbs data={[...finalBreadcrumbData]} initial={[...finalInitialBreadcrumbData]} />;
};

export default Breadcrumbs;
