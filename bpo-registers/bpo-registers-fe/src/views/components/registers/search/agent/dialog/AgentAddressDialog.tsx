import React from "react";

import { ViewDialog } from "@duosoftbg/bpo-components";
import { AgentAddressesControlActions } from "../../../../../../store/redux/slice/ComponentsControl/agentAddressesControl";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import AgentAddressInfo from "../../../view/representatives/common/mainData/section/AddressInfo";
import { isAgentCaDataNotEmpty, RegisterType } from "../../../../../../utils/constants";
import CorrespondentAddressInfo from "../../../view/representatives/common/mainData/section/CorrespondentAddressInfo";

const AgentAddressDialog = () => {
  const dispatch = useAppDispatch();
  const { open, person } = useAppSelector((state) => {
    return state.ComponentsControl.agentAddressesControl.modals.view;
  });

  const handleCloseDialog = () => {
    dispatch(AgentAddressesControlActions.closeModal());
  };

  return (
    <ViewDialog
      open={open}
      onClose={handleCloseDialog}
      title={"l.agent.address"}
      disableEnforceFocus
      dialogActionsSpacing={{ pr: 3 }}
      maxWidth={"xs"}
    >
      <Content person={person} />
    </ViewDialog>
  );
};

const Content = ({ person }) => {
  const notEmptyCaData = isAgentCaDataNotEmpty(person);
  return (
    <>
      {person && (
        <>
          <AgentAddressInfo registerType={RegisterType.AGENT} person={person} />
          {notEmptyCaData && <CorrespondentAddressInfo agentAddress={person.agent.address} />}
        </>
      )}
    </>
  );
};

export default AgentAddressDialog;
