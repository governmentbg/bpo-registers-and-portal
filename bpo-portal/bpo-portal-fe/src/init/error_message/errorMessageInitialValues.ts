import { FeedbackDetails } from "../../types/feedback/feedbackTypes";
import { FeedbackMessageType } from "../../utils/constants";

export const errorMessageInitialValues: FeedbackDetails = {
  firstName: "",
  lastName: "",
  description: "",
  email: "",
  objectType: "",
  applicationNumber: "",
  messageType: FeedbackMessageType.IpObjectError,
  attachments: [],
};
