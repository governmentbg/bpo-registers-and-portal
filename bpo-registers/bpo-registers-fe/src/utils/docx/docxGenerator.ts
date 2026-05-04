import {
  AlignmentType,
  Document,
  Footer,
  HeadingLevel,
  ImageRun,
  Packer,
  PageNumber,
  Paragraph,
  ShadingType,
  Table,
  TableCell,
  TableRow,
  TextRun,
  WidthType,
} from "docx";
import { saveAs } from "file-saver";
import { format } from "date-fns";
import { CircularArray } from "../circularArray";
import { isArrayNotEmpty } from "@duosoftbg/bpo-components";
import { DocumentSectionType } from "./docxJsonConvertor";
import { CustomSection, generateAgentHistoryTable, generatePartnershipHistoryTable } from "./customSectionGenerator";

export const generateDocx = async (jsonData, fileName, objectId, docTitle, t) => {
  let paragraphs = [];
  paragraphs = await processSections(paragraphs, jsonData, t);

  const doc = new Document({
    styles: {
      paragraphStyles: [
        {
          id: "Normal",
          name: "Normal",
          run: {
            font: "Arial",
          },
        },
        {
          id: "Heading2",
          name: "Heading 2",
          basedOn: "Normal",
          next: "Normal",
          quickFormat: true,
          run: {
            font: "Arial",
            size: 24, // 12pt font (since docx uses half-points)
            bold: true,
            color: "000000", // Black color
          },
        },
        {
          id: "Heading3",
          name: "Heading 3",
          basedOn: "Normal",
          next: "Normal",
          quickFormat: true,
          run: {
            font: "Arial",
            size: 22, // 12pt font (since docx uses half-points)
            bold: true,
            color: "000000", // Black color
          },
        },
      ],
    },
    sections: [
      {
        properties: {},
        // headers: {
        //   default: new Header({
        //     children: [
        //       new Paragraph({
        //         text: t("l.docx.header"),
        //         heading: HeadingLevel.HEADING_2,
        //         alignment: AlignmentType.CENTER,
        //       }),
        //     ],
        //   }),
        // },
        footers: {
          default: new Footer({
            children: [
              new Paragraph({
                children: [
                  new TextRun({
                    children: [t("l.docx.page"), PageNumber.CURRENT, t("l.docx.of"), PageNumber.TOTAL_PAGES],
                    font: "Arial",
                    color: "000000",
                  }),
                ],
                heading: HeadingLevel.HEADING_3,
                alignment: AlignmentType.CENTER,
              }),
            ],
          }),
        },
        children: [
          new Table({
            rows: [
              new TableRow({
                children: [
                  new TableCell({
                    children: [
                      new Paragraph({
                        alignment: AlignmentType.LEFT,
                        children: [
                          new TextRun({
                            text: t("l.docx.header"),
                            bold: true,
                            size: 28,
                          }),
                        ],
                      }),
                    ],
                  }),
                  new TableCell({
                    children: [
                      new Paragraph({
                        alignment: AlignmentType.RIGHT,
                        children: [
                          new TextRun({
                            text: docTitle + " " + format(new Date(), "dd.MM.yyyy HH:mm"),
                            bold: true,
                            size: 28,
                          }),
                        ],
                      }),
                    ],
                  }),
                ],
              }),
            ],
            borders: {
              top: { style: "none" },
              bottom: { style: "none" },
              left: { style: "none" },
              right: { style: "none" },
              insideVertical: { style: "none" },
              insideHorizontal: { style: "none" },
            },
            width: {
              size: 100,
              type: WidthType.PERCENTAGE,
            },
          }),
          ...paragraphs,
        ],
      },
    ],
  });

  Packer.toBlob(doc).then((blob) => {
    saveAs(blob, fileName + ".docx");
  });
};

const processSections = async (paragraphs, jsonData, t) => {
  for (let i = 0; i < jsonData.length; i++) {
    paragraphs = [
      ...paragraphs,
      new Paragraph({}),
      new Paragraph({
        text: jsonData[i].section,
        heading: HeadingLevel.HEADING_2,
        alignment: AlignmentType.LEFT,
        keepNext: true,
      }),
    ];
    if (jsonData[i].type === DocumentSectionType.TEXT) {
      paragraphs = processTextSection(paragraphs, jsonData[i].content, t);
    } else if (jsonData[i].type === DocumentSectionType.GRID) {
      paragraphs = processGridSection(paragraphs, jsonData[i].content, t);
    } else if (jsonData[i].type === DocumentSectionType.LIST) {
      paragraphs = processListSection(paragraphs, jsonData[i].content, t);
    } else if (jsonData[i].type === DocumentSectionType.IMAGE) {
      paragraphs = await processImageSection(paragraphs, jsonData[i].content, t);
    } else if (jsonData[i].type === DocumentSectionType.IMAGE_CONSECUTIVE) {
      paragraphs = await processTextImageSectionConsecutive(paragraphs, jsonData[i].content, t);
    } else if (jsonData[i].type === DocumentSectionType.TABLE) {
      paragraphs = processTableSection(paragraphs, jsonData[i].content, t);
    } else if (jsonData[i].type === DocumentSectionType.CUSTOM) {
      paragraphs = processCustomSection(paragraphs, jsonData[i].content, t);
    }
  }
  return paragraphs;
};

const processTextSection = (paragraphs, content, t) => {
  if (content && content.length > 0) {
    paragraphs = [
      ...paragraphs,
      ...content?.map((item) =>
        item.label
          ? new Paragraph({
              children: [
                new TextRun({ text: item.label, bold: true }),
                new TextRun({ text: ": ", bold: true }),
                new TextRun({ text: item.text.trim() }),
              ],
            })
          : new Paragraph({ text: item.text.trim() })
      ),
    ];
  } else {
    paragraphs = [
      ...paragraphs,
      new Paragraph({
        text: t("l.panel.no.data"),
      }),
    ];
  }
  return paragraphs;
};

const processListSection = (paragraphs, content, t) => {
  paragraphs = [
    ...paragraphs,
    isArrayNotEmpty(content)
      ? new Paragraph({
          children: content?.map((item) => new TextRun({ text: item.text + "; " })),
        })
      : new Paragraph({
          text: t("l.panel.no.data"),
        }),
  ];
  return paragraphs;
};

const processImageSection = async (paragraphs, content, t) => {
  const maxWidth = 300;
  const maxHeight = 400;
  let images = content.filter((x) => x.imageSrc);
  let texts = content.filter((x) => x.text);

  if (images && images.length > 0) {
    for (let i = 0; i < images.length; i++) {
      let originalWidth = images[i].imgWidth;
      let originalHeight = images[i].imgHeight;
      let aspectRatio = originalWidth / originalHeight;

      let newWidth = originalWidth;
      let newHeight = originalHeight;

      // Scale down if larger than max dimensions
      if (newWidth > maxWidth) {
        newWidth = maxWidth;
        newHeight = newWidth / aspectRatio;
      }
      if (newHeight > maxHeight) {
        newHeight = maxHeight;
        newWidth = newHeight * aspectRatio;
      }

      paragraphs = [
        ...paragraphs,
        new Paragraph({ text: images[i].label, keepNext: true }),
        new Paragraph({
          children: [
            new ImageRun({
              data: await images[i].imageSrc,
              transformation: {
                width: newWidth,
                height: newHeight,
              },
            }),
          ],
        }),
      ];
    }
  }

  if (texts && texts.length > 0) {
    paragraphs = [
      ...paragraphs,
      ...texts?.map((item) =>
        item.label
          ? new Paragraph({
              children: [
                new TextRun({ text: item.label, bold: true }),
                new TextRun({ text: ": ", bold: true }),
                new TextRun({ text: item.text.trim() }),
              ],
            })
          : new Paragraph({ text: item.text.trim() })
      ),
    ];
  }
  return paragraphs;
};

const processTextImageSectionConsecutive = async (paragraphs, content, t) => {
  const maxWidth = 300;
  const maxHeight = 400;

  if (content && content.length > 0) {
    for (const el of content) {
      if (el.imageSrc) {
        paragraphs = [...paragraphs, new Paragraph({})];
        let originalWidth = el.imgWidth;
        let originalHeight = el.imgHeight;
        let aspectRatio = originalWidth / originalHeight;

        let newWidth = originalWidth;
        let newHeight = originalHeight;

        // Scale down if larger than max dimensions
        if (newWidth > maxWidth) {
          newWidth = maxWidth;
          newHeight = newWidth / aspectRatio;
        }
        if (newHeight > maxHeight) {
          newHeight = maxHeight;
          newWidth = newHeight * aspectRatio;
        }

        paragraphs = [
          ...paragraphs,
          new Paragraph({ text: el.label, keepNext: true }),
          new Paragraph({
            children: [
              new ImageRun({
                data: await el.imageSrc,
                transformation: {
                  width: newWidth,
                  height: newHeight,
                },
              }),
            ],
          }),
        ];
        paragraphs = [...paragraphs, new Paragraph({})];
      } else if (el.text) {
        paragraphs = [
          ...paragraphs,
          el.label
            ? new Paragraph({
                children: [
                  new TextRun({ text: el.label, bold: true }),
                  new TextRun({ text: ": ", bold: true }),
                  new TextRun({ text: el.text.trim() }),
                ],
              })
            : new Paragraph({ text: el.text.trim() }),
        ];
      } else if (el.subheading) {
        paragraphs = [
          ...paragraphs,
          new Paragraph({}),
          new Paragraph({
            text: el.subheading,
            heading: HeadingLevel.HEADING_3,
            alignment: AlignmentType.LEFT,
            keepNext: true,
          }),
        ];
      }
    }
  }
  return paragraphs;
};

const processGridSection = (paragraphs, content, t) => {
  let labels = content.map((item) => item.label);
  let tableHeaders = Array.from(new Set(labels)).map((e) => String(e));

  let cellTexts = [];
  if (isArrayNotEmpty(tableHeaders)) {
    const circularArrayHeaders = new CircularArray<string>(tableHeaders);
    for (let i = 0; i < content.length; i++) {
      let currentHeader = circularArrayHeaders.getNext();
      if (currentHeader === content[i].label) {
        cellTexts = [...cellTexts, content[i].text];
      } else {
        cellTexts = [...cellTexts, ""];
        --i;
      }
    }

    while (cellTexts.length % tableHeaders.length !== 0) {
      cellTexts = [...cellTexts, ""];
    }

    let rows = [];
    let cells = [];
    for (let i = 0; i < cellTexts.length; i++) {
      cells = [
        ...cells,
        new TableCell({
          children: [new Paragraph({ text: cellTexts[i] })],
        }),
      ];
      if ((i + 1) % tableHeaders.length === 0) {
        rows = [
          ...rows,
          new TableRow({
            children: cells,
          }),
        ];
        cells = [];
      }
    }

    const table = new Table({
      width: {
        size: 100,
        type: WidthType.PERCENTAGE,
      },
      rows: [
        new TableRow({
          tableHeader: true,
          children: tableHeaders?.map(
            (item) =>
              new TableCell({
                children: [new Paragraph({ children: [new TextRun({ text: item + "", bold: true })] })],
              })
          ),
        }),
        ...rows,
      ],
    });
    paragraphs = [...paragraphs, table];
  } else {
    paragraphs = [
      ...paragraphs,
      new Paragraph({
        text: t("l.panel.no.data"),
      }),
    ];
  }
  return paragraphs;
};

const processTableSection = (paragraphs, content, t) => {
  if (content) {
    let rows = [];
    for (let i = 0; i < content?.rows?.length; i++) {
      rows = [
        ...rows,
        new TableRow({
          children: content?.rows[i].cells?.map(
            (x) =>
              new TableCell({
                children: [new Paragraph({ text: x })],
              })
          ),
        }),
      ];

      if (content?.rows[i].extraData && content?.rows[i].extraData.length > 0) {
        rows = [
          ...rows,
          new TableRow({
            children: [
              new TableCell({
                shading: {
                  fill: "D3D3D3",
                  type: ShadingType.CLEAR,
                  color: "auto",
                },
                columnSpan: content.headerRow.length,
                children: content?.rows[i].extraData.map((x) =>
                  x.label
                    ? new Paragraph({
                        children: [
                          new TextRun({ text: x.label, bold: true }),
                          new TextRun({ text: ": ", bold: true }),
                          new TextRun({ text: x.text.trim() }),
                        ],
                      })
                    : new Paragraph({ text: x.text.trim() })
                ),
              }),
            ],
          }),
        ];
      }
    }

    const table = new Table({
      width: {
        size: 100,
        type: WidthType.PERCENTAGE,
      },
      rows: [
        new TableRow({
          tableHeader: true,
          children: content?.headerRow?.map(
            (item) =>
              new TableCell({
                children: [new Paragraph({ children: [new TextRun({ text: item + "", bold: true })] })],
              })
          ),
        }),
        ...rows,
      ],
    });
    paragraphs = [...paragraphs, table];
  } else {
    paragraphs = [
      ...paragraphs,
      new Paragraph({
        text: t("l.panel.no.data"),
      }),
    ];
  }
  return paragraphs;
};

const processCustomSection = (paragraphs, content, t) => {
  if (content && content.data) {
    let generatedSection;
    switch (content.section) {
      case CustomSection.AGENT_HISTORY:
        generatedSection = generateAgentHistoryTable(content.data, t);
        break;
      case CustomSection.PARTNERSHIP_HISTORY:
        generatedSection = generatePartnershipHistoryTable(content.data, t);
        break;
    }
    paragraphs = [...paragraphs, generatedSection];
  } else {
    paragraphs = [
      ...paragraphs,
      new Paragraph({
        text: t("l.panel.no.data"),
      }),
    ];
  }
  return paragraphs;
};
