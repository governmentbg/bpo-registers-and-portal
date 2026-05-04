import React, { useEffect, useState } from "react";
import { downloadFile } from "../../../../../../axios/api/services";
import { AsyncCallArgs, BlockText, BoxSpg, DividerSpg, iTxt, useAsyncCall } from "@duosoftbg/bpo-components";
import { MarkKind, RegisterType } from "../../../../../../utils/constants";
import i18n from "i18next";
import { useTranslation } from "react-i18next";
import { CardMedia, Tooltip, Typography } from "@mui/material";
import styled from "@mui/system/styled";
import BrokenImage from "@mui/icons-material/BrokenImage";
import { arrayContainsArray } from "../../../../../../utils/functions";

const Image = styled(BoxSpg)<{ src?: string }>`
  width: 50%;
  height: auto;
  display: block;
  transition: width 0.3s ease;

  &:hover {
    width: 100%;
  }
`;

const MediaPanel = ({ registerType, data = null, attachments, types }) => {
  const { t } = useTranslation();
  return (
    <div className={"media-data-panel"}>
      {attachments
        ?.filter((x) =>
          arrayContainsArray(
            types,
            x?.attachment?.attachmentType?.categories?.map((x) => x?.id)
          )
        )
        ?.map((x, index) => (
          <div key={"media-frag-" + index} style={{ display: "block", width: "50%" }}>
            {index !== 0 && <DividerSpg />}
            <MediaPart
              attachment={x?.attachment}
              attachmentType={x?.attachment?.attachmentType?.id}
              hasLabel={registerType === RegisterType.MARK}
              label={`l.mark.image.inid.code.${x?.attachment?.attachmentType?.id}`}
            />
          </div>
        ))}
      {registerType === RegisterType.MARK && (
        <BlockText
          propsGrid={{ xs: 12, sm: 12, md: 12, lg: 12 }}
          withGrid
          label={data?.markKind?.id === MarkKind.D ? "" : t("l.word.elements")}
          text={iTxt(
            i18n.language,
            data?.ipObject?.title,
            data?.markTranslation && data?.markTranslation !== ""
              ? data?.markTranslation
              : data?.markTransliteration && data?.markTransliteration !== ""
              ? data?.markTransliteration
              : data?.ipObject?.title
          )}
        />
      )}
    </div>
  );
};

export const MediaPart = ({ attachment, attachmentType, hasLabel = false, label = undefined }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();

  const [error, setError] = useState(false);
  const [media, setMedia] = useState(null);

  const handleFileDownload = (bucketName, fileLocation) => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: downloadFile(bucketName, fileLocation),
      commonErrorMessage: "m.error.load.media",
      onSuccess: (response) => {
        setMedia(response.data);
      },
      onError: (response) => {
        setError(true);
      },
    };
    asyncCall(asyncCallArgs);
  };

  useEffect(() => {
    handleFileDownload(attachment?.bucketName, attachment?.fileLocation);
    // eslint-disable-next-line
  }, [attachment]);

  if (attachmentType === "IMAGE") {
    return (
      <div className={"block-image"}>
        {hasLabel && (
          <Typography variant="body2" component={"div"} fontWeight={"fontWeightBold"} className={"block-image-label"}>
            {t(label)}
          </Typography>
        )}
        {media && (
          <div className={"block-image-image"}>
            <Image
              key={"img-" + Math.random()}
              src={URL.createObjectURL(media)}
              loading="lazy"
              alt={"drawing"}
              component={"img"}
            />
          </div>
        )}
        {error && (
          <Tooltip title={t("m.error.load.media")} placement={"right"}>
            <BrokenImage fontSize={"large"} />
          </Tooltip>
        )}
      </div>
    );
  } else if (attachmentType === "VIDEO") {
    return (
      <>
        {hasLabel && (
          <Typography variant="body2" component={"div"} fontWeight={"fontWeightBold"}>
            {t(label)}
          </Typography>
        )}
        {media && attachmentType === "VIDEO" && (
          <>
            <CardMedia
              component="video"
              controls
              src={URL.createObjectURL(media)}
              autoPlay={false}
              style={{ maxWidth: "100%", maxHeight: "500px" }}
            />
            <div style={{ display: "none" }}>
              {/* for docx generation*/}
              <BlockText label={"l.format"} text={t("l.video")} />
            </div>
          </>
        )}
        {error && (
          <Tooltip title={t("m.error.load.media")} placement={"right"}>
            <BrokenImage fontSize={"large"} />
          </Tooltip>
        )}
      </>
    );
  } else if (attachmentType === "AUDIO") {
    return (
      <>
        {hasLabel && (
          <Typography variant="body2" component={"div"} fontWeight={"fontWeightBold"}>
            {t(label)}
          </Typography>
        )}
        {media && attachmentType === "AUDIO" && (
          <>
            <CardMedia
              component="audio"
              controls
              src={URL.createObjectURL(media)}
              autoPlay={false}
              style={{ maxWidth: "100%", maxHeight: "500px" }}
            />
            <div style={{ display: "none" }}>
              {/* for docx generation*/}
              <BlockText label={"l.format"} text={t("l.audio")} />
            </div>
          </>
        )}
        {error && (
          <Tooltip title={t("m.error.load.media")} placement={"right"}>
            <BrokenImage fontSize={"large"} />
          </Tooltip>
        )}
      </>
    );
  } else {
    return null;
  }
};
export default MediaPanel;
