import { Attachment } from "@duosoftbg/bpo-components";

export interface FeedbackDetails {
  firstName: string;
  lastName: string;
  description: string;
  email: string;
  objectType?: string;
  applicationNumber?: string;
  messageType: string;
  attachments: Array<Attachment>;
}
