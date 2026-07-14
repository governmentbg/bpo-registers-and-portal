import { useTranslation } from "react-i18next";

import React, { useEffect, useState } from "react";
import { Alert, Chip, Typography } from "@mui/material";
import { NiceClassesSearchFilterControlActions } from "../../../../../../../store/redux/slice/ComponentsControl/niceClassesSearchFilterControl";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import {
  AsyncCallArgs,
  BlockText,
  CircularLoader,
  GridContainer,
  GridItem,
  isArrayEmpty,
  isNotEmpty,
  MediumButton,
  MediumGreyButton,
  TempFormDataActions,
  TextButton,
  useAsyncCall,
  ViewDialog,
} from "@duosoftbg/bpo-components";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { getNiceClasses } from "../../../../../../../axios/api/services";
import styled from "styled-components";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheck, faClose } from "@fortawesome/free-solid-svg-icons";
import { Add, Remove } from "@mui/icons-material";

const NiceClassChip = styled(Chip)`
  .MuiChip-label {
    width: 50px;
    text-align: center;
  }
`;

const NiceClassSearchFilterListDialog = () => {
  const dispatch = useAppDispatch();
  const { asyncCall } = useAsyncCall();

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [niceClasses, setNiceClasses] = useState(null);

  const { open, niceClassCodes, onlyGoods } = useAppSelector((state) => {
    return state.ComponentsControl.niceClassesSearchFilterControl.modals.editList;
  });

  useEffect(() => {
    if (open && isArrayEmpty(niceClasses)) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: getNiceClasses(onlyGoods),
        onSuccess: (response) => {
          setNiceClasses(response);
          setLoading(false);
        },
        onError: () => {
          setError(true);
          setLoading(false);
        },
      };
      asyncCall(asyncCallArgs);
    }
    // eslint-disable-next-line
  }, [open]);

  const handleCloseDialog = () => {
    if (isArrayEmpty(niceClasses)) {
      setLoading(true);
      setError(false);
    }

    dispatch(NiceClassesSearchFilterControlActions.closeEditListModal({}));
  };

  return (
    <ViewDialog
      open={open}
      onClose={handleCloseDialog}
      title={"l.niceClasses"}
      disableEnforceFocus
      dialogActionsSpacing={{ pr: 3 }}
      maxWidth={"xs"}
    >
      <Content open={open} loading={loading} error={error} niceClasses={niceClasses} niceClassCodes={niceClassCodes} />
    </ViewDialog>
  );
};

const Content = ({ open, loading, error, niceClasses, niceClassCodes }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const [niceClassSelected, setNiceClassSelected] = useState(null);
  const [description, setDescription] = useState(null);

  useEffect(() => {
    setNiceClassSelected(niceClassCodes);
  }, [niceClassCodes]);

  if (!open) {
    return null;
  }

  if (loading) {
    return <CircularLoader />;
  }

  if (error) {
    return <Alert severity="error">{t("m.error.serverFetchingError")}</Alert>;
  }

  const handleOnClick = (niceClass) => {
    const niceCode = niceClass.id + "";

    if (niceClassSelected?.some((code) => code === niceCode)) {
      setNiceClassSelected(niceClassSelected?.filter((code) => code !== niceCode));
      setDescription(null);
    } else {
      setNiceClassSelected([...niceClassSelected, niceCode]);
      setDescription(niceClass.heading);
    }
  };

  const selectAllClasses = () => {
    setDescription(null);
    setNiceClassSelected(niceClasses?.map((niceClass) => niceClass.id + ""));
  };

  const deselectAllClasses = () => {
    setDescription(null);
    setNiceClassSelected([]);
  };

  const handleConfirmClasses = () => {
    dispatch(
      TempFormDataActions.setTempData({
        key: "niceClassCodesListKey",
        pointer: "niceClasses.niceClassCodes",
        data: niceClassSelected.sort(function (a, b) {
          return a - b;
        }),
      })
    );
    dispatch(NiceClassesSearchFilterControlActions.closeEditListModal({}));
  };

  const handleClearClasses = () => {
    dispatch(
      TempFormDataActions.setTempData({
        key: "niceClassCodesListKey",
        pointer: "niceClasses.niceClassCodes",
        data: [],
      })
    );
    dispatch(NiceClassesSearchFilterControlActions.closeEditListModal({}));
  };

  return (
    <>
      <GridContainer>
        <TextButton disableRipple startIcon={<Add />} color="primary" onClick={selectAllClasses}>
          {"Селектирай всички"}
        </TextButton>
        <TextButton disableRipple startIcon={<Remove />} color="primary" onClick={deselectAllClasses}>
          {"Изчисти селектираните"}
        </TextButton>
      </GridContainer>
      <GridContainer spacing={2}>
        {niceClasses.map((niceClass) => (
          <GridItem xs={3} sm={2} md={1} lg={1} key={niceClass.id}>
            <NiceClassChip
              label={niceClass.id}
              color={niceClassSelected?.some((code) => code === niceClass.id + "") ? "primary" : "default"}
              onClick={() => handleOnClick(niceClass)}
            />
          </GridItem>
        ))}
      </GridContainer>
      {isNotEmpty(description) && (
        <GridContainer spacing={2}>
          <BlockText text={description} label={"l.description"} />
        </GridContainer>
      )}
      <GridContainer spacing={3} mt={0}>
        <GridItem sm={12} md={12}>
          <Typography align={"right"}>
            <MediumButton
              startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faCheck} />}
              type={"button"}
              variant="contained"
              color="primary"
              onClick={handleConfirmClasses}
            >
              {t("l.btn.confirm")}
            </MediumButton>
            <MediumGreyButton
              startIcon={<FontAwesomeIcon style={{ fontSize: 12 }} icon={faClose} />}
              onClick={handleClearClasses}
              style={{ marginLeft: "15px" }}
              variant="contained"
            >
              {t("l.btn.clear")}
            </MediumGreyButton>
          </Typography>
        </GridItem>
      </GridContainer>
    </>
  );
};

export default NiceClassSearchFilterListDialog;
