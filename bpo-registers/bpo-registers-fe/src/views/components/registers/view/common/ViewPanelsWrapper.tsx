import React, { useState } from "react";
import {
  BoxSpg,
  CardSpg,
  DividerSpg,
  GridContainer,
  GridItem,
  useEffectSkipFirstRender,
} from "@duosoftbg/bpo-components";
import { CardContent, Collapse, IconButton } from "@mui/material";
import Typography from "@mui/material/Typography";
import { useTranslation } from "react-i18next";
import styled from "styled-components/macro";
import { KeyboardArrowDown, KeyboardArrowUp } from "@mui/icons-material";
import { useSelector } from "react-redux";
import { GlobalPanelControl } from "../../../../../utils/constants";
import useAppDispatch from "../../../../../hooks/redux/base/useAppDispatch";
import { GlobalPanelsControlActions } from "../../../../../store/redux/slice/ComponentsControl/globalPanelsControl";

const PanelTitle = styled(Typography)<{
  component?: string;
  $empty?: boolean;
}>`
  color: ${(props) => (props.$empty && props.$empty ? "#8a8a8a" : props.theme.palette.primary.light)};
  position: relative;
`;

const PanelTitleSection = styled.div<{ $empty?: boolean }>`
  width: 100%;
  user-select: none;
  position: relative;
  cursor: ${(props) => (props.$empty ? "default" : "pointer")};
`;

const ViewPanelsWrapper = ({ children, title, emptyPanel = false }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const [open, setOpen] = useState(true);

  const globalOpen = useSelector((state) => {
    return state["ComponentsControl"].globalPanelsControl.open;
  });

  useEffectSkipFirstRender(() => {
    if (GlobalPanelControl.MANUAL !== globalOpen) {
      setOpen(GlobalPanelControl.OPEN === globalOpen);
    }
  }, [globalOpen]);

  const handleManualPanelToggle = () => {
    if (!emptyPanel) {
      dispatch(GlobalPanelsControlActions.manual());
      setOpen(!open);
    }
  };

  return (
    <BoxSpg>
      <CardSpg style={{ minHeight: 50 }} mb={5}>
        <CardContent style={{ padding: 24 }}>
          <PanelTitleSection $empty={emptyPanel} onClick={handleManualPanelToggle}>
            <GridContainer>
              <GridItem xs={11} sm={9} md={9}>
                <PanelTitle gutterBottom variant="h4" component="h4" $empty={emptyPanel}>
                  {t(title)}
                </PanelTitle>
              </GridItem>

              {!emptyPanel && (
                <GridItem xs={1} sm={3} md={3} style={{ textAlign: "right" }}>
                  <IconButton style={{ marginBottom: 5 }} size="small">
                    {open ? <KeyboardArrowUp /> : <KeyboardArrowDown />}
                  </IconButton>
                </GridItem>
              )}
              {emptyPanel && (
                <GridItem xs={12} sm={3} md={3} style={{ textAlign: "right" }}>
                  <Typography gutterBottom variant="body2" component={"h6"} color={"#8a8a8a"}>
                    {t("l.panel.no.data")}
                  </Typography>
                </GridItem>
              )}
            </GridContainer>
          </PanelTitleSection>

          {!emptyPanel && (
            <Collapse in={open}>
              <DividerSpg mb={4} />
              <BoxSpg>{children}</BoxSpg>
            </Collapse>
          )}
        </CardContent>
      </CardSpg>
    </BoxSpg>
  );
};

export default ViewPanelsWrapper;
