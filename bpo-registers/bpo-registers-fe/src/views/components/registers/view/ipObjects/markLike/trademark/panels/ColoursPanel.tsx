import { BlockText, GridContainer } from "@duosoftbg/bpo-components";
import React from "react";
import { useTranslation } from "react-i18next";

const ColoursPanel = ({ colours }) => {
  const { t } = useTranslation();
  return (
    <div className={"colours-data-panel"}>
      <GridContainer spacing={3}>
        <BlockText
          propsGrid={{ xs: 12, sm: 12, md: 12, lg: 12 }}
          withGrid
          label={""}
          text={
            colours && colours.length > 0 && colours.toString() ? colours?.join("; ") : t("l.according.representation")
          }
        />
      </GridContainer>
    </div>
  );
};
export default ColoursPanel;
