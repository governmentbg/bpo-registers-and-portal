import { GridContainer, THUNK_STATUS } from "@duosoftbg/bpo-components";
import { AlertSpg, BoxSpg, CircularLoader } from "@duosoftbg/bpo-components";
import React, { useEffect } from "react";
import { useTranslation } from "react-i18next";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";
import useAppDispatch from "../../../hooks/redux/base/useAppDispatch";
import { Typography } from "@mui/material";
import UserGuidesCardContent from "./UserGuidesCardContent";
import styled from "styled-components";
import { userGuidesDataThunk } from "../../../store/redux/slice/AppData/userGuidesData";
import { UserGuidesType } from "../../../types/userGuides/userGuidesTypes";

const UserGuidesTitle = styled(Typography)<{ component: any }>`
  font-size: 25px;
  font-weight: 450;
  margin-bottom: 5px;
  text-align: center;
`;

const UserGuides = () => {
  const { t } = useTranslation();

  const dispatch = useAppDispatch();

  const userGuidesData = useAppSelector((state) => {
    return state.AppData.userGuidesData;
  });

  useEffect(() => {
    dispatch(userGuidesDataThunk());
  }, [dispatch]);

  if (THUNK_STATUS.PENDING === userGuidesData.status) {
    return (
      <BoxSpg>
        <CircularLoader />
      </BoxSpg>
    );
  }

  if (THUNK_STATUS.REJECTED === userGuidesData.status) {
    return (
      <BoxSpg>
        <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
      </BoxSpg>
    );
  }
  if (THUNK_STATUS.FULFILLED === userGuidesData.status && userGuidesData.data) {
    return (
      <>
        <BoxSpg textAlign={"center"} mt={2}>
          <Typography style={{ position: "relative" }} gutterBottom variant="h5" component="div">
            <UserGuidesTitle component={"h2"} variant={"h2"} style={{ paddingBottom: "20px", paddingTop: "10px" }}>
              {t("t.page.userGuides")}
            </UserGuidesTitle>
          </Typography>
        </BoxSpg>
        <GridContainer spacing={2}>
          {userGuidesData.data?.map((item: UserGuidesType) => (
            <UserGuidesCardContent key={item.id} {...item} />
          ))}
        </GridContainer>
      </>
    );
  }

  return null;
};

export default UserGuides;
