import { Alert, Stack } from "@mui/material";
import { BoxSpg } from "@duosoftbg/bpo-components";
import { useState } from "react";
import { useSearchParams } from "react-router-dom";

const SuccessfullyErrorReport = (message) => {
  const [searchParams] = useSearchParams();
  const sentMessage = searchParams.get("sentMessage");

  const [showReport, setShowReport] = useState(true);

  if (sentMessage === "true") {
    return (
      <>
        {showReport && (
          <BoxSpg>
            <Stack sx={{ width: "100%" }} mb={5}>
              <Alert severity="success" style={{ fontSize: 14 }} onClose={() => setShowReport(false)}>
                {message.text}
              </Alert>
            </Stack>
          </BoxSpg>
        )}
      </>
    );
  }

  return null;
};

export default SuccessfullyErrorReport;
