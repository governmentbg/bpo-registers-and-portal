import { combineI18nMessages, defaultMessagesEN } from "@duosoftbg/bpo-components";
import validationKeysEN from "./slices/validationKey/validationKeysEN";

const messagesEn = combineI18nMessages(defaultMessagesEN, {
  translation: {
    "t.page.homePage": "Home Page",
    "l.generalInformation.homePage": "Information",
    "l.dynamicInformation.homePage": "Important",
    "t.page.services": "Electronic Services",
    "t.page.section": "Section",
    "t.page.serviceDefinition": "Service Definition",
    "t.page.feedback": "Feedback",
    "t.page.ipObjectErrorMessage": "Report an error",
    "t.page.adminConsole": "Admin Console",
    "l.bpo.registers": "Registers",
    "t.page.userGuides": "User guides",
    "l.name": "Name",
    "l.feedbackDescription": " Suggestion",
    "l.errorMessageDescription": "Error description",
    "l.email": "Email",
    "l.btn.newRequest": "New request",
    "l.btn.myApplications": "My applications",
    "l.objectType": "Object type",
    "l.applicationNumber": "Application number",
    "m.email.text.errorMessage": "A copy of the reported error will be sent to the specified email address",
    "m.email.text.feedback": "A copy of the suggestion will be sent to the specified email address",
    "m.feedback.general.info":
      "Dear customers, the feedback form should be used solely for suggestions and/or opinions regarding the manner in which services are provided, the accessibility of information about them, and possible improvements in communication and processes related to customer service. The feedback form is not intended for inquiries about submitted applications or administrative services already provided. Information about the administrative services carried out by the Bulgarian Patent Office — including how to complete the relevant requests, the service fees, and the amount of the charges — can be obtained on-site at the Administrative Service Centre of the Patent Office of the Republic of Bulgaria, or after submitting a written request by fax or by email at: services@bpo.bg. ",
    "m.feedback.general.info.warn":
      "Any other inquiries, requests, or submitted documents sent through the feedback form, beyond those specified above, will not be responded to.",
    "validation.field.invalid.applicationNumber": "Invalid application number",
    "validation.field.invalid.objectType": "Invalid object type",
    "validation.recaptcha.verification.failed": "There was a problem processing the request.",
    ...validationKeysEN.translation,
  },
});

export default messagesEn;
