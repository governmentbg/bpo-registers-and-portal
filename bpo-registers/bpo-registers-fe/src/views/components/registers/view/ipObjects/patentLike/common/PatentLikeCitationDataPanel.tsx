import { BlockText, DividerSpg, GridContainer } from "@duosoftbg/bpo-components";
import React, { Fragment } from "react";

const PatentLikeCitationDataPanel = ({ data }) => {
  return (
    <div className={"citation-data-panel"}>
      {data?.citations.map((x, index) => (
        <Fragment key={x?.id?.patentId + "-" + x?.id?.refNumber}>
          {index !== 0 && <DividerSpg />}
          <GridContainer spacing={3}>
            <BlockText
              propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
              withGrid
              label={`l.citation.refNbr`}
              text={x?.id?.refNumber}
            />
            <BlockText
              propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
              withGrid
              label={`l.citation.refDescription`}
              text={x?.refDescription}
            />
            <BlockText
              propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
              withGrid
              label={`l.citation.refClaims`}
              text={x?.refClaims}
            />
          </GridContainer>
        </Fragment>
      ))}
    </div>
  );
};
export default PatentLikeCitationDataPanel;
