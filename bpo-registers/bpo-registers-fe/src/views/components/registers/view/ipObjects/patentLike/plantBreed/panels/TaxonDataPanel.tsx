import { BlockText, GridContainer } from "@duosoftbg/bpo-components";
import React from "react";

const TaxonDataPanel = ({ data }) => {
  return (
    <div className={"taxon-data-panel"}>
      <GridContainer spacing={3}>
        <BlockText
          propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
          withGrid
          label={`l.taxon.taxonCode`}
          text={data?.taxon?.taxonCode}
        />
        <BlockText
          propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
          withGrid
          label={`l.taxon.commonClassifyBg`}
          text={data?.taxon?.commonClassifyBg}
        />
        <BlockText
          propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
          withGrid
          label={`l.taxon.latinClassify`}
          text={data?.taxon?.latinClassify}
        />
      </GridContainer>
    </div>
  );
};
export default TaxonDataPanel;
