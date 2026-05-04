import { BlockText, DividerSpg, GridContainer, isEmpty, iTxt } from "@duosoftbg/bpo-components";
import React, { Fragment } from "react";
import i18n from "i18next";
import { getRegisterTypeByObjectType, RelationshipType } from "../../../../../../utils/constants";
import { generateUrl } from "../../../../../../utils/urls";
import { useTranslation } from "react-i18next";
import ParsedTextWithLinkBlock from "../../../../common/text/ParsedTextWithLinkBlock";

const RelatedObjectsDataPanel = ({ data, registerType = undefined }) => {
  const { t } = useTranslation();

  const generateAdditionalDataBlockText = (x) => {
    switch (x?.id?.relationshipType + "_" + x?.id?.applicationType) {
      case RelationshipType.TM_WO:
        return (
          <BlockText
            propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
            withGrid
            label={`l.related.object.additional.data`}
            textArray={[
              x?.registrationNumber ? t("l.related.object.registration.number") + ": " + x?.registrationNumber : "",
              x?.priorityDate ? t("l.related.object.priority.date") + ": " + x?.priorityDate : "",
            ]}
            text={" "}
          />
        );
      case RelationshipType.TM_EM:
        return (
          <BlockText
            propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
            withGrid
            label={`l.related.object.additional.data`}
            textArray={[
              x?.registrationNumber ? t("l.related.object.registration.number") + ": " + x?.registrationNumber : "",
              x?.priorityDate ? t("l.related.object.priority.date") + ": " + x?.priorityDate : "",
              x?.serveMessageDate ? t("l.related.object.serve.message.date") + ": " + x?.serveMessageDate : "",
            ]}
            text={" "}
          />
        );
      // default:
      //   return (
      //     <BlockText
      //       propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
      //       withGrid
      //       label={`l.related.object.additional.data`}
      //       textArray={[
      //         x?.registrationNumber ? t("l.related.object.registration.number") + ": " + x?.registrationNumber : "",
      //         x?.registrationDate ? t("l.related.object.registration.date") + ": " + x?.registrationDate : "",
      //         x?.priorityDate ? t("l.related.object.priority.date") + ": " + x?.priorityDate : "",
      //         x?.serveMessageDate ? t("l.related.object.serve.message.date") + ": " + x?.serveMessageDate : "",
      //         x?.cancellationDate ? t("l.related.object.cancellation.date") + ": " + x?.cancellationDate : "",
      //       ]}
      //       text={" "}
      //     />
      //   );
    }
  };

  return (
    <div className={"related-objects-data-panel"}>
      {data?.relationshipsView?.map((x, index) => (
        <Fragment key={x?.id?.objectId + "-" + x?.id?.relationshipType + "-" + x?.id?.objectReference}>
          {index !== 0 && <DividerSpg />}
          <GridContainer spacing={3}>
            {x?.relationshipName && (
              <BlockText
                propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                withGrid
                label={`l.related.object.relationshipType`}
                text={iTxt(i18n.language, x?.relationshipName, x?.relationshipNameEn)}
              />
            )}
            <DescriptionField relationship={x} registerType={registerType} />
            {x?.id?.objectReference && x?.id?.objectReference !== "NONE" && (
              <BlockText
                propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                withGrid
                label={`l.related.object.link`}
                text={x?.id?.objectReference}
                href={generateUrl(
                  getRegisterTypeByObjectType(x?.id?.objectReference?.split("/")[1]),
                  x?.id?.objectReference?.replaceAll("/", "_"),
                  "view",
                  true
                )}
              />
            )}
            {generateAdditionalDataBlockText(x)}
          </GridContainer>
        </Fragment>
      ))}
    </div>
  );
};

const DescriptionField = ({ relationship, registerType }) => {
  const description = relationship?.id?.description;

  if (isEmpty(description)) {
    return null;
  }

  switch (relationship?.id?.relationshipType + "_" + relationship?.id?.applicationType) {
    case RelationshipType.SEN_NONE:
      return (
        <ParsedTextWithLinkBlock
          label={iTxt(i18n.language, relationship?.relationshipName, relationship?.relationshipNameEn)}
          text={iTxt(i18n.language, description, relationship?.id?.descriptionEn)}
          hrefBase={"https://euipo.europa.eu/eSearch/#details/trademarks/"}
        />
      );
    default:
      return (
        <BlockText
          propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
          withGrid
          label={`l.related.object.description.${registerType}`}
          text={iTxt(i18n.language, description, relationship?.id?.descriptionEn)}
        />
      );
  }
};
export default RelatedObjectsDataPanel;
