import { RefObject } from "react";
import { getNestedValue } from "../functions";

const getTextContentFromBlockTextComponents = (
  ref: RefObject<HTMLDivElement>,
  sectionClassName: string
): { label: string; text: string }[] => {
  const result: { label: string; text: string }[] = [];
  if (ref.current) {
    const elements = ref.current.querySelectorAll(`${sectionClassName} .block-text`);
    elements.forEach((el) => {
      const labelElement = el.querySelector(".block-text-label");
      const textElements = el.querySelectorAll(".block-text-text");
      if (labelElement && textElements) {
        result.push({
          label: labelElement.textContent,
          text: Array.from(textElements)
            .map((x) => x.textContent)
            .filter((x) => x) //filter non empty only
            .join(" / "),
        });
      }
    });
  }
  return result;
};

const getTextFromTypography = (
  ref: RefObject<HTMLDivElement>,
  sectionClassName: string,
  typographyClassName: string
) => {
  const result: { subheading: string }[] = [];
  if (ref.current) {
    const elements = ref.current.querySelectorAll(`${sectionClassName} ${typographyClassName}`);
    elements.forEach((el) => {
      result.push({ subheading: el.textContent });
    });
  }
  return result;
};

const getContentFromImageComponents = (ref, sectionClassName) => {
  const result = [];
  if (ref.current) {
    const elements = ref.current.querySelectorAll(`${sectionClassName} .block-image`);

    elements.forEach((el) => {
      const labelElement = el.querySelector(".block-image-label");
      const imageElement = el.querySelector(".block-image-image img");

      result.push({
        label: labelElement?.textContent,
        imageSrc: getArrayBufferFromImageElement(imageElement),
        imgWidth: imageElement?.naturalWidth,
        imgHeight: imageElement?.naturalHeight,
      });
    });
  }
  return result;
};

const getArrayBufferFromImageElement = (imgElement) => {
  return new Promise((resolve, reject) => {
    const canvas = document.createElement("canvas");

    const width = imgElement.naturalWidth;
    const height = imgElement.naturalHeight;

    canvas.width = width;
    canvas.height = height;

    const context = canvas.getContext("2d");
    context.drawImage(imgElement, 0, 0, width, height);
    canvas.toBlob((blob) => {
      if (!blob) {
        reject(new Error("Canvas to Blob conversion failed"));
        return;
      }
      const reader = new FileReader();
      reader.onloadend = () => {
        resolve(reader.result);
      };
      reader.onerror = reject;
      reader.readAsArrayBuffer(blob);
    });
  });
};

const getTableContentFromJson = (data, headersMapping) => {
  if (!data || data.length === 0) return;
  const headerRow = headersMapping.map((x) => x.label).filter((x) => !x.startsWith("extraData"));
  let mainHeadersMapping = headersMapping.filter((x) => !x.label.startsWith("extraData"));
  let extraHeadersMapping = headersMapping.filter((x) => x.label.startsWith("extraData"));
  let rows = [];

  for (let i = 0; i < data.length; i++) {
    let recordal = data[i];
    let cells = [];
    let extraDataArr = [];
    for (let j = 0; j < mainHeadersMapping.length; j++) {
      let header = mainHeadersMapping[j].key;
      cells.push(getNestedValue(recordal, header));
    }
    if (extraHeadersMapping && extraHeadersMapping.length > 0) {
      let extraData = getNestedValue(recordal, extraHeadersMapping[0].key);
      if (extraData) {
        for (let k = 0; k < extraData.length; k++) {
          let label = getNestedValue(extraData[k], extraHeadersMapping[1].key);
          let text = getNestedValue(extraData[k], extraHeadersMapping[2].key);
          extraDataArr.push({ label: label, text: text });
        }
      }
    }
    rows.push({ cells: cells, extraData: extraDataArr });
  }
  return { headerRow: headerRow, rows: rows };
};

const getCustomContentFromJson = (data, customSection) => {
  if (!data || data.length === 0 || !customSection) return;
  return { data: data, section: customSection };
};

export const DocumentSectionType = {
  TEXT: "text",
  GRID: "grid",
  LIST: "list",
  TABLE: "table",
  IMAGE: "image",
  IMAGE_CONSECUTIVE: "imageConsecutive",
  CUSTOM: "custom",
};

export const convertSectionDataTextBlock = (componentRef, sectionTitle, sectionClassName, sectionType) => {
  let textContentFromDOM = getTextContentFromBlockTextComponents(componentRef, sectionClassName);
  return { section: sectionTitle, type: sectionType, content: textContentFromDOM };
};

export const convertSectionDataImage = (componentRef, sectionTitle, sectionClassName, sectionType) => {
  let textContentFromDOM = getTextContentFromBlockTextComponents(componentRef, sectionClassName);
  let imageContentFromDOM = getContentFromImageComponents(componentRef, sectionClassName);
  return { section: sectionTitle, type: sectionType, content: [...imageContentFromDOM, ...textContentFromDOM] };
};

export const convertSectionDataTextImageConsecutive = (
  componentRef,
  sectionTitle,
  sectionClassName,
  dividerClassName,
  subheadingClassName,
  sectionType
) => {
  const result = [];
  if (componentRef.current) {
    const elements = componentRef.current.querySelectorAll(`${sectionClassName} ${dividerClassName}`);
    elements.forEach((el, index) => {
      let subheading = getTextFromTypography(componentRef, `${dividerClassName}-${index}`, subheadingClassName);
      let textContentFromDOM = getTextContentFromBlockTextComponents(componentRef, `${dividerClassName}-${index}`);
      let imageContentFromDOM = getContentFromImageComponents(componentRef, `${dividerClassName}-${index}`);
      result.push(...subheading, ...textContentFromDOM, ...imageContentFromDOM);
    });
  }

  return {
    section: sectionTitle,
    type: sectionType,
    content: result,
  };
};

export const convertSectionDataTableJson = (sectionTitle, data, headersMapping, sectionType) => {
  let content = getTableContentFromJson(data, headersMapping);
  return { section: sectionTitle, type: sectionType, content: content };
};

export const convertSectionDataCustom = (sectionTitle, data, customSection) => {
  let content = getCustomContentFromJson(data, customSection);
  return { section: sectionTitle, type: DocumentSectionType.CUSTOM, content: content };
};
