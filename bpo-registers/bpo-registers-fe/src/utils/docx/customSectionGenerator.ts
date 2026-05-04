import { Paragraph, ShadingType, Table, TableCell, TableRow, TextRun, WidthType } from "docx";
import i18n from "i18next";
import { concatNotEmptyBy, isArrayNotEmpty, isEmpty, isNotEmpty, iTxt } from "@duosoftbg/bpo-components";
import { i18nKeyByCode } from "../labels";
import { RegisterType } from "../constants";

export const CustomSection = {
  AGENT_HISTORY: "AGENT_HISTORY",
  PARTNERSHIP_HISTORY: "PARTNERSHIP_HISTORY",
};

export const generateAgentHistoryTable = (data, t) => {
  const viewType = RegisterType.AGENT;
  if (data) {
    let rows = [];
    for (let i = 0; i < data?.length; i++) {
      let record = data[i];
      rows.push(
        new TableRow({
          children: [
            new TableCell({
              shading: {
                fill: "D3D3D3",
                type: ShadingType.CLEAR,
                color: "auto",
              },
              children: [
                new Paragraph({
                  text:
                    iTxt(i18n.language, record.historyType.name, record.historyType.nameEn) +
                    " [" +
                    record.historyTimestamp.substring(0, 10) +
                    "]",
                }),
              ],
            }),
          ],
        })
      );

      let newData = record?.historyRecord?.newData?.agent;
      let paragraphs = [];

      if (newData?.agentCode) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t("l.detail.agentCode"), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({ text: newData?.agentCode }),
            ],
          })
        );
      }

      const invalidatedStatus =
        record?.historyType?.id === "RECORD_INVALIDATED" || record?.historyType?.id === "RECORD_TEMPORARY_INACTIVATED";
      if (invalidatedStatus && record?.historyType?.name) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t("l.detail.status"), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({ text: iTxt(i18n.language, record?.historyType?.name, record?.historyType?.nameEn) }),
            ],
          })
        );
      }

      if (newData?.nameAddress?.name) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t(i18nKeyByCode(viewType, "l.detail.agentName")), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({ text: newData?.nameAddress?.name }),
            ],
          })
        );
      }

      if (newData?.nameAddress?.nameEn) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t(i18nKeyByCode(viewType, "l.detail.agentNameEn")), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({ text: newData?.nameAddress?.nameEn }),
            ],
          })
        );
      }

      if (newData?.agentSpeciality?.name) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t("l.detail.ipoArea"), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({
                text: iTxt(i18n.language, newData?.agentSpeciality?.name, newData?.agentSpeciality?.nameEn),
              }),
            ],
          })
        );
      }

      if (newData?.diplomaSpeciality) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t("l.detail.speciality"), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({ text: newData?.diplomaSpeciality }),
            ],
          })
        );
      }

      if (newData?.diplomaSpecialityEn) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t("l.detail.specialityEn"), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({ text: newData?.diplomaSpecialityEn }),
            ],
          })
        );
      }

      if (newData?.qualificationCountryCode) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t("l.detail.certification.country"), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({ text: newData?.qualificationCountryCode }),
            ],
          })
        );
      }

      fillAddress(paragraphs, newData, t, viewType);
      fillCorrespondenceAddress(paragraphs, newData, t);

      paragraphs.push(new Paragraph({}));

      rows.push(
        new TableRow({
          children: [
            new TableCell({
              children: paragraphs,
            }),
          ],
        })
      );
    }

    return new Table({
      width: {
        size: 100,
        type: WidthType.PERCENTAGE,
      },
      rows: rows,
    });
  }
  return;
};

export const generatePartnershipHistoryTable = (data, t) => {
  const viewType = RegisterType.PARTNERSHIP;
  if (data) {
    let rows = [];
    for (let i = 0; i < data?.length; i++) {
      let record = data[i];
      rows.push(
        new TableRow({
          children: [
            new TableCell({
              shading: {
                fill: "D3D3D3",
                type: ShadingType.CLEAR,
                color: "auto",
              },
              children: [
                new Paragraph({
                  text:
                    iTxt(i18n.language, record.historyType.name, record.historyType.nameEn) +
                    " [" +
                    record.historyTimestamp.substring(0, 10) +
                    "]",
                }),
              ],
            }),
          ],
        })
      );

      let newData = record?.historyRecord?.newData?.partnership;
      let paragraphs = [];

      if (newData?.agentCode) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t("l.detail.agentCode"), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({ text: newData?.agentCode }),
            ],
          })
        );
      }

      if (newData?.representativeType?.description) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t("l.detail.agentType"), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({
                text: iTxt(
                  i18n.language,
                  newData?.representativeType?.description,
                  newData?.representativeType?.descriptionEn
                ),
              }),
            ],
          })
        );
      }

      const invalidatedStatus =
        record?.historyType?.id === "RECORD_INVALIDATED" || record?.historyType?.id === "RECORD_TEMPORARY_INACTIVATED";
      if (invalidatedStatus && record?.historyType?.name) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t("l.detail.status"), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({ text: iTxt(i18n.language, record?.historyType?.name, record?.historyType?.nameEn) }),
            ],
          })
        );
      }

      if (newData?.nameAddress?.name) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t(i18nKeyByCode(viewType, "l.detail.agentName")), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({ text: newData?.nameAddress?.name }),
            ],
          })
        );
      }

      if (newData?.nameAddress?.nameEn) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t(i18nKeyByCode(viewType, "l.detail.agentNameEn")), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({ text: newData?.nameAddress?.nameEn }),
            ],
          })
        );
      }

      if (newData?.agentSpeciality?.name) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t("l.detail.ipoArea"), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({
                text: iTxt(i18n.language, newData?.agentSpeciality?.name, newData?.agentSpeciality?.nameEn),
              }),
            ],
          })
        );
      }

      if (newData?.oriCountryCode) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t(i18nKeyByCode(viewType, "l.detail.origin.country")), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({ text: newData?.oriCountryCode }),
            ],
          })
        );
      }

      fillAddress(paragraphs, newData, t, viewType);
      fillCorrespondenceAddress(paragraphs, newData, t);

      //AGENTS
      if (isArrayNotEmpty(newData?.partnershipMembers)) {
        const formatMemberRecords = (members) => {
          if (isArrayNotEmpty(members)) {
            let formattedArray = members.map((item) => `${item["agentName"]} (${item["agentCode"]})`);
            return concatNotEmptyBy("; ")(...formattedArray);
          }
        };

        const newMembers = newData?.partnershipMembers;

        if (newMembers) {
          paragraphs.push(
            new Paragraph({
              children: [
                new TextRun({ text: t("l.detail.partnership.participants"), bold: true }),
                new TextRun({ text: ": ", bold: true }),
                new TextRun({
                  text: formatMemberRecords(newMembers),
                }),
              ],
            })
          );
        }
      }

      paragraphs.push(new Paragraph({}));

      rows.push(
        new TableRow({
          children: [
            new TableCell({
              children: paragraphs,
            }),
          ],
        })
      );
    }

    return new Table({
      width: {
        size: 100,
        type: WidthType.PERCENTAGE,
      },
      rows: rows,
    });
  }
  return;
};

const fillAddress = (paragraphs, newData, t, viewType) => {
  const address = newData?.nameAddress?.address;
  if (isNotEmpty(address)) {
    const newAddressBG = concatNotEmptyBy(", ")(
      address?.countryCode,
      address?.zipCode,
      address?.cityName,
      address?.address
    );
    const newAddressEN = concatNotEmptyBy(", ")(
      address?.countryCode,
      address?.zipCode,
      address?.cityNameEn,
      address?.addressEn
    );

    if (newAddressBG) {
      paragraphs.push(
        new Paragraph({
          children: [
            new TextRun({ text: t(i18nKeyByCode(viewType, "l.detail.official.address")), bold: true }),
            new TextRun({ text: ": ", bold: true }),
            new TextRun({ text: newAddressBG }),
          ],
        })
      );
    }

    if (newAddressEN) {
      paragraphs.push(
        new Paragraph({
          children: [
            new TextRun({ text: t(i18nKeyByCode(viewType, "l.detail.official.addressEn")), bold: true }),
            new TextRun({ text: ": ", bold: true }),
            new TextRun({ text: newAddressEN }),
          ],
        })
      );
    }

    if (address?.phone) {
      paragraphs.push(
        new Paragraph({
          children: [
            new TextRun({ text: t("l.detail.phone"), bold: true }),
            new TextRun({ text: ": ", bold: true }),
            new TextRun({
              text: address?.phone,
            }),
          ],
        })
      );
    }

    if (address?.email) {
      paragraphs.push(
        new Paragraph({
          children: [
            new TextRun({ text: t("l.detail.email"), bold: true }),
            new TextRun({ text: ": ", bold: true }),
            new TextRun({
              text: address?.email,
            }),
          ],
        })
      );
    }

    if (address?.website) {
      paragraphs.push(
        new Paragraph({
          children: [
            new TextRun({ text: t("l.detail.website"), bold: true }),
            new TextRun({ text: ": ", bold: true }),
            new TextRun({
              text: address?.website,
            }),
          ],
        })
      );
    }
  }
};

const fillCorrespondenceAddress = (paragraphs, newData, t) => {
  const address = newData?.nameAddress?.address;
  if (isNotEmpty(newData?.nameAddress?.address)) {
    const newCaAddressBG = concatNotEmptyBy(", ")(
      newData?.nameAddress?.address?.caZipCode,
      newData?.nameAddress?.address?.caCity,
      newData?.nameAddress?.address?.caAddress
    );

    const newCaAddressEN = concatNotEmptyBy(", ")(
      newData?.nameAddress?.address?.caZipCode,
      newData?.nameAddress?.address?.caCityEn,
      newData?.nameAddress?.address?.caAddressEn
    );

    const emptyHistoryCa = isEmpty(
      concatNotEmptyBy(",")(
        newCaAddressBG,
        newCaAddressEN,
        newData?.nameAddress?.address?.caPhone,
        newData?.nameAddress?.address?.caEmail,
        newData?.nameAddress?.address?.caWebsite
      )
    );

    if (!emptyHistoryCa) {
      if (newCaAddressBG) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t("l.detail.ca.address"), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({ text: newCaAddressBG }),
            ],
          })
        );
      }

      if (newCaAddressEN) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t("l.detail.ca.addressEn"), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({ text: newCaAddressEN }),
            ],
          })
        );
      }
      if (address?.caPhone) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t("l.detail.ca.phone"), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({
                text: address?.caPhone,
              }),
            ],
          })
        );
      }

      if (address?.caEmail) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t("l.detail.ca.email"), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({
                text: address?.caEmail,
              }),
            ],
          })
        );
      }

      if (address?.caWebsite) {
        paragraphs.push(
          new Paragraph({
            children: [
              new TextRun({ text: t("l.detail.ca.website"), bold: true }),
              new TextRun({ text: ": ", bold: true }),
              new TextRun({
                text: address?.caWebsite,
              }),
            ],
          })
        );
      }
    }
  }
};
