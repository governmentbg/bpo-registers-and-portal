import {
  BlockText,
  DividerSpg,
  GridContainer,
  isEmpty,
  isNotEmpty,
  ProcessEnvironments,
} from "@duosoftbg/bpo-components";
import React, { Fragment } from "react";
import { Grid, IconButton } from "@mui/material";
import { PictureAsPdf } from "@mui/icons-material";
import { useTranslation } from "react-i18next";

const PublicationDataPanel = ({ data }) => {
  const { t } = useTranslation();
  return (
    <div className={"publication-data-panel"}>
      {data?.ipObject?.publications.map((x, index) => (
        <Fragment key={x?.id}>
          {index !== 0 && <DividerSpg />}
          <GridContainer spacing={3}>
            {isEmpty(x?.journalNbr) && (
              <BlockText
                propsGrid={{ xs: 10, sm: 10, md: 3, lg: 3 }}
                withGrid
                label={`l.publication.journal`}
                text={x?.publicationNumber + "/" + x?.publicationYear}
              />
            )}
            {isNotEmpty(x?.journalNbr) && (
              <BlockText
                propsGrid={{ xs: 10, sm: 10, md: 3, lg: 3 }}
                withGrid
                label={`l.publication.journal`}
                text={x?.publicationNumber + "/" + x?.publicationYear}
                href={
                  x?.isPublic
                    ? `${ProcessEnvironments.JournalUrl}journal/view/element/${x?.journalNbr}?node=${x?.journalStructNodeNbr}`
                    : undefined
                }
              />
            )}
            <BlockText
              propsGrid={{ xs: 12, sm: 12, md: 4, lg: 4 }}
              withGrid
              label={`l.publication.date`}
              text={x?.publicationDate}
            />
            <BlockText
              propsGrid={{ xs: 12, sm: 12, md: 4, lg: 4 }}
              withGrid
              label={`l.publication.section`}
              text={x?.publicationSection?.nmsection}
            />
            {(data?.ipObject?.id || data?.ipObject?.registrationNumber) && x?.elementNbr && x?.isPublic && (
              <Grid item xs={2} sm={2} md={1} lg={1}>
                <IconButton
                  className={"docx-hidable"}
                  size="large"
                  color={"primary"}
                  title={t("l.display.pdf")}
                  onClick={() =>
                    window.open(
                      `${ProcessEnvironments.JournalUrl}admin/downloadFile?regNum=${
                        data?.ipObject?.registrationNumber ? data?.ipObject?.registrationNumber : ""
                      }&appNum=${data?.ipObject?.id ? data?.ipObject?.id : ""}&elementNumber=${x?.elementNbr}`,
                      "_blank"
                    )
                  }
                >
                  <PictureAsPdf />
                </IconButton>
              </Grid>
            )}
          </GridContainer>
        </Fragment>
      ))}
    </div>
  );
};
export default PublicationDataPanel;
