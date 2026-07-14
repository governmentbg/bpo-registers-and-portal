import {
  BoxSpg,
  CardSpg,
  CircularLoader,
  DividerSpg,
  GridContainer,
  GridItem,
  iTxt,
  THUNK_STATUS,
} from "@duosoftbg/bpo-components";
import React, { useEffect } from "react";
import { useTranslation } from "react-i18next";
import { Alert, CardContent, Typography } from "@mui/material";
import DOMPurify from "dompurify";
import i18n from "i18next";
import styled from "styled-components";
import { Link } from "react-router-dom";
import useAppDispatch from "../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";
import { eServicesDataThunk } from "../../../store/redux/slice/AppData/eservicesData";

const Title = styled(Typography)<{ component: any }>`
  font-size: 25px;
  font-weight: 350;
  margin-bottom: 5px;
  text-align: center;

  @media (max-width: 600px) {
    font-size: 20px;
    padding-top: 8px;
  }
`;

const GridContainerLink = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 1em;
  padding: 1em;

  a {
    text-decoration: none;
  }

  @media (max-width: 600px) {
    grid-template-columns: 1fr;
    padding: 0.5em;
  }
`;

const StyledLink = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  text-decoration: none;
  width: 300px;
  height: 50px;
  font-size: 1rem;
  font-weight: 500;
  color: #353131;
  background-color: rgba(221, 221, 230, 0.53);
  border: 1px solid transparent;
  box-shadow: 4px 4px 8px rgb(133, 134, 138);
  transition: all 0.3s ease;
  margin: 0;
  border-radius: 4px;

  border-left: 10px solid #5facff;

  padding: 0 10px;
  overflow: hidden;

  &:hover {
    background-color: rgba(170, 170, 180, 0.65);
    font-weight: 520;
    color: #000000;
    border-left-color: rgb(81, 138, 197);
  }

  &:active {
    transform: translateY(2px);
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }

  &:focus {
    outline: 2px dashed #80bdff;
    outline-offset: 4px;
  }

  @media (max-width: 600px) {
    width: 85%;
  }
`;

const ServiceGroup = ({ serviceGroup }) => {
  const dispatch = useAppDispatch();

  const { t } = useTranslation();

  const eServices = useAppSelector((state) => {
    return state.AppData.eServicesData;
  });

  useEffect(() => {
    dispatch(eServicesDataThunk(serviceGroup));
  }, [serviceGroup, dispatch]);

  if (!eServices[serviceGroup]?.status) {
    return (
      <BoxSpg>
        <CircularLoader />
      </BoxSpg>
    );
  }

  if (eServices[serviceGroup]?.status === THUNK_STATUS.REJECTED) {
    return (
      <BoxSpg>
        <Alert severity={"error"}>{t("m.error.serverFetchingError")}</Alert>
      </BoxSpg>
    );
  }

  if (eServices[serviceGroup]?.status === THUNK_STATUS.FULFILLED) {
    return <ServiceGroupContent serviceGroup={serviceGroup} content={eServices[serviceGroup].data} />;
  }

  return null;
};

const ServiceGroupContent = ({ serviceGroup, content }) => {
  const title = iTxt(i18n.language, content?.labelBg, content?.labelEn, true);
  const description = iTxt(i18n.language, content?.descriptionBg, content?.descriptionEn, true);

  return (
    <CardSpg my={4} style={{ overflow: "visible" }}>
      <CardContent style={{ position: "relative" }}>
        <>
          <BoxSpg textAlign={"center"} mt={2}>
            <Typography style={{ position: "relative" }} gutterBottom variant="h5" component="div">
              <Title component={"div"}>{title}</Title>
            </Typography>
          </BoxSpg>
          <DividerSpg my={4} />
          {description && (
            <>
              <BoxSpg textAlign={"center"}>
                <Alert variant="outlined" severity="info">
                  <div dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(description) }}></div>
                </Alert>
              </BoxSpg>
              <DividerSpg my={4} />
            </>
          )}

          <GridContainerLink>
            {content.childrenServices?.map((service) => (
              <ServiceLink
                key={service.id}
                serviceGroup={serviceGroup}
                serviceId={service.id}
                titleBg={service.titleBg}
                titleEn={service.titleEn}
              />
            ))}
          </GridContainerLink>
        </>
      </CardContent>
    </CardSpg>
  );
};

const ServiceLink = ({ serviceGroup, serviceId, titleBg, titleEn }) => {
  const title = iTxt(i18n.language, titleBg, titleEn, true);

  return (
    <Link to={`/eservices/${serviceGroup}/service-definition/${serviceId}`}>
      <StyledLink>
        <GridContainer mt={0}>
          <GridItem xs={10} sm={10} md={10}>
            {title}
          </GridItem>
        </GridContainer>
      </StyledLink>
    </Link>
  );
};

export default ServiceGroup;
