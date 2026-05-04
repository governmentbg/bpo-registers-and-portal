import { BoxSpg, GridItem, isEmpty } from "@duosoftbg/bpo-components";
import styled from "styled-components";
import { Box, Grid } from "@mui/material";
import { useTranslation } from "react-i18next";
import { Clear, Done } from "@mui/icons-material";
import React, { ReactElement } from "react";

const Text = styled(Box)`
  margin-right: 6px;
  font-size: 0.875rem;
`;

export const IconWrapper = styled.span`
  svg {
    width: 15px;
    vertical-align: middle;
    color: ${(props) => props.theme.formFields.iconColor};
  }
`;

const Label = styled(Box)`
  font-size: 0.95rem;
  color: ${(props) => props.theme.palette.body.color};
`;

type HistoryBlockTextProps = {
  label?: string;
  startIcon?: React.ReactElement;
  newText: string;
  oldText: string;
  historyArrayMatch?: boolean;
  withGrid?: boolean;
  propsGrid?: {
    xs?: number;
    sm?: number;
    md?: number;
    lg?: number;
  };
};

const HistoryBlockText = ({
  label,
  startIcon,
  newText,
  oldText,
  historyArrayMatch = false,
  withGrid = false,
  propsGrid = { xs: 12, sm: 12, md: 4, lg: 4 },
}: HistoryBlockTextProps) => {
  const isOldEmpty = isEmpty(oldText);
  const isNewEmpty = isEmpty(newText);

  if (isOldEmpty && isNewEmpty) {
    return <></>;
  }

  return (
    <HistoryTextGridWrapper
      label={label}
      startIcon={startIcon}
      newText={newText}
      oldText={newText === oldText || historyArrayMatch ? null : oldText}
      withGrid={withGrid}
      propsGrid={propsGrid}
    />
  );
};

const HistoryTextGridWrapper = ({ label, startIcon, newText, oldText, withGrid, propsGrid }) => {
  if (withGrid) {
    return (
      <GridItem xs={propsGrid.xs} sm={propsGrid.sm} md={propsGrid.md} lg={propsGrid.lg}>
        <HistoryText label={label} startIcon={startIcon} newText={newText} oldText={oldText} />
      </GridItem>
    );
  }
  return <HistoryText label={label} startIcon={startIcon} newText={newText} oldText={oldText} />;
};

const HistoryText = ({
  label,
  startIcon,
  newText,
  oldText,
}: {
  label: string;
  startIcon: ReactElement;
  newText: string;
  oldText: string;
}) => {
  const { t } = useTranslation();

  return (
    <>
      {label && <Label fontWeight="fontWeightBold">{t(label)}</Label>}

      <Grid container wrap="nowrap" spacing={2}>
        {startIcon && (
          <Grid item>
            <IconWrapper>{startIcon}</IconWrapper>
          </Grid>
        )}
        <Grid item>
          {newText && (
            <BoxSpg>
              <Text component={"span"}>{newText}</Text>
              {oldText && (
                <IconWrapper>
                  <Done style={{ color: "green" }} />
                </IconWrapper>
              )}
            </BoxSpg>
          )}
          {oldText && (
            <BoxSpg>
              <Text component={"span"}>{oldText}</Text>
              <IconWrapper>
                <Clear style={{ color: "red" }} />
              </IconWrapper>
            </BoxSpg>
          )}
        </Grid>
      </Grid>
    </>
  );
};

export default HistoryBlockText;
