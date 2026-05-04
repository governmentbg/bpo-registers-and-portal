import { Card, CardContent as MuiCardContent, Typography } from "@mui/material";
import styled from "styled-components";
import DOMPurify from "dompurify";
import { BoxSpg, GridItem, iTxt, SvgIcon } from "@duosoftbg/bpo-components";
import i18n from "i18next";
import React from "react";
import Navigation from "../../../common/navigation/Navigation";
import { CardContentType } from "../../../../../types/home/cards/card";

const IconWrapper = styled.span`
  svg {
    width: 30px;
    color: ${(props) => (props.theme.palette.mode === "dark" ? "#87aece" : "#059")};
  }
`;

const CardWrapper = styled(Card)`
  height: 100%;

  &:hover {
    background: ${(props) => (props.theme.palette.mode === "dark" ? "rgba(6, 78, 148, 0.3)" : "#eee")};
  }
`;

const SectionCardContent = (content: CardContentType) => {
  const { action, actionTarget, img, gridProps = { xs: 12, sm: 6, md: 4, lg: 3 } } = content;

  const title = iTxt(i18n.language, content.titleBg, content.titleEn);
  const description = iTxt(i18n.language, content.descriptionBg, content.descriptionEn);

  return (
    <GridItem xs={gridProps.xs} sm={gridProps.sm} md={gridProps.md} lg={gridProps.lg}>
      <Navigation action={action} actionTarget={actionTarget}>
        <CardWrapper>
          <MuiCardContent style={{ padding: "30px", paddingTop: "20px" }}>
            <Typography
              variant="h5"
              component="div"
              sx={{
                display: "flex",
                alignItems: "center",
                gap: "0.6rem",
              }}
            >
              {img && (
                <BoxSpg sx={{ width: 30, height: 30, display: "flex", alignItems: "center" }}>
                  <IconWrapper>
                    <SvgIcon img={img} />
                  </IconWrapper>
                </BoxSpg>
              )}

              <BoxSpg
                component="span"
                sx={{
                  wordBreak: "break-word",
                  whiteSpace: "normal",
                  flex: 1,
                  fontWeight: "bold",
                }}
              >
                {title}
              </BoxSpg>
            </Typography>

            {description && (
              <Typography component="div" style={{ marginTop: "15px" }}>
                <div dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(description) }}></div>
              </Typography>
            )}
          </MuiCardContent>
        </CardWrapper>
      </Navigation>
    </GridItem>
  );
};

export default SectionCardContent;
