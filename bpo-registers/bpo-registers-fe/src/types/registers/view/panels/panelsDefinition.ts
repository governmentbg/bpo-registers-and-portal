import React from "react";

export type PanelToComponentDefinition = {
  id: string;
  label: string;
  component: (data) => React.ReactElement;
  defineEmptyPanelFn?: (data) => boolean;
  loadPanelNotifier?: any;
}[];
