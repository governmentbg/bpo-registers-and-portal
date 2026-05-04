import { GridItem, iTxt, SelectFormField, useAppDispatch } from "@duosoftbg/bpo-components";
import { useEffect } from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { agentStatusesThunk } from "../../../../../../store/redux/slice/AppData/agentStatuses";
import { useTranslation } from "react-i18next";

const AgentStatusSearchFilter = () => {
  const { i18n } = useTranslation();

  const agentStatuses = useAppSelector((state) => {
    return state.AppData.agentStatuses;
  });
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(agentStatusesThunk());
  }, [dispatch]);

  if (!agentStatuses || !agentStatuses.data) {
    return null;
  }
  return (
    <>
      <GridItem sm={6} md={6} lg={6}>
        <SelectFormField
          fieldName={"status"}
          labelCode={"l.searchFilter.status"}
          selectOptions={agentStatuses.data.map((status) => {
            return { value: status.id, text: iTxt(i18n.language, status.name, status.nameEn) };
          })}
          addEmptyOption={true}
        />
      </GridItem>
    </>
  );
};
export default AgentStatusSearchFilter;
