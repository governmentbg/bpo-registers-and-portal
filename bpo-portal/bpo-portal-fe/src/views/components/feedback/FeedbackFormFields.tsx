import {
  AttachmentsFormField,
  GridContainer,
  GridItem,
  InputFormField,
  MediumButton,
  TextareaFormField,
} from "@duosoftbg/bpo-components";
import Typography from "@mui/material/Typography/Typography";
import React from "react";
import { useTranslation } from "react-i18next";
import styled from "styled-components";
import { FeedbackMessageType } from "../../../utils/constants";

const SmallText = styled.span`
  font-size: 12px;
  color: grey;
  margin-top: 4px;
  margin-left: 4px;
`;

const FeedbackFormFields = ({ feedbackType }) => {
  const { t } = useTranslation();

  return (
    <div>
      <GridContainer spacing={3} mt={2}>
        <GridItem sm={4} md={4} lg={4}>
          <InputFormField required={true} fieldName={"firstName"} labelCode={"l.name"} />
        </GridItem>
        <GridItem sm={4} md={4} lg={4}>
          <InputFormField required={true} fieldName={"lastName"} labelCode={"l.lastName"} />
        </GridItem>
        <GridItem sm={4} md={4} lg={4}>
          <InputFormField required={true} fieldName={"email"} labelCode={"l.email"} />
        </GridItem>
      </GridContainer>
      <FeedbackDescriptionFormField feedbackType={feedbackType} />
      <FeedbackEmailSendingNote feedbackType={feedbackType} />
      <GridContainer spacing={3} mt={2}>
        <GridItem sm={12} md={12} lg={12}>
          <AttachmentsFormField fieldName={"attachments"} validators={[{ maxSize: 10000000 }]} />
        </GridItem>
      </GridContainer>
      <GridContainer spacing={3}>
        <GridItem sm={12} md={12}>
          <Typography align={"right"}>
            <MediumButton size={"medium"} variant="contained" color="primary" type={"submit"}>
              {t("l.btn.save")}
            </MediumButton>
          </Typography>
        </GridItem>
      </GridContainer>
    </div>
  );
};

const FeedbackDescriptionFormField = ({ feedbackType }) => {
  const descriptionLabel =
    FeedbackMessageType.Feedback === feedbackType ? "l.feedbackDescription" : "l.errorMessageDescription";

  return (
    <GridContainer spacing={3} mt={2}>
      <GridItem sm={12} md={12} lg={12}>
        <TextareaFormField required={true} fieldName={"description"} labelCode={descriptionLabel} />
      </GridItem>
    </GridContainer>
  );
};

const FeedbackEmailSendingNote = ({ feedbackType }) => {
  const { t } = useTranslation();
  const emailTextMessage =
    FeedbackMessageType.Feedback === feedbackType ? "m.email.text.feedback" : "m.email.text.errorMessage";

  return (
    <GridContainer spacing={1} mt={0}>
      <GridItem sm={12} md={12} lg={12}>
        <SmallText>{t(emailTextMessage)} </SmallText>
      </GridItem>
    </GridContainer>
  );
};

export default FeedbackFormFields;
