import { useEffect, useState } from "react";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import useTableSelectedColumns from "../../../../../../hooks/registers/search/table/useTableSelectedColumns";
import SelectedColumnsDialogContent from "./SelectedColumnsDialogContent";
import { updateSelectedColumnsValue } from "../../../../../../store/redux/slice/SearchData/registersSearchData";
import { selectTableColumnsBySearchGroup } from "../../../../../../config/registers/search/registersSearchConfig";

const SelectedColumnsDialog = ({ searchGroup, title = "t.modal.selectTableColumns" }) => {
  const dispatch = useAppDispatch();
  const [columns, setColumns] = useState([]);
  let tableColumns = useTableSelectedColumns(searchGroup);

  useEffect(() => {
    setColumns(tableColumns);
  }, [tableColumns]);

  const handleChange = (event) => {
    dispatch(
      updateSelectedColumnsValue({
        group: searchGroup,
        name: event.target.name,
        value: event.target.checked,
      })
    );
  };

  return (
    <SelectedColumnsDialogContent
      title={title}
      columns={columns}
      handleChange={handleChange}
      columnsDefinition={selectTableColumnsBySearchGroup(searchGroup)}
    />
  );
};
export default SelectedColumnsDialog;
