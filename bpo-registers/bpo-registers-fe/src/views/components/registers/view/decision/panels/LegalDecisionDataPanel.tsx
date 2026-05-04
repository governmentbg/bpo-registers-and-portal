import { BlockText, GridContainer, iTxt, useAsyncCall } from "@duosoftbg/bpo-components";
import React from "react";
import i18n from "i18next";
import { generateUrl } from "../../../../../../utils/urls";
import { getRegisterTypeByObjectType } from "../../../../../../utils/constants";
import { handleFileDownload } from "../../../../../../utils/functions";

const LegalDecisionDataPanel = ({ data }) => {
  const { asyncCall } = useAsyncCall();

  return (
    <>
      <GridContainer spacing={3}>
        <BlockText
          propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
          withGrid
          label={`l.attachment.download`}
          text={data?.attachment?.fileName}
          onClickFn={() =>
            handleFileDownload(
              data?.attachment?.bucketName,
              data?.attachment?.fileLocation,
              data?.attachment?.fileName,
              asyncCall
            )
          }
        />
        <BlockText
          propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
          withGrid
          label={`l.objectId`}
          text={data?.objectId}
          href={generateUrl(
            getRegisterTypeByObjectType(data?.objectId?.split("/")[1]),
            data?.objectId?.replaceAll("/", "_"),
            "view",
            true
          )}
        />
        <BlockText
          propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
          withGrid
          label={`l.objectType`}
          text={iTxt(i18n.language, data?.objectType?.description, data?.objectType?.descriptionEn)}
        />
        <BlockText
          propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
          withGrid
          label={`l.documentNumber`}
          text={data?.documentNumber}
        />
        <BlockText
          propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
          withGrid
          label={`l.documentDate`}
          text={data?.documentDate}
        />
        <BlockText propsGrid={{ xs: 12, sm: 12, md: 12, lg: 12 }} withGrid label={`l.title`} text={data?.title} />
        <BlockText
          propsGrid={{ xs: 12, sm: 12, md: 12, lg: 12 }}
          withGrid
          label={`l.documentType`}
          text={iTxt(i18n.language, data?.documentType?.name, data?.documentType?.nameEn)}
        />
        <BlockText
          propsGrid={{ xs: 12, sm: 12, md: 12, lg: 12 }}
          withGrid
          label={`l.legalGroundTypes`}
          textArray={data?.legalGroundTypes?.map((x) => iTxt(i18n.language, x?.name, x?.nameEn))}
          text={data?.legalGroundTypes?.length > 0 ? " " : ""}
        />
      </GridContainer>
    </>
  );
};
export default LegalDecisionDataPanel;
