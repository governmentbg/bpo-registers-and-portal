import { useTranslation } from "react-i18next";
import { useState } from "react";
import { FilterList } from "@mui/icons-material";
import { Checkbox, DialogContentText, FormControl, FormControlLabel, FormGroup, Toolbar } from "@mui/material";
import { GridContainer, GridItem, TextButton, ViewDialog } from "@duosoftbg/bpo-components";

const SelectedColumnsDialogContent = ({ title, columns, handleChange, columnsDefinition }) => {
  const { t } = useTranslation();
  const [open, setOpen] = useState(false);

  return (
    <>
      <Toolbar variant={"dense"} style={{ background: "rgb(238, 238, 238)" }}>
        <div className={"docx-hidable"}>
          <TextButton disableRipple startIcon={<FilterList />} color="primary" onClick={() => setOpen(true)}>
            {t(title)}
          </TextButton>
        </div>
      </Toolbar>

      <ViewDialog open={open} onClose={() => setOpen(false)} title={title}>
        <DialogContentText>{t("t.modal.columns.select.instruction")}</DialogContentText>
        <FormControl fullWidth component="fieldset" variant="standard">
          <FormGroup>
            <GridContainer spacing={1}>
              {columns &&
                columns.map((item) => (
                  <GridItem xs={12} sm={6} md={4} key={item.id}>
                    <FormControlLabel
                      control={<Checkbox checked={item.value} onChange={handleChange} name={item.id} />}
                      label={t(columnsDefinition[item.id].label) as string}
                    />
                  </GridItem>
                ))}
            </GridContainer>
          </FormGroup>
        </FormControl>
      </ViewDialog>
    </>
  );
};
export default SelectedColumnsDialogContent;
