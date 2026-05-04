import { isDate } from "date-fns";
import {
  AsyncCallArgs,
  isArrayEmpty,
  isArrayNotEmpty,
  isNotEmpty,
  parseDate,
  SidebarPageData,
} from "@duosoftbg/bpo-components";
import { messagesBg, messagesEn } from "../i18n";
import { NomenclatureConfigArray, NomenclatureGroupData } from "../types/nomenclatures";
import { downloadFile } from "../axios/api/services";
import { object } from "yup";

export const parseDateString = (value, originalValue) => {
  return isDate(originalValue) ? originalValue : parseDate(originalValue);
};

export const exportUniqueAccesssRoles = (nomenclatures: NomenclatureGroupData) => {
  let roles = [];
  if (isArrayNotEmpty(nomenclatures)) {
    let roleArrays = nomenclatures.map((value) => value.accessRoles);
    if (roleArrays) {
      roleArrays.forEach((array) => {
        roles = roles.concat(array);
      });
    }
  }
  if (isArrayEmpty(roles)) {
    return [];
  }

  return Array.from(new Set(roles));
};

export const exportUniqueAccessRolesFromNomenclaturesArray = (nomenclatureArray: NomenclatureConfigArray) => {
  let roles = [];
  if (nomenclatureArray) {
    nomenclatureArray.forEach((value) => {
      const exportedRoles = exportUniqueAccesssRoles(value.nomenclatures);
      if (isArrayNotEmpty(exportedRoles)) {
        roles.push.apply(roles, exportedRoles);
      }
    });
  }
  if (isArrayEmpty(roles)) {
    return [];
  }

  return Array.from(new Set(roles));
};

export const convertNomenclatureConfigToSidebarData = (
  nomenclatures: NomenclatureGroupData
): Array<SidebarPageData> => {
  if (nomenclatures) {
    let map = nomenclatures.map((value) => {
      return {
        id: value.name,
        title: messagesBg.translation[value.name],
        titleEn: messagesEn.translation[value.name],
        accessRoles: value.accessRoles,
        href: value.href,
      };
    });

    if (map) {
      return map;
    }
  }

  return [];
};

export const convertNomenclatureConfigArrayToSidebarData = (
  nomenclatureArray: NomenclatureConfigArray
): Array<SidebarPageData> => {
  if (nomenclatureArray) {
    let map = nomenclatureArray.map((group) => {
      let groupName = group.groupName;
      return {
        id: groupName,
        title: messagesBg.translation[groupName],
        titleEn: messagesEn.translation[groupName],
        accessRoles: exportUniqueAccesssRoles(group.nomenclatures),
        children: convertNomenclatureConfigToSidebarData(group.nomenclatures),
      };
    });

    if (map) {
      return map;
    }
  }

  return [];
};

export const getNestedValue = (obj, key) => {
  const value = key.split(".").reduce((acc, part) => {
    if (acc === undefined || acc === null) return acc;
    return acc[part];
  }, obj);

  return value !== undefined && value !== null ? (typeof value === "number" ? String(value) : value) : value;
};

export const arrayContainsArray = (mainArray, subArray) => {
  if (isArrayEmpty(subArray)) {
    return false;
  }
  return subArray.every((item) => mainArray.includes(item));
};

export const handleFileDownload = (bucketName, fileLocation, fileName, asyncCall) => {
  const asyncCallArgs: AsyncCallArgs = {
    promise: downloadFile(bucketName, fileLocation),
    onSuccess: (response) => {
      var downloadLink = window.document.createElement("a");
      downloadLink.href = window.URL.createObjectURL(
        new Blob([response.data], { type: response?.headers?.["content-type"] })
      );
      downloadLink.download = fileName;
      document.body.appendChild(downloadLink);
      downloadLink.click();
      document.body.removeChild(downloadLink);
    },
  };
  asyncCall(asyncCallArgs);
};
