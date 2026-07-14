import { BlockText, DividerSpg, GridContainer, GridItem, iTxt } from "@duosoftbg/bpo-components";
import React, { Fragment, useRef } from "react";
import i18n from "i18next";
import { MediaPart } from "../../../common/MediaPanel";
import { Accordion, AccordionDetails, AccordionSummary, Button, Typography } from "@mui/material";
import { AllOut } from "@mui/icons-material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMaximize, faMinimize } from "@fortawesome/free-solid-svg-icons";
import { useTranslation } from "react-i18next";

const SingleDesignsDataPanel = ({ data }) => {
  const { t } = useTranslation();
  const accordionRefs = useRef([]);

  const handleToggleAll = (open) => {
    accordionRefs.current.forEach((accordion) => {
      if (accordion) {
        const isExpanded = accordion.getAttribute("aria-expanded") === "true";
        if (open && !isExpanded) {
          accordion.click();
        } else if (!open && isExpanded) {
          accordion.click();
        }
      }
    });
  };

  return (
    <div className={"single-designs-data-panel"}>
      <div style={{ marginBottom: "16px" }}>
        <Button
          className={"docx-hidable"}
          variant="text"
          component="span"
          startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faMaximize} />}
          onClick={() => handleToggleAll(true)}
        >
          {t("l.btn.expand.all")}
        </Button>
        <Button
          className={"docx-hidable"}
          variant="text"
          component="span"
          startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faMinimize} />}
          onClick={() => handleToggleAll(false)}
        >
          {t("l.btn.collapse.all")}
        </Button>
      </div>

      {data?.singleDesigns?.map((x, index) => (
        <div className={"docx-element-divider docx-element-divider-" + index} key={"docx-element-divider-" + index}>
          <GridItem sm={12} md={12} xs={12} key={"acc-grid-" + index}>
            <Accordion key={"acc-" + index} defaultExpanded>
              <AccordionSummary
                ref={(el) => (accordionRefs.current[index] = el)}
                key={"acs-" + index}
                expandIcon={<AllOut />}
                sx={{
                  "&:hover": {
                    backgroundColor: "rgba(0, 0, 0, 0.04)",
                    cursor: "pointer",
                  },
                }}
              >
                <Typography variant="h5" component={"div"} fontWeight={"fontWeightBold"} className={"docx-subheading"}>
                  {iTxt(i18n.language, x?.name, x?.nameEn) +
                    " [" +
                    x?.alternateKey +
                    "] " +
                    iTxt(i18n.language, x?.status?.bpoOnlineStatus, x?.designType?.bpoOnlineStatusEn)}
                </Typography>
              </AccordionSummary>
              <AccordionDetails key={"acd-" + index}>
                <Fragment key={x?.id}>
                  <GridContainer spacing={3}>
                    <BlockText
                      propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                      withGrid
                      label={"l.single.design.identifier"}
                      text={x?.id}
                    />
                    <BlockText
                      propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                      withGrid
                      label={"l.single.design.alternateKey"}
                      text={x?.alternateKey}
                    />
                    <BlockText
                      propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                      withGrid
                      label={"l.single.design.designType"}
                      text={iTxt(i18n.language, x?.designType?.description, x?.designType?.descriptionEn)}
                    />
                    <BlockText
                      propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                      withGrid
                      label={"l.single.design.status"}
                      text={iTxt(i18n.language, x?.status?.bpoOnlineStatus, x?.designType?.bpoOnlineStatusEn)}
                    />
                    <BlockText
                      propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                      withGrid
                      label={"l.single.design.name"}
                      text={x?.name}
                    />
                    <BlockText
                      propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                      withGrid
                      label={"l.single.design.nameEn"}
                      text={x?.nameEn}
                    />
                    <BlockText
                      propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                      withGrid
                      label={"l.single.design.locarno"}
                      text={x?.locarnos?.map((z) => z?.id?.locarnoClassCode)?.join("; ")}
                    />
                    <BlockText
                      propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                      withGrid
                      label={"l.single.design.verbal.element"}
                      text={x?.verbalElement}
                    />
                    <BlockText
                      propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                      withGrid
                      label={"l.single.design.verbal.element.en"}
                      text={x?.verbalElementEn}
                    />
                    <GridItem md={12} sm={12} xs={12} pt={12} pr={12}>
                      <GridContainer>
                        {x?.drawings?.map((y, index) => (
                          <GridItem xs={12} sm={12} md={6} lg={5} key={"img-grid-" + index}>
                            <MediaPart
                              attachment={y?.attachment}
                              attachmentType={y?.attachment?.attachmentType?.id}
                              hasLabel={true}
                              label={y?.viewType?.viewTypeName}
                            />
                          </GridItem>
                        ))}
                      </GridContainer>
                    </GridItem>
                  </GridContainer>
                </Fragment>
              </AccordionDetails>
            </Accordion>
            <DividerSpg />
          </GridItem>
        </div>
      ))}
    </div>
  );
};
export default SingleDesignsDataPanel;
