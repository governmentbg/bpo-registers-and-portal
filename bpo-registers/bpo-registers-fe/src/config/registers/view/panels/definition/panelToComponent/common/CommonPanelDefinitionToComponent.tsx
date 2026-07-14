import React from "react";
import { isArrayEmpty } from "@duosoftbg/bpo-components";
import RecordalDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/RecordalDataPanel";
import {
  getCancellationsByObjectId,
  getDecisionById,
  getDecisionByObjectId,
  getOppositionsByObjectId,
  getRecordalsByObjectId,
} from "../../../../../../../axios/api/services";
import OppositionDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/OppositionDataPanel";
import CancellationDataPanel from "../../../../../../../views/components/registers/view/ipObjects/common/CancellationDataPanel";
import ObjectDecisionsPanel from "../../../../../../../views/components/registers/view/ipObjects/common/ObjectDecisionsPanel";

export const COMMON_PANEL_DEFINITION_TO_COMPONENT = {
  recordalData: {
    id: "recordalData",
    label: "l.panel.recordalData",
    component: (data) => <RecordalDataPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.recordals),
    loadPanelNotifier: {
      notifierFieldName: "recordals",
      notifierAsyncFunc: (data) => {
        return () => getRecordalsByObjectId(data.id);
      },
    },
  },
  oppositionData: {
    id: "oppositionData",
    label: "l.panel.oppositionData",
    component: (data) => <OppositionDataPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.oppositions),
    loadPanelNotifier: {
      notifierFieldName: "oppositions",
      notifierAsyncFunc: (data) => {
        return () => getOppositionsByObjectId(data.id);
      },
    },
  },
  cancellationData: {
    id: "cancellationData",
    label: "l.panel.cancellationData",
    component: (data) => <CancellationDataPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.cancellations),
    loadPanelNotifier: {
      notifierFieldName: "cancellations",
      notifierAsyncFunc: (data) => {
        return () => getCancellationsByObjectId(data.id);
      },
    },
  },

  objectDecisionsData: {
    id: "objectDecisionsData",
    label: "l.panel.objectDecisionsData",
    component: (data) => <ObjectDecisionsPanel data={data} />,
    defineEmptyPanelFn: (data) => isArrayEmpty(data?.decisions),
    loadPanelNotifier: {
      notifierFieldName: "decisions",
      notifierAsyncFunc: (data) => {
        return () => getDecisionByObjectId(data.id);
      },
    },
  },
};
