import React from "react";
import { isEmpty, ViewDialog } from "@duosoftbg/bpo-components";
import { RepresentativeHistoryControlActions } from "../../../../../../../../store/redux/slice/ComponentsControl/representativeHistoryControl";
import useAppSelector from "../../../../../../../../hooks/redux/base/useAppSelector";
import useAppDispatch from "../../../../../../../../hooks/redux/base/useAppDispatch";
import { RegisterType } from "../../../../../../../../utils/constants";
import ViewPartnershipHistoryContent from "../../../partnership/panels/history/dialog/ViewPartnershipHistoryContent";
import ViewAgentHistoryContent from "../../../agent/panels/history/dialog/ViewAgentHistoryContent";

const ViewRepresentativeHistoryDialog = () => {
  const dispatch = useAppDispatch();

  const { open, historyRecord, viewType } = useAppSelector((state) => {
    return state.ComponentsControl.representativeHistoryControl.modals.view;
  });

  const handleCloseDialog = () => {
    dispatch(RepresentativeHistoryControlActions.closeViewHistoryModal());
  };

  if (isEmpty(historyRecord)) {
    return null;
  }

  return (
    <ViewDialog
      open={open}
      onClose={handleCloseDialog}
      title={historyRecord.historyType.name}
      disableEnforceFocus
      dialogActionsSpacing={{ pr: 3 }}
    >
      {viewType === RegisterType.PARTNERSHIP && <ViewPartnershipHistoryContent history={historyRecord} />}
      {viewType === RegisterType.AGENT && <ViewAgentHistoryContent history={historyRecord} />}
    </ViewDialog>
  );
};

export default ViewRepresentativeHistoryDialog;
