import { useTranslation } from "react-i18next";
import { useState } from "react";
import { FilterList } from "@mui/icons-material";
import { Checkbox, DialogContentText, FormControl, FormControlLabel, FormGroup } from "@mui/material";
import { GridContainer, GridItem, TextButton, ViewDialog } from "@duosoftbg/bpo-components";

const SearchFiltersDialogContent = ({
  searchGroup,
  title,
  filters,
  handleChange,
  configName,
  filtersDefinition,
  searchConfig,
}) => {
  const { t } = useTranslation();
  const [open, setOpen] = useState(false);

  return (
    <>
      <div className={"docx-hidable"}>
        <TextButton disableRipple startIcon={<FilterList />} color="primary" onClick={() => setOpen(true)}>
          {t("l.btn.searchFilter")}
        </TextButton>
      </div>
      <ViewDialog open={open} onClose={() => setOpen(false)} title={title}>
        <DialogContentText>{t("t.modal.searchFilters.instruction")}</DialogContentText>
        <FormControl fullWidth component="fieldset" variant="standard">
          <FormGroup>
            <GridContainer spacing={1}>
              {filters &&
                filters.map((item) => (
                  <GridItem xs={12} sm={6} md={4} key={item.id}>
                    <FormControlLabel
                      control={
                        <Checkbox
                          disabled={
                            searchConfig[searchGroup][configName].find((element) => element.id === item.id).required ===
                            true
                          }
                          checked={item.value}
                          onChange={handleChange}
                          name={item.id}
                        />
                      }
                      label={t(filtersDefinition[item.id].label) as string}
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
export default SearchFiltersDialogContent;
