import { SEARCH_GROUP, selectTableColumnsBySearchGroup } from "../config/registers/search/registersSearchConfig";

export const selectActiveHeadCells = (tableColumns, searchGroup) => {
  return [
    {
      id: "number",
      label: "l.table.head.number",
      sortable: false,
    },
    ...(SEARCH_GROUP.COMBINED_SEARCH === searchGroup || SEARCH_GROUP.DECISION_SEARCH === searchGroup
      ? [
          {
            id: "type",
            label: "l.table.head.type",
            sortable: false,
          },
        ]
      : []),
    ...getActiveData(tableColumns, searchGroup),
    ...(SEARCH_GROUP.AGENTS_SEARCH === searchGroup
      ? [
          {
            id: "options",
            label: "",
            sortable: false,
            className: "docx-hidable",
          },
        ]
      : []),
  ];
};

export const selectActiveColumns = (tableColumns, searchGroup) => {
  return [...getActiveData(tableColumns, searchGroup)];
};

const getActiveData = (tableColumns, searchGroup) => {
  const activeData = [];

  tableColumns.forEach((item) => {
    if (item.value === true) {
      activeData.push(selectTableColumnsBySearchGroup(searchGroup)[item.id]);
    }
  });
  return activeData;
};
