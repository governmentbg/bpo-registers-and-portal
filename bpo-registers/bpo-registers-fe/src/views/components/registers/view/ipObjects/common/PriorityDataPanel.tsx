import { BlockText, DividerSpg, GridContainer, GridItem } from "@duosoftbg/bpo-components";
import React, { Fragment } from "react";
import { Typography } from "@mui/material";

const PriorityDataPanel = ({ data, registerType = undefined, t }) => {
  const priorities = data?.ipObject?.priorities?.filter((x) => x?.status === 1);
  return (
    <div className={"priority-data-panel"}>
      <GridContainer spacing={3}>
        <GridItem xs={12} sm={12} md={12}>
          <Typography gutterBottom variant="h5" component="h5" color="black" mt={4}>
            {t(`l.panel.priorityData.${registerType}`)}
          </Typography>
          <DividerSpg mb={2} mt={4} />
        </GridItem>
        {priorities && priorities?.length > 0 && (
          <>
            {priorities.map((x, index) => (
              <Fragment key={x?.id}>
                {index !== 0 && (
                  <GridItem xs={12} sm={12} md={12}>
                    <DividerSpg />
                  </GridItem>
                )}
                {x.country?.id && (
                  <BlockText
                    propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                    withGrid
                    label={`l.priority.country.${registerType}`}
                    text={x?.country?.name + " (" + x.country?.id + ")"}
                  />
                )}
                <BlockText
                  propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                  withGrid
                  label={`l.priority.number.${registerType}`}
                  text={x?.priorityNumber}
                />
                <BlockText
                  propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                  withGrid
                  label={`l.priority.date.${registerType}`}
                  text={x?.priorityDate}
                />
              </Fragment>
            ))}
          </>
        )}
        {!priorities ||
          (priorities?.length === 0 && (
            <GridItem xs={12} sm={12} md={12}>
              <Typography>{t("l.none")}</Typography>
            </GridItem>
          ))}
      </GridContainer>
    </div>
  );
};
export default PriorityDataPanel;
