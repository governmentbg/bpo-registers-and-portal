import SectionContent from "./section/SectionContent";
import useAppDispatch from "../../../hooks/redux/base/useAppDispatch";
import { useParams } from "react-router-dom";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";
import { useEffect } from "react";
import { homePageDataThunk } from "../../../store/redux/slice/AppData/homePageData";

const HomeSection = () => {
  const dispatch = useAppDispatch();
  const params = useParams();

  const homePageData = useAppSelector((state) => {
    return state.AppData.homePageData;
  });
  const sections = homePageData?.data?.sections;
  const section = sections && sections?.find((element) => element.id === params?.sectionId);

  useEffect(() => {
    dispatch(homePageDataThunk());
  }, [dispatch]);

  if (section) {
    return <SectionContent {...section} />;
  }

  return null;
};

export default HomeSection;
