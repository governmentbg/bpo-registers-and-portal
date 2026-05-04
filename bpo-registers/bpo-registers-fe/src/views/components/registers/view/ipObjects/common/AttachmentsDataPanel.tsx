import { BlockText, DividerSpg, GridContainer, iTxt, useAsyncCall } from "@duosoftbg/bpo-components";
import React, { Fragment } from "react";
import i18n from "i18next";
import { arrayContainsArray, handleFileDownload } from "../../../../../../utils/functions";

const AttachmentsDataPanel = ({ data, types }) => {
  const { asyncCall } = useAsyncCall();

  return (
    <div className={"attachments-data-panel"}>
      {data?.ipObject?.attachments
        ?.filter((x) =>
          arrayContainsArray(
            types,
            x?.attachment?.attachmentType?.categories?.map((x) => x?.id)
          )
        )
        ?.map((x, index) => (
          <Fragment key={x?.id?.objectId + "-" + x?.id?.sequenceNumber}>
            {index !== 0 && <DividerSpg />}
            <GridContainer spacing={3}>
              <BlockText
                propsGrid={{ xs: 12, sm: 12, md: 6, lg: 6 }}
                withGrid
                label={`l.attachment.attachmentType`}
                text={iTxt(i18n.language, x?.attachment?.attachmentType?.name, x?.attachment?.attachmentType?.nameEn)}
              />
              <BlockText
                propsGrid={{ xs: 12, sm: 12, md: 6, lg: 6 }}
                withGrid
                label={`l.attachment.download`}
                text={x?.attachment?.fileName}
                onClickFn={() =>
                  handleFileDownload(
                    x?.attachment?.bucketName,
                    x?.attachment?.fileLocation,
                    x?.attachment?.fileName,
                    asyncCall
                  )
                }
              />
            </GridContainer>
          </Fragment>
        ))}
    </div>
  );
};
export default AttachmentsDataPanel;
