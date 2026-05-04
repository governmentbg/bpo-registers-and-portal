import { topologyData } from "./data";
import { BpoTableSimple, CardSpg, FileIconButton, HeadCellType, useAsyncCall } from "@duosoftbg/bpo-components";
import { CardContent } from "@mui/material";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell/TableCell";
import { handleFileDownload } from "../../../../../utils/functions";

const topologyColumns: HeadCellType[] = [
  { id: "applicationNumber", label: "l.table.head.applicationNumber", sortable: false },
  { id: "documentNumber", label: "l.table.head.documentNumber", sortable: false },
  { id: "applicationDate", label: "l.table.head.applicationDate", sortable: false },
  { id: "expiryDate", label: "l.table.head.expiryDate", sortable: false },
  { id: "name", label: "l.table.head.name", sortable: false },
  { id: "owner", label: "l.table.head.owner", sortable: false },
  { id: "creator", label: "l.table.head.creator", sortable: false },
  { id: "representative", label: "l.table.head.representative", sortable: false },
  { id: "file", label: "l.table.head.file", sortable: false },
];

const SearchTopology = () => {
  const records = topologyData;
  const { asyncCall } = useAsyncCall();

  return (
    <CardSpg my={4} style={{ overflow: "visible" }}>
      <CardContent style={{ position: "relative" }}>
        <BpoTableSimple headCells={topologyColumns} isSmall={true}>
          {records.map((row) => (
            <TableRow key={row.applicationNumber}>
              <TableCell>{row.applicationNumber}</TableCell>
              <TableCell>{row.documentNumber}</TableCell>
              <TableCell>{row.applicationDate}</TableCell>
              <TableCell>{row.expiryDate}</TableCell>
              <TableCell>{row.name}</TableCell>
              <TableCell>{row.owner}</TableCell>
              <TableCell>{row.creator}</TableCell>
              <TableCell>{row.representative}</TableCell>
              <TableCell>
                <FileIconButton
                  onClick={() =>
                    handleFileDownload(
                      "bpo-registers",
                      `topologies/topology_${row.applicationNumber}.pdf`,
                      `topology_${row.applicationNumber}.pdf`,
                      asyncCall
                    )
                  }
                  fileHref={null}
                  type={"pdf"}
                />
              </TableCell>
            </TableRow>
          ))}
        </BpoTableSimple>
      </CardContent>
    </CardSpg>
  );
};

export default SearchTopology;
