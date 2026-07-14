import { useTranslation } from "react-i18next";
import { useFormContext } from "react-hook-form";
import * as React from "react";
import { Fragment } from "react";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import { AlertSpg, BpoSearchTable, TableImage } from "@duosoftbg/bpo-components";
import TableRow from "@mui/material/TableRow";
import { CellType, getRegisterTypeByObjectType, ObjectType, RegisterType } from "../../../../../../utils/constants";
import { ApiEndpoints } from "../../../../../../axios/api/endpoints";
import { generateUrl, openInNewTab } from "../../../../../../utils/urls";
import { useNavigate } from "react-router-dom";
import { SEARCH_GROUP } from "../../../../../../config/registers/search/registersSearchConfig";
import { ReactComponent as MarkLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-mark.svg";
import { ReactComponent as GILogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-geo.svg";
import { ReactComponent as PatentLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-patent.svg";
import { ReactComponent as EuPatentLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-eupatent.svg";
import { ReactComponent as UMLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-utility-model.svg";
import { ReactComponent as DogLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-dog.svg";
import { ReactComponent as SPCLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-pills.svg";
import { ReactComponent as DesignLogo } from "@duosoftbg/bpo-components/dist/assets/images/objects/icon-design.svg";
import { Tooltip } from "@mui/material";

const RegisterListTableBase = ({
  total,
  records,
  blockTable,
  onPageOrOrderChange,
  searchGroup,
  activeCells,
  activeColumns,
  selectedColumnsDialog,
  optionCellContent = undefined,
}) => {
  const { t } = useTranslation();

  return (
    <Fragment>
      {total > 0 && (
        <>
          {selectedColumnsDialog}
          <BpoSearchTable
            group={searchGroup}
            headCells={activeCells}
            total={total}
            blockTable={blockTable}
            onPageOrOrderChange={onPageOrOrderChange}
          >
            <TableBody>
              {records.map((row, index) => (
                <RegisterTableRow
                  key={(row.objectId ? row.objectId : row.id) + "-" + index}
                  row={row}
                  index={index}
                  activeColumns={activeColumns}
                  optionCellContent={optionCellContent}
                  searchGroup={searchGroup}
                />
              ))}
            </TableBody>
          </BpoSearchTable>
        </>
      )}
      {!(total > 0) && (
        <AlertSpg mt={10} mb={10} severity="info">
          {t("m.empty.list")}
        </AlertSpg>
      )}
    </Fragment>
  );
};

const RegisterTableRow = ({ row, index, activeColumns, optionCellContent, searchGroup }) => {
  const { getValues } = useFormContext();
  const navigate = useNavigate();

  return (
    <TableRow
      hover
      onClick={(event) => {
        if (searchGroup !== SEARCH_GROUP.DECISION_SEARCH) {
          if (event.ctrlKey || event.metaKey) {
            // Open in a new tab
            openInNewTab(
              generateUrl(
                getRegisterTypeByObjectType(row?.objectType),
                row?.objectId?.replaceAll("/", "_"),
                "view",
                true,
                searchGroup === SEARCH_GROUP.COMBINED_SEARCH
              )
            );
          } else {
            // Keep state of SPA
            navigate(
              generateUrl(
                getRegisterTypeByObjectType(row?.objectType),
                row?.objectId?.replaceAll("/", "_"),
                "view",
                false,
                searchGroup === SEARCH_GROUP.COMBINED_SEARCH
              )
            );
          }
        } else {
          if (event.ctrlKey || event.metaKey) {
            // Open in a new tab
            openInNewTab(generateUrl(RegisterType.DECISIONS, row?.id?.replaceAll("/", "_"), "view", true, false));
          } else {
            // Keep state of SPA
            navigate(generateUrl(RegisterType.DECISIONS, row?.id?.replaceAll("/", "_"), "view", false, false));
          }
        }
      }}
      style={{ cursor: "pointer" }}
    >
      <TableCell>{index + 1 + getValues().page * getValues().pageSize}</TableCell>
      <ObjectLogoCell row={row} searchGroup={searchGroup} />
      <>
        {activeColumns.map((cell) => (
          <TableCellRenderer key={cell.id} cell={cell} row={row} />
        ))}
      </>
    </TableRow>
  );
};

const TableCellRenderer = ({ cell, row }) => {
  switch (cell.type) {
    case CellType.IMAGE:
      return cell.getValue(row) ? <TableImageCell row={row} /> : <TableCell></TableCell>;
    default:
      return <TableCell key={cell.id}>{cell.getValue(row) ?? ""}</TableCell>;
  }
};

const TableImageCell = ({ row }) => {
  const registersApi = `${process.env.REACT_APP_REGISTERS_API_URL}`;
  const thumbnailEndpoint = `${ApiEndpoints.registers.files.thumbnails.getByObjectIdAndType
    .replace("{objectId}", row.objectId)
    .replace("{objectType}", row.objectType)}`;
  const srcEndpoint = `${registersApi}/api/v1${thumbnailEndpoint}`;

  return (
    <TableCell>
      <TableImage src={srcEndpoint}></TableImage>
    </TableCell>
  );
};

const ObjectLogoCell = ({ row, searchGroup }) => {
  const { t } = useTranslation();
  let objType;
  if (searchGroup === SEARCH_GROUP.COMBINED_SEARCH) {
    objType = row.objectType;
  } else if (searchGroup === SEARCH_GROUP.DECISION_SEARCH) {
    objType = row.objectType.id;
  } else {
    return null;
  }
  return (
    <Tooltip title={t(`l.object.${objType}`)} placement={"left"} arrow>
      <TableCell width={"3%"}>
        {[ObjectType.MARK, ObjectType.DIVISIONAL_MARK].includes(objType) && <MarkLogo />}
        {[ObjectType.DESIGN, ObjectType.SINGLE_DESIGN].includes(objType) && <DesignLogo />}
        {objType === ObjectType.PATENT && <PatentLogo />}
        {objType === ObjectType.EU_PATENT && <EuPatentLogo />}
        {objType === ObjectType.UTILITY_MODEL && <UMLogo />}
        {objType === ObjectType.SPC && <SPCLogo />}
        {objType === ObjectType.PLANT_BREED && <DogLogo />}
        {objType === ObjectType.GEO_INDICATION && <GILogo />}
      </TableCell>
    </Tooltip>
  );
};
export default RegisterListTableBase;
