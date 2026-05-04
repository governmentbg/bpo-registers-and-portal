import { ListItem, ListItemButton, ListItemText } from "@mui/material";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import { RepresentativeHistoryControlActions } from "../../../../../../../store/redux/slice/ComponentsControl/representativeHistoryControl";
import styled from "styled-components";
import { convertDate, DATE_TIME_FORMAT, parseDate } from "@duosoftbg/bpo-components";

const HistoryListItem = styled(ListItem)`
  border: 1px solid #ccc;
  margin-top: 4px;
`;

const RepresentativeHistoryRecord = ({ viewType, historyRecord }) => {
  const dispatch = useAppDispatch();

  const handleOpenDialog = () => {
    dispatch(RepresentativeHistoryControlActions.openViewHistoryModal({ historyRecord, viewType }));
  };

  const historyDate = convertDate(parseDate(historyRecord.historyTimestamp, DATE_TIME_FORMAT));

  return (
    <HistoryListItem disablePadding>
      <ListItemButton onClick={handleOpenDialog}>
        <ListItemText
          primary={`[${historyDate}] ${historyRecord.historyType.name}`}
          primaryTypographyProps={{
            fontWeight: 500,
          }}
        />
      </ListItemButton>
    </HistoryListItem>
  );
};

export default RepresentativeHistoryRecord;
