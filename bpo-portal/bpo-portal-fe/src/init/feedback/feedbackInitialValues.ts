import { FeedbackDetails } from "../../types/feedback/feedbackTypes";
import { FeedbackMessageType } from "../../utils/constants";

export const feedbackDetailsInitialValues: FeedbackDetails = {
  firstName: "",
  lastName: "",
  description: "",
  email: "",
  messageType: FeedbackMessageType.Feedback,
  attachments: [],
};
