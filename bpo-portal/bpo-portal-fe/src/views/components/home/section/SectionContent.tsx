import { BoxSpg, GridContainer, iTxt } from "@duosoftbg/bpo-components";
import { Typography } from "@mui/material";
import React from "react";
import styled from "styled-components";
import i18n from "i18next";
import DOMPurify from "dompurify";
import { SectionContentType } from "../../../../types/home/sections/section";
import SectionCardContent from "./card/SectionCardContent";

const SectionTitle = styled(Typography)<{ component: any }>`
  font-size: 25px;
  font-weight: 450;
  margin-bottom: 5px;
  text-align: center;
`;

const SectionContent = (section: SectionContentType) => {
  const title = iTxt(i18n.language, section.labelBg, section.labelEn);
  const description = iTxt(i18n.language, section.descriptionBg, section.descriptionEn);

  return (
    <>
      <BoxSpg textAlign={"center"} mt={2}>
        <Typography style={{ position: "relative" }} gutterBottom variant="h5" component="div">
          <SectionTitle component={"h2"} variant={"h2"}>
            {title}
          </SectionTitle>
        </Typography>
      </BoxSpg>
      {description && (
        <BoxSpg textAlign={"center"}>
          <div dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(description) }}></div>
        </BoxSpg>
      )}

      <GridContainer spacing={2}>
        {section.childrenPanels?.map((children) => (
          <SectionCardContent key={children.titleBg} {...children} />
        ))}
      </GridContainer>
    </>
  );
};
export default SectionContent;
