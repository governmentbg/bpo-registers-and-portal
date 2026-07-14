import { combineI18nMessages, defaultMessagesBG } from "@duosoftbg/bpo-components";
import validationKeysBG from "./slices/validationKey/validationKeysBG";

const messagesBg = combineI18nMessages(defaultMessagesBG, {
  translation: {
    "t.page.homePage": "Начало",
    "l.generalInformation.homePage": "Информация",
    "l.dynamicInformation.homePage": "Важно съобщение",
    "t.page.services": "Електронни услуги",
    "t.page.section": "Секция",
    "t.page.serviceDefinition": "Описание на услуга",
    "t.page.feedback": "Обратна връзка",
    "t.page.ipObjectErrorMessage": "Докладвай грешка",
    "t.page.adminConsole": "Административен модул",
    "t.page.userGuides": "Указания за ползване",
    "l.bpo.registers": "Регистри",
    "l.name": "Име",
    "l.feedbackDescription": "Предложение",
    "l.errorMessageDescription": "Описание на грешката",
    "l.email": "Електронен адрес",
    "l.btn.newRequest": "Ново заявление",
    "l.btn.myApplications": "Моите заявки",
    "l.objectType": "Тип обект",
    "l.applicationNumber": "Номер на заявката",
    "m.email.text.errorMessage": "Копие от докладваната грешка ще бъде изпратено на посочения електронен адрес",
    "m.email.text.feedback": "Копие от направеното предложение ще бъде изпратено на посочения електронен адрес",
    "m.feedback.general.info":
      "Уважаеми клиенти, формулярът за обратна връзка следва да се използва единствено за предложения и/или мнения относно начина на предоставяне на услугите, достъпността на информацията за тях, възможни подобрения в комуникацията и процесите, свързани с обслужването на клиентите. Чрез формуляра за обратна връзка не се извършват справки по подадени заявки или по предоставени административни услуги. Информация за административните услуги, извършвани от Патентно ведомство, включваща: попълването на исканията за тях, цената на услугите и размера на таксите, можете да получите на място в Центъра за административно обслужване на Патентно ведомство на Република България или след подадено писмено искане по факс или на e-mail: services@bpo.bg. ",
    "m.feedback.general.info.warn":
      "На всякакви други запитвания, искания или предоставени документи, отправени чрез формуляра за обратна връзка, извън посочените по – горе, няма да бъде отговаряно.",
    "validation.field.invalid.applicationNumber": "Невалиден номер на заявката",
    "validation.field.invalid.objectType": "Невалиден тип обект",
    "validation.recaptcha.verification.failed": "Възникна проблем при обработване на заявката",
    ...validationKeysBG.translation,
  },
});

export default messagesBg;
