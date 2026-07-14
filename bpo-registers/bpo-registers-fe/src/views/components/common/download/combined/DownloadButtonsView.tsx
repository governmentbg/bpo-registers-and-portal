import React from "react";
import DownloadViewAsDocxButton from "../DownloadViewAsDocxButton";
import { isEmpty } from "@duosoftbg/bpo-components";
import DownloadViewAsPdfButton from "../DownloadViewAsPdfButton";

const DownloadButtonsView = ({ data, registerType, componentRef, objectId }) => {
  if (isEmpty(data) || !componentRef) {
    return null;
  }

  return (
    <div className={"docx-hidable"} style={{ float: "right" }}>
      <DownloadViewAsPdfButton registerType={registerType} objectId={objectId} />
      <DownloadViewAsDocxButton
        data={data}
        registerType={registerType}
        componentRef={componentRef}
        objectId={objectId}
      />
    </div>
  );
};

export default DownloadButtonsView;
