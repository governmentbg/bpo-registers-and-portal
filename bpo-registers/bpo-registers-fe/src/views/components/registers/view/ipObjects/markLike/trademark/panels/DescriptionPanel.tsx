import { BlockText, GridContainer } from "@duosoftbg/bpo-components";
import React from "react";
import { useTranslation } from "react-i18next";

const DescriptionPanel = ({ description }) => {
  const { t } = useTranslation();
  return (
    <div className={"description-data-panel"}>
      <GridContainer spacing={3}>
        <BlockText
          propsGrid={{ xs: 12, sm: 12, md: 12, lg: 12 }}
          withGrid
          label={""}
          text={description ? description : t("l.according.representation")}
        />
      </GridContainer>
    </div>
  );
};
export default DescriptionPanel;
