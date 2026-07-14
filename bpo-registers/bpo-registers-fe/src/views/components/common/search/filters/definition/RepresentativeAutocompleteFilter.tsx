import { useWatch } from "react-hook-form";
import { RepresentativeSearchType } from "../../../../../../types/registers/search/filter/filterTypes";
import { AsyncAutocompleteFormField, GridItem, iTxt } from "@duosoftbg/bpo-components";
import { autocompleteRepresentatives } from "../../../../../../axios/api/services";
import { HTMLAttributes } from "react";
import { AutocompleteRenderOptionState, Box, Checkbox, Typography } from "@mui/material";
import { CheckBox, CheckBoxOutlineBlank, Info, People, Person } from "@mui/icons-material";
import { ReactComponent as Lawyer } from "@duosoftbg/bpo-components/dist/assets/images/representatives/lawyer.svg";
import { ReactComponent as Temp } from "@duosoftbg/bpo-components/dist/assets/images/representatives/temp-person.svg";
import * as React from "react";
import { IconWrapper } from "../../../../registers/view/representatives/common/history/HistoryBlockText";
import { useTranslation } from "react-i18next";

const RepresentativeAutocompleteFilter = () => {
  const objectRange = useWatch({ name: "objectRange" });
  const searchType = useWatch({ name: "representativeSearchType" });
  const { i18n } = useTranslation();

  if (RepresentativeSearchType.AUTOCOMPLETE !== searchType) {
    return null;
  }
  return (
    <>
      <GridItem sm={12} md={12} lg={12}>
        <GridItem xs={12} sm={12} md={12} lg={12}>
          <AsyncAutocompleteFormField
            multiple={true}
            disableCloseOnSelect={true}
            minCharsAutocomplete={2}
            label={"l.searchFilter.representatives"}
            asyncFn={autocompleteRepresentatives}
            additionalAsyncFnParams={{ objectTypes: objectRange }}
            fieldName={"representatives"}
            isOptionEqualToValue={(option, val) => option.id === val.id}
            optionSelectedText={(option) => option.name + (option.agentCode ? ` (${option.agentCode})` : "")}
            renderOption={(props, option, state) => (
              <RepresentativeAutocompleteOption
                props={props}
                option={option.name + (option.agentCode ? ` (${option.agentCode})` : "")}
                state={state}
                type={option.representativeType}
                title={iTxt(i18n.language, option.representativeTypeName, option.representativeTypeNameEn)}
              />
            )}
          />
        </GridItem>
      </GridItem>
    </>
  );
};
export default RepresentativeAutocompleteFilter;

export const RepresentativeAutocompleteOption = ({
  props,
  option,
  state,
  type,
  title,
}: {
  props: HTMLAttributes<HTMLLIElement>;
  option: string;
  state: AutocompleteRenderOptionState;
  type: string;
  title: string;
}) => {
  const getOptionIcon = (representativeType) => {
    switch (representativeType) {
      case "AG":
      case "IP":
        return <Person />;
      case "PP":
      case "PC":
        return <People />;
      case "LA":
      case "LP":
      case "LC":
        return <Lawyer />;
      case "TS":
        return <Temp />;
      default:
        return <Info />;
    }
  };

  return (
    <Box component="li" {...props}>
      <Checkbox
        icon={<CheckBoxOutlineBlank fontSize="small" />}
        checkedIcon={<CheckBox fontSize="small" />}
        checked={state.selected}
      />
      <Typography mr={2} title={title}>
        <IconWrapper>{getOptionIcon(type)}</IconWrapper>
      </Typography>
      <Typography>{option}</Typography>
    </Box>
  );
};
