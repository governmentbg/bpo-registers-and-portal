import React, { Fragment } from "react";
import { Box, Grid } from "@mui/material";
import { GridSpg, TableSkeleton } from "@duosoftbg/bpo-components";

const RegisterList = ({ isLoading, children }) => {
  const showTable = () => {
    return <Fragment>{children}</Fragment>;
  };

  return (
    <Box style={{ marginTop: "10px" }}>
      <Grid container spacing={1}>
        <GridSpg item xs={12}>
          {isLoading ? <TableSkeleton /> : showTable()}
        </GridSpg>
      </Grid>
    </Box>
  );
};
export default RegisterList;
