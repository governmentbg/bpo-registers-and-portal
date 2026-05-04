import { useTranslation } from "react-i18next";
import useAppDispatch from "../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";
import React, { useEffect } from "react";
import { AlertSpg, BoxSpg, CardSpg, CircularLoader, THUNK_STATUS } from "@duosoftbg/bpo-components";
import { adminConsoleDataThunk } from "../../../store/redux/slice/AppData/adminConsoleData";
import CardContent from "@mui/material/CardContent/CardContent";
import AdminConsoleSection from "./AdminConsoleSection";

const AdminConsole = () => {
  const { t } = useTranslation();

  const dispatch = useAppDispatch();

  const adminConsoleData = useAppSelector((state) => {
    return state.AppData.adminConsoleData;
  });

  useEffect(() => {
    dispatch(adminConsoleDataThunk());
  }, [dispatch]);

  if (THUNK_STATUS.PENDING === adminConsoleData.status) {
    return (
      <BoxSpg>
        <CircularLoader />
      </BoxSpg>
    );
  }

  if (THUNK_STATUS.REJECTED === adminConsoleData.status) {
    return (
      <BoxSpg>
        <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
      </BoxSpg>
    );
  }

  if (THUNK_STATUS.FULFILLED === adminConsoleData.status && adminConsoleData.data) {
    return (
      <CardSpg my={4} style={{ overflow: "visible" }}>
        <CardContent style={{ position: "relative" }}>
          <BoxSpg ml={5}>
            <AdminConsolePageContent key={adminConsoleData.status} content={adminConsoleData.data} />
          </BoxSpg>
        </CardContent>
      </CardSpg>
    );
  }

  return null;
};

const AdminConsolePageContent = ({ content }) => {
  return (
    <>
      {content?.map((section) => (
        <AdminConsoleSection key={section.id} section={section} />
      ))}
    </>
  );
};
export default AdminConsole;
