import {
  BlockText,
  CircularLoader,
  DividerSpg,
  GridContainer,
  isArrayEmpty,
  iTxt,
  useAsyncCall,
  ViewDialog,
} from "@duosoftbg/bpo-components";
import React, { Fragment, useEffect, useState } from "react";
import { getRegisterTypeByObjectType, PersonType } from "../../../../../../utils/constants";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { Alert, Grid, IconButton, Link, Table } from "@mui/material";
import { AccountTree } from "@mui/icons-material";
import { useTranslation } from "react-i18next";
import { PersonRelatedObjectsControlActions } from "../../../../../../store/redux/slice/ComponentsControl/personRelatedObjectsControl";
import {
  getPersonObjectRelationshipsByPersonName,
  getPersonObjectRelationshipsByPersonNameCount,
} from "../../../../../../axios/api/services";
import TableHead from "@mui/material/TableHead/TableHead";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell/TableCell";
import TableBody from "@mui/material/TableBody";
import TableContainer from "@mui/material/TableContainer/TableContainer";
import Typography from "@mui/material/Typography";
import i18n from "i18next";
import { generateUrl } from "../../../../../../utils/urls";
import DownloadPersonObjectsAsExcelButton from "../../../../common/download/DownloadPersonObjectsAsExcelButton";

export const RelatedObjectsDialog = () => {
  const dispatch = useAppDispatch();
  const { asyncCall } = useAsyncCall();

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [relatedObjects, setRelatedObjects] = useState(null);

  const { open, person, objectId } = useAppSelector((state) => {
    return state.ComponentsControl.personRelatedObjectsControl.modals.view;
  });

  const handleCloseDialog = () => {
    dispatch(PersonRelatedObjectsControlActions.closeModal());
    setRelatedObjects(null);
    setLoading(true);
  };

  useEffect(() => {
    if (person && objectId) {
      asyncCall({
        promise: getPersonObjectRelationshipsByPersonName(person?.name, objectId),
        onSuccess: (response) => {
          setRelatedObjects(response);
          setLoading(false);
          setError(false);
        },
        onError: () => {
          setError(true);
          setLoading(false);
        },
      });
    }
  }, [asyncCall, objectId, person]);

  return (
    <ViewDialog
      open={open}
      onClose={handleCloseDialog}
      title={"l.person.related.objects"}
      disableEnforceFocus
      dialogActionsSpacing={{ pr: 3 }}
      maxWidth={"xs"}
    >
      <Content
        open={open}
        loading={loading}
        error={error}
        setError={setError}
        relatedObjects={relatedObjects}
        personName={person?.name}
        objectId={objectId}
      />
    </ViewDialog>
  );
};

const Content = ({ open, loading, error, setError, relatedObjects, personName, objectId }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const [recordCount, setRecordCount] = useState(0);

  useEffect(() => {
    if (personName && objectId) {
      asyncCall({
        promise: getPersonObjectRelationshipsByPersonNameCount(personName),
        onSuccess: (response) => {
          setRecordCount(response);
        },
        onError: () => {
          setError(true);
        },
      });
    }
  }, [asyncCall, objectId, personName, setError]);

  if (!open) {
    return null;
  }

  if (loading) {
    return <CircularLoader />;
  }

  if (error) {
    return <Alert severity="error">{t("m.error.serverFetchingError")}</Alert>;
  }

  if (isArrayEmpty(relatedObjects)) {
    return (
      <>
        <Alert severity="warning" style={{ marginTop: "10px", marginBottom: "10px" }}>
          {t("m.warning.person.duplicates")}
        </Alert>
        <Alert severity="info">{t("m.empty.list")}</Alert>
      </>
    );
  }

  return (
    <>
      <Typography variant="h3" textAlign={"center"}>
        {personName}
      </Typography>
      <Alert severity="warning" style={{ marginTop: "10px", marginBottom: "10px" }}>
        {t("m.warning.person.duplicates")}
      </Alert>
      {recordCount > 200 && (
        <Alert severity="warning" style={{ marginTop: "10px", marginBottom: "10px" }}>
          {t("m.warning.person.cut.results.3", [recordCount])}
        </Alert>
      )}
      <div style={{ float: "right", marginBottom: "10px" }}>
        <DownloadPersonObjectsAsExcelButton personName={personName} objectId={objectId} />
      </div>
      <TableContainer>
        <Table aria-label="collapsible table">
          <TableHead>
            <TableRow>
              <TableCell>№</TableCell>
              <TableCell>{t("l.table.head.objectId")}</TableCell>
              <TableCell>{t("l.table.head.title")}</TableCell>
              <TableCell>{t("l.table.head.role")}</TableCell>
            </TableRow>
          </TableHead>

          <TableBody>
            {relatedObjects.map((x, i) => (
              <TableRow hover key={Math.random()}>
                <TableCell>{i + 1}</TableCell>
                <TableCell>
                  <Link
                    target={"_blank"}
                    href={generateUrl(
                      getRegisterTypeByObjectType(x?.id?.objectId?.split("/")[1]),
                      x?.id?.objectId?.replaceAll("/", "_"),
                      "view",
                      true
                    )}
                  >
                    {x?.id?.objectId}
                  </Link>
                </TableCell>
                <TableCell>{iTxt(i18n.language, x?.objectTitle, x?.objectTitleEn)}</TableCell>
                <TableCell>{iTxt(i18n.language, x?.roleDescription, x?.roleDescriptionEn)}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </>
  );
};

const PersonDataPanel = ({ data, personType }) => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation();

  const handleOpenRelatedObjectsDialog = (person) => {
    dispatch(PersonRelatedObjectsControlActions.openModal({ person, objectId: data?.id }));
  };

  return (
    <div className={"person-data-panel-" + personType}>
      {data?.ipObject?.persons
        .filter((z) => z.id?.personRole === personType)
        .sort((a, b) => a.personOrder - b.personOrder)
        .map((x, index) => (
          <Fragment key={x?.id?.personId}>
            {index !== 0 && <DividerSpg />}
            <GridContainer spacing={3}>
              <BlockText
                propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                withGrid
                label={`l.person.name`}
                text={
                  personType === PersonType.REPRESENTATIVE
                    ? x?.representativeType?.description + ": " + x?.person?.name
                    : x?.person?.name
                }
              />
              <BlockText
                propsGrid={{ xs: 10, sm: 10, md: 5, lg: 3 }}
                withGrid
                label={`l.person.nationality`}
                text={x?.person?.nationalityCountryCode?.name}
              />
              <BlockText
                propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                withGrid
                label={`l.person.address`}
                text={[
                  x?.person?.address?.residenceCountryCode?.name,
                  x?.person?.address?.stateName,
                  x?.person?.address?.cityName,
                  x?.person?.address?.zipCode,
                  x?.person?.address?.addressStreet,
                ]
                  .filter((a) => a && a !== "")
                  .join(", ")}
              />
              <Grid item xs={2} sm={2} md={1} lg={1}>
                <IconButton
                  className={"docx-hidable"}
                  size="large"
                  color={"primary"}
                  title={t("l.view.related.objects")}
                  onClick={() => handleOpenRelatedObjectsDialog(x?.person)}
                >
                  <AccountTree />
                </IconButton>
              </Grid>
            </GridContainer>
          </Fragment>
        ))}
    </div>
  );
};
export default PersonDataPanel;
