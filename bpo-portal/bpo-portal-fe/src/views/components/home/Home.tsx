import { isNotEmpty, THUNK_STATUS } from "@duosoftbg/bpo-components";
import { AlertSpg, BoxSpg, CircularLoader } from "@duosoftbg/bpo-components";
import React, { useEffect } from "react";
import SectionContent from "./section/SectionContent";
import { useTranslation } from "react-i18next";
import { HomePageType } from "../../../types/home/sections/section";
import { homePageDataThunk } from "../../../store/redux/slice/AppData/homePageData";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";
import useAppDispatch from "../../../hooks/redux/base/useAppDispatch";
import DynamicPortalMessage from "./dynamicMessage/DynamicPortalMessage";

const Home = () => {
  const { t } = useTranslation();

  const dispatch = useAppDispatch();

  const homePageData = useAppSelector((state) => {
    return state.AppData.homePageData;
  });

  useEffect(() => {
    dispatch(homePageDataThunk());
  }, [dispatch]);

  if (THUNK_STATUS.PENDING === homePageData.status) {
    return (
      <BoxSpg>
        <CircularLoader />
      </BoxSpg>
    );
  }

  if (THUNK_STATUS.REJECTED === homePageData.status) {
    return (
      <BoxSpg>
        <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
      </BoxSpg>
    );
  }
  if (THUNK_STATUS.FULFILLED === homePageData.status && homePageData.data) {
    return <HomePageContent {...homePageData.data} />;
  }

  return null;
};

const HomePageContent = (contentHomePage: HomePageType) => {
  const hasDescription = isNotEmpty(contentHomePage.descriptionBg) || isNotEmpty(contentHomePage.descriptionEn);
  const messageContent = hasDescription
    ? {
        descriptionBg: contentHomePage.descriptionBg,
        descriptionEn: contentHomePage.descriptionEn,
      }
    : null;
  return (
    <>
      <DynamicPortalMessage {...messageContent} />
      {contentHomePage.sections?.map((section) => (
        <SectionContent key={section.id} {...section} childrenPanels={section.childrenPanels} />
      ))}
    </>
  );
};

export default Home;
