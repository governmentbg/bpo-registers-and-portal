import { Box, Typography } from "@mui/material";
import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClose, faSearch } from "@fortawesome/free-solid-svg-icons";
import { useTranslation } from "react-i18next";
import { GridContainer, GridItem, MediumButton, MediumGreyButton } from "@duosoftbg/bpo-components";

const FilterButtons = ({ control }) => {
  const { t } = useTranslation();

  return (
    <Box>
      <GridContainer spacing={3} mt={0}>
        <GridItem sm={12} md={12}>
          <div className={"docx-hidable"}>
            <Typography align={"right"}>
              <MediumButton
                startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faSearch} />}
                size={"small"}
                disabled={control.isSubmitBtnDisabled}
                type={"submit"}
                variant="contained"
                color="primary"
              >
                {t("l.btn.search")}
              </MediumButton>
              <MediumGreyButton
                size={"small"}
                startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faClose} />}
                disabled={control.isResetBtnDisabled || control.isSubmitBtnDisabled}
                onClick={control.handleResetFilters}
                style={{ marginLeft: "15px" }}
                variant="contained"
              >
                {t("l.btn.clear")}
              </MediumGreyButton>
            </Typography>
          </div>
        </GridItem>
      </GridContainer>
    </Box>
  );
};
export default FilterButtons;
