import { axiosClientCore, axiosClientPortal, axiosClientRegisters } from "../axiosClient";
import { ApiEndpoints } from "./endpoints";
import qs from "qs";
import { MAX_AUTOCOMPLETE_RESULTS } from "@duosoftbg/bpo-components";
import i18n from "i18next";

// const commonFilterPostFunction = async (url, params) => {
//   const response = await axiosClientRegisters.post(url, {
//     params,
//     paramsSerializer: (params) => {
//       return qs.stringify(params);
//     },
//   });
//   return response.data;
// };

const commonFilterFunction = async (url, params, withIndeces = true) => {
  const response = await axiosClientRegisters.get(url, {
    params,
    paramsSerializer: (params) => {
      return qs.stringify(params, { indices: withIndeces });
    },
  });
  return response.data;
};

const commonFilterFunctionCore = async (url, params, withIndeces = true) => {
  const response = await axiosClientCore.get(url, {
    params,
    paramsSerializer: (params) => {
      return qs.stringify(params, { indices: withIndeces });
    },
  });
  return response.data;
};

export const searchMarks = (params) => async () => {
  const response = await axiosClientRegisters.post(ApiEndpoints.registers.search.marks, params);
  return response.data;
};

export const searchPatents = (params) => async () => {
  const response = await axiosClientRegisters.post(ApiEndpoints.registers.search.patents, params);
  return response.data;
};

export const searchDesigns = (params) => async () => {
  const response = await axiosClientRegisters.post(ApiEndpoints.registers.search.designs, params);
  return response.data;
};

export const searchCombined = (params) => async () => {
  const response = await axiosClientRegisters.post(ApiEndpoints.registers.search.combined, params);
  return response.data;
};

export const searchAgents = (params) => async () => {
  const response = await axiosClientRegisters.post(ApiEndpoints.registers.search.agents, {
    ...params,
    language: i18n.language,
  });
  return response.data;
};

export const searchEuPatents = (params) => async () => {
  const response = await axiosClientRegisters.post(ApiEndpoints.registers.search.euPatents, params);
  return response.data;
};

export const searchSpcs = (params) => async () => {
  const response = await axiosClientRegisters.post(ApiEndpoints.registers.search.spcs, params);
  return response.data;
};

export const searchUtilityModels = (params) => async () => {
  const response = await axiosClientRegisters.post(ApiEndpoints.registers.search.utilityModels, params);
  return response.data;
};

export const searchPlantsBreeds = (params) => async () => {
  const response = await axiosClientRegisters.post(ApiEndpoints.registers.search.plantsBreeds, params);
  return response.data;
};

export const searchDecisions = (params) => async () => {
  const response = await axiosClientRegisters.post(ApiEndpoints.registers.search.decisions, params);
  return response.data;
};

export const searchGeoIndications = (params) => async () => {
  const response = await axiosClientRegisters.post(ApiEndpoints.registers.search.geoIndications, params);
  return response.data;
};
export const getMarkKinds = () => async () => {
  const response = await axiosClientRegisters.get(ApiEndpoints.registers.nomenclatures.markKinds.getAll);
  return response.data;
};

export const getObjectTypes = () => async () => {
  const response = await axiosClientRegisters.get(ApiEndpoints.registers.nomenclatures.objectTypes.getAll);
  return response.data;
};

export const getLegalDecisionTypes = () => async () => {
  const response = await axiosClientRegisters.get(ApiEndpoints.registers.nomenclatures.legalDecisionTypes.getAll);
  return response.data;
};

export const getLegalDecisionGroundTypes = () => async () => {
  const response = await axiosClientRegisters.get(ApiEndpoints.registers.nomenclatures.legalDecisionGroundTypes.getAll);
  return response.data;
};

export const getRecordalsByObjectId = (objectId) => async () => {
  const response = await axiosClientRegisters.get(
    ApiEndpoints.registers.recordals.getRecordalsByObjectId.replaceAll("{objectId}", objectId)
  );
  return response.data;
};

export const getOppositionsByObjectId = (objectId) => async () => {
  const response = await axiosClientRegisters.get(
    ApiEndpoints.registers.recordals.getOppositionsByObjectId.replaceAll("{objectId}", objectId)
  );
  return response.data;
};

export const getCancellationsByObjectId = (objectId) => async () => {
  const response = await axiosClientRegisters.get(
    ApiEndpoints.registers.recordals.getCancellationsByObjectId.replaceAll("{objectId}", objectId)
  );
  return response.data;
};

export const getAgentCities = (cityName) => async () => {
  return commonFilterFunction(ApiEndpoints.registers.nomenclatures.agentCities.getAll, {
    language: i18n.language,
    cityName: cityName.name,
  });
};

export const getIpoAreas = () => async () => {
  const response = await axiosClientRegisters.get(ApiEndpoints.registers.nomenclatures.ipoAreas.getAll);
  return response.data;
};
export const getAgentStatuses = () => async () => {
  const response = await axiosClientRegisters.get(ApiEndpoints.registers.nomenclatures.agentStatuses.getAll);
  return response.data;
};
export const getNiceClasses = (onlyGoods) => async () => {
  return commonFilterFunctionCore(ApiEndpoints.registers.nomenclatures.niceClasses.getAll, { onlyGoods });
};

export const getCountries = () => async () => {
  const response = await axiosClientRegisters.get(ApiEndpoints.registers.nomenclatures.countries.getAll);
  return response.data;
};

export const autocompleteViennaClasses =
  ({ name }) =>
  async () => {
    return commonFilterFunction(
      ApiEndpoints.registers.nomenclatures.viennaClasses.autocomplete,
      { name, page: 0, pageSize: MAX_AUTOCOMPLETE_RESULTS },
      false
    );
  };

export const autocompleteLocarnoClasses =
  ({ name }) =>
  async () => {
    return commonFilterFunction(
      ApiEndpoints.registers.nomenclatures.locarnoClasses.autocomplete,
      { name, page: 0, pageSize: MAX_AUTOCOMPLETE_RESULTS },
      false
    );
  };

export const autocompleteIpc =
  ({ name }) =>
  async () => {
    return commonFilterFunction(
      ApiEndpoints.registers.nomenclatures.ipc.autocomplete,
      { name, page: 0, pageSize: MAX_AUTOCOMPLETE_RESULTS },
      false
    );
  };

export const autocompleteIpcCodes =
  ({ name }) =>
  async () => {
    let result = await commonFilterFunction(
      ApiEndpoints.registers.nomenclatures.ipc.autocomplete,
      { name, page: 0, pageSize: MAX_AUTOCOMPLETE_RESULTS },
      false
    );
    return Array.from(
      new Set(
        result?.map((option) =>
          (
            option.id.sectionCode +
            option.id.classCode +
            option.id.subclassCode +
            option.id.groupCode +
            option.id.subgroupCode
          )?.trim()
        )
      )
    );
  };

export const autocompleteCpc =
  ({ name }) =>
  async () => {
    return commonFilterFunction(
      ApiEndpoints.registers.nomenclatures.cpc.autocomplete,
      { name, page: 0, pageSize: MAX_AUTOCOMPLETE_RESULTS },
      false
    );
  };

export const autocompleteCpcCodes =
  ({ name }) =>
  async () => {
    let result = await commonFilterFunction(
      ApiEndpoints.registers.nomenclatures.cpc.autocomplete,
      { name, page: 0, pageSize: MAX_AUTOCOMPLETE_RESULTS },
      false
    );
    return Array.from(
      new Set(
        result?.map((option) =>
          (
            option.id.sectionCode +
            option.id.classCode +
            option.id.subclassCode +
            option.id.groupCode +
            option.id.subgroupCode
          )?.trim()
        )
      )
    );
  };

export const getObjectSubtypesByObjectRange = (objectRange) => async () => {
  return commonFilterFunction(
    ApiEndpoints.registers.nomenclatures.objectSubtypes.byObjectRange,
    { objectTypes: objectRange },
    false
  );
};

export const getObjectPublicationNumbers =
  ({ year, objectTypes }) =>
  async () => {
    return commonFilterFunction(ApiEndpoints.registers.search.publications.numbers, { objectTypes, year }, false);
  };

export const getObjectPublicationSections =
  ({ year, number, objectTypes }) =>
  async () => {
    return commonFilterFunction(
      ApiEndpoints.registers.search.publications.sections,
      { objectTypes, year, number },
      false
    );
  };

export const getObjectStatuses =
  ({ objectTypes }) =>
  async () => {
    return commonFilterFunction(ApiEndpoints.registers.search.statuses, { objectTypes }, false);
  };

export const autocompleteRegistrationNumbers =
  ({ name, objectTypes }) =>
  async () => {
    return commonFilterFunction(
      ApiEndpoints.registers.search.autocomplete.registrationNumbers,
      { name, page: 0, pageSize: MAX_AUTOCOMPLETE_RESULTS, objectTypes },
      false
    );
  };

export const autocompleteFilingNumbers =
  ({ name, objectTypes }) =>
  async () => {
    return commonFilterFunction(
      ApiEndpoints.registers.search.autocomplete.filingNumbers,
      { name, page: 0, pageSize: MAX_AUTOCOMPLETE_RESULTS, objectTypes },
      false
    );
  };

export const autocompleteRepresentatives =
  ({ name, objectTypes }) =>
  async () => {
    return commonFilterFunction(
      ApiEndpoints.registers.search.autocomplete.representatives,
      { name, page: 0, pageSize: MAX_AUTOCOMPLETE_RESULTS, objectTypes },
      false
    );
  };

export const getAllBgClassifications = () => async () => {
  const response = await commonFilterFunction(
    ApiEndpoints.registers.nomenclatures.plantTaxon.getAllBgClassifications,
    false
  );

  return response;
};

export const getAllLatinClassifications = () => async () => {
  const response = await commonFilterFunction(
    ApiEndpoints.registers.nomenclatures.plantTaxon.getAllLatinClassifications,
    false
  );

  return response;
};

export const getAllRepresentativeTypes = () => async () => {
  const response = await commonFilterFunction(ApiEndpoints.registers.nomenclatures.representativeTypes.getAll, false);
  return response;
};

export const getAgentByAgentCode = (agentCode, representativeTypes) => async () => {
  return commonFilterFunction(
    ApiEndpoints.registers.view.agents.byAgentCode.replace("{agentCode}", agentCode),
    { representativeTypes },
    false
  );
};

export const getPatentById = (id) => async () => {
  return commonFilterFunction(ApiEndpoints.registers.view.patents.byId, { id }, false);
};

export const getDesignById = (id) => async () => {
  return commonFilterFunction(ApiEndpoints.registers.view.designs.byId, { id }, false);
};
export const getEuPatentById = (id) => async () => {
  return commonFilterFunction(ApiEndpoints.registers.view.euPatents.byId, { id }, false);
};
export const getSpcById = (id) => async () => {
  return commonFilterFunction(ApiEndpoints.registers.view.spcs.byId, { id }, false);
};
export const getUtilityModelById = (id) => async () => {
  return commonFilterFunction(ApiEndpoints.registers.view.utilityModels.byId, { id }, false);
};
export const getPlantsBreedsById = (id) => async () => {
  return commonFilterFunction(ApiEndpoints.registers.view.plantsBreeds.byId, { id }, false);
};

export const getTrademarkById = (id) => async () => {
  return commonFilterFunction(ApiEndpoints.registers.view.marks.byId, { id }, false);
};
export const getGeoIndicationById = (id) => async () => {
  return commonFilterFunction(ApiEndpoints.registers.view.geoIndications.byId, { id }, false);
};
export const getDecisionById = (id) => async () => {
  return commonFilterFunction(ApiEndpoints.registers.view.decisions.byId, { id }, false);
};
export const getDecisionByObjectId = (id) => async () => {
  return commonFilterFunction(ApiEndpoints.registers.view.decisions.byObjectId, { id }, false);
};

export const downloadFile = (bucket, fullPath) => async () => {
  return await axiosClientRegisters.get(ApiEndpoints.registers.files.download, {
    params: {
      bucket: bucket,
      fullPath: fullPath,
    },
    responseType: "blob",
  });
};

export const getObjectRelationshipsByObjectId = (objectId) => async () => {
  const response = await axiosClientRegisters.get(
    ApiEndpoints.registers.objectRelationships.getObjectRelationshipsByObjectId.replaceAll("{objectId}", objectId)
  );
  return response.data;
};

export const getPersonObjectRelationshipsByPersonName = (personName, objectId) => async () => {
  const response = await axiosClientRegisters.get(
    ApiEndpoints.registers.personObjectRelationships.getPersonObjectRelationshipsByPersonName,
    {
      params: {
        personName: personName,
        objectId: objectId,
      },
    }
  );
  return response.data;
};

export const getPersonObjectRelationshipsByPersonNameCount = (personName) => async () => {
  const response = await axiosClientRegisters.get(
    ApiEndpoints.registers.personObjectRelationships.getPersonObjectRelationshipsByPersonNameCount,
    {
      params: {
        personName: personName,
      },
    }
  );
  return response.data;
};

export const getIpObjectViewReceiptPdf = (registerType, objectId, lang) => async () => {
  return await axiosClientRegisters.get(
    ApiEndpoints.registers.receipts.pdf.view
      .replaceAll("{registerType}", registerType)
      .replaceAll("{id}", objectId)
      .replaceAll("{lang}", lang),
    { responseType: "blob" }
  );
};

export const getIpObjectSearchReceiptPdf = (registerType, filter, lang) => async () => {
  return await axiosClientRegisters.post(
    ApiEndpoints.registers.receipts.pdf.search.replaceAll("{registerType}", registerType).replaceAll("{lang}", lang),
    filter,
    {
      responseType: "blob",
    }
  );
};

export const getIpObjectSearchReceiptExcel = (registerType, filter, lang) => async () => {
  return await axiosClientRegisters.post(
    ApiEndpoints.registers.receipts.excel.search.replaceAll("{registerType}", registerType).replaceAll("{lang}", lang),
    filter,
    {
      responseType: "blob",
    }
  );
};

export const getPersonObjectsReceiptExcel = (personName, objectId, lang) => async () => {
  return await axiosClientRegisters.get(
    ApiEndpoints.registers.receipts.excel.personObjects.replaceAll("{lang}", lang),
    {
      params: {
        personName: personName,
      },
      responseType: "blob",
    }
  );
};

export const getObjectIdByAlternateKey = (alternateKey) => async () => {
  const response = await axiosClientRegisters.get(ApiEndpoints.registers.view.external.byAlternateKey, {
    params: { alternateKey: alternateKey },
  });
  return response.data;
};

export const getPanelsData = () => async () => {
  const response = await axiosClientPortal.get(ApiEndpoints.portal.panels.get);
  return response.data;
};
