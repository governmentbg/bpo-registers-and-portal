import { Card, CardContent as MuiCardContent, Typography } from "@mui/material";
import styled from "styled-components";
import DOMPurify from "dompurify";
import { AsyncCallArgs, BoxSpg, GridItem, iTxt, SvgIcon, useAsyncCall } from "@duosoftbg/bpo-components";
import i18n from "i18next";
import React from "react";
import { UserGuidesType } from "../../../types/userGuides/userGuidesTypes";
import FileCopyIcon from "@mui/icons-material/FileCopy";
import { getUserGuidesFileDataById } from "../../../axios/api/services";

const IconWrapper = styled.span`
  display: flex;
  align-items: center;

  svg {
    width: 30px;
    height: 30px;
    color: ${(props) => (props.theme.palette.mode === "dark" ? "#87aece" : "#059")};
  }
`;

const CardWrapper = styled(Card)`
  height: 100%;
  cursor: pointer;

  &:hover {
    background: ${(props) => (props.theme.palette.mode === "dark" ? "rgba(6, 78, 148, 0.3)" : "#eee")};
  }
`;

const downloadFile = (attachmentId, asyncCall) => {
  const asyncCreation: AsyncCallArgs = {
    withGlobalBackdrop: true,
    promise: getUserGuidesFileDataById(attachmentId),
    onSuccess: (response) => {
      const blob = new Blob([response.data], {
        type: response?.headers?.["content-type"],
      });
      const objectUrl = URL.createObjectURL(blob);
      const downloadLink = document.createElement("a");
      downloadLink.href = objectUrl;
      downloadLink.download = response.headers["x-file-name"] || "downloaded-file";
      document.body.appendChild(downloadLink);
      downloadLink.click();
      document.body.removeChild(downloadLink);
    },
  };
  asyncCall(asyncCreation);
};

const UserGuidesCardContent = (content: UserGuidesType) => {
  const { asyncCall } = useAsyncCall();
  const title = iTxt(i18n.language, content.labelBg, content.labelEn, true);
  const description = iTxt(i18n.language, content.descriptionBg, content.descriptionEn, true);

  return (
    <GridItem xs={12} sm={12} md={6} lg={12}>
      <CardWrapper>
        <MuiCardContent style={{ padding: "30px" }} onClick={() => downloadFile(content.id, asyncCall)}>
          <BoxSpg
            sx={{
              display: "flex",
              alignItems: "center",
              gap: 1.5,
              marginBottom: "10px",
            }}
          >
            <IconWrapper>
              {content.image ? <SvgIcon img={content.image} /> : <FileCopyIcon sx={{ fontSize: 30 }} />}
            </IconWrapper>

            <Typography variant="h5" component="div" sx={{ fontWeight: "bold", flex: 1 }}>
              {title}
            </Typography>
          </BoxSpg>

          {description && (
            <Typography component="div" sx={{ marginTop: "15px" }}>
              <div
                dangerouslySetInnerHTML={{
                  __html: DOMPurify.sanitize(description),
                }}
              ></div>
            </Typography>
          )}
        </MuiCardContent>
      </CardWrapper>
    </GridItem>
  );
};

export default UserGuidesCardContent;
