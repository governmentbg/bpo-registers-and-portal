import { Card, CardContent as MuiCardContent, Typography } from "@mui/material";
import styled from "styled-components";
import DOMPurify from "dompurify";
import { GridItem, iTxt } from "@duosoftbg/bpo-components";
import i18n from "i18next";
import React from "react";

const IconWrapper = styled.span`
  svg {
    width: 30px;
    color: ${(props) => (props.theme.palette.mode === "dark" ? "#87aece" : "#059")};
  }
`;

const CardTitle = styled.span`
  position: absolute;
  margin-left: 10px;
  margin-top: 5px;
  font-weight: bold;
`;

const CardWrapper = styled(Card)`
  height: 100%;
`;

type MessageCardContentType = {
  title?: string;
  img?: any;
  descriptionBg: string;
  descriptionEn: string;
};

const MessageCardContent = (content: MessageCardContentType) => {
  if (!content) {
    return null;
  }
  const gridProps = { xs: 12, sm: 6, md: 12, lg: 1 };

  const description = iTxt(i18n.language, content.descriptionBg, content.descriptionEn, true);
  const img = content.img;
  const title = content.title;
  const headerExists = img || title;
  const topPadding = headerExists ? "10px" : "0px";
  const bottomPadding = headerExists ? "15px" : "5px";
  const topMargin = headerExists ? "15px" : "0px";

  return (
    <>
      <GridItem xs={gridProps.xs} sm={gridProps.sm} md={gridProps.md} lg={gridProps.lg}>
        <CardWrapper>
          <MuiCardContent
            style={{
              padding: "10px",
              paddingTop: topPadding,
              paddingLeft: "30px",
              paddingBottom: bottomPadding,
              paddingRight: "30px",
            }}
          >
            {headerExists && (
              <Typography style={{ position: "relative", height: "20px" }} gutterBottom variant="h5" component="div">
                {img && (
                  <IconWrapper>
                    <content.img style={{ fontSize: "40px" }} />
                  </IconWrapper>
                )}
                {title && <CardTitle>{title}</CardTitle>}
              </Typography>
            )}
            {description && (
              <Typography component="div" style={{ marginTop: topMargin }}>
                <div dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(description) }}></div>
              </Typography>
            )}
          </MuiCardContent>
        </CardWrapper>
      </GridItem>
    </>
  );
};

export default MessageCardContent;
