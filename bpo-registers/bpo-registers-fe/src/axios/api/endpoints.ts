const ipObjectsBaseUrl = "/ip-objects";
const marksBaseUrl = "/marks";
const recordalsBaseUrl = "/recordals";
const objectRelationshipsBaseUrl = "/object-relationships";
const personObjectRelationshipsBaseUrl = "/person-object-relationships";
const geoIndicationsBaseUrl = "/geo-indications";
const patentsBaseUrl = "/patents";
const designsBaseUrl = "/designs";
const agentsBaseUrl = "/agents";
const euPatentsBaseUrl = "/eu-patents";
const spcsBaseUrl = "/spc";
const utilityModelsBaseUrl = "/utility-models";
const plantsBreedsBaseUrl = "/plants-breeds";
const decisionsBaseUrl = "/legal-decisions";
const nomenclaturesBaseUrl = "/nomenclature";
const thumbnailsBaseUrl = "/files/thumbnails";
const receiptsBaseUrl = "/receipts";

const ApiEndpoints = {
  registers: {
    search: {
      marks: `${marksBaseUrl}`,
      geoIndications: `${geoIndicationsBaseUrl}`,
      patents: `${patentsBaseUrl}`,
      designs: `${designsBaseUrl}`,
      agents: `${agentsBaseUrl}`,
      euPatents: `${euPatentsBaseUrl}`,
      spcs: `${spcsBaseUrl}`,
      utilityModels: `${utilityModelsBaseUrl}`,
      plantsBreeds: `${plantsBreedsBaseUrl}`,
      decisions: `${decisionsBaseUrl}`,
      combined: `${ipObjectsBaseUrl}`,
      autocomplete: {
        registrationNumbers: `${ipObjectsBaseUrl}/autocomplete/registrationNumber`,
        filingNumbers: `${ipObjectsBaseUrl}/autocomplete/filingNumber`,
        representatives: `${ipObjectsBaseUrl}/autocomplete/representative`,
      },
      publications: {
        numbers: `${ipObjectsBaseUrl}/publication/numbers`,
        sections: `${ipObjectsBaseUrl}/publication/sections`,
      },
      statuses: `${ipObjectsBaseUrl}/statuses`,
    },
    view: {
      external: {
        byAlternateKey: `${ipObjectsBaseUrl}/by-alternate-key`,
      },
      agents: {
        byAgentCode: `${agentsBaseUrl}/view/by-agent-code/{agentCode}`,
      },
      patents: {
        byId: `${patentsBaseUrl}/by-id`,
      },
      designs: {
        byId: `${designsBaseUrl}/by-id`,
      },
      euPatents: {
        byId: `${euPatentsBaseUrl}/by-id`,
      },
      spcs: {
        byId: `${spcsBaseUrl}/by-id`,
      },
      utilityModels: {
        byId: `${utilityModelsBaseUrl}/by-id`,
      },
      plantsBreeds: {
        byId: `${plantsBreedsBaseUrl}/by-id`,
      },
      marks: {
        byId: `${marksBaseUrl}/by-id`,
      },
      geoIndications: {
        byId: `${geoIndicationsBaseUrl}/by-id`,
      },
      decisions: {
        byId: `${decisionsBaseUrl}/by-id`,
        byObjectId: `${decisionsBaseUrl}/by-object-id`,
      },
    },
    recordals: {
      getRecordalsByObjectId: `${recordalsBaseUrl}?objectId={objectId}`,
      getOppositionsByObjectId: `${recordalsBaseUrl}/oppositions?objectId={objectId}`,
      getCancellationsByObjectId: `${recordalsBaseUrl}/cancellations?objectId={objectId}`,
    },
    objectRelationships: {
      getObjectRelationshipsByObjectId: `${objectRelationshipsBaseUrl}?objectId={objectId}`,
    },
    personObjectRelationships: {
      getPersonObjectRelationshipsByPersonName: `${personObjectRelationshipsBaseUrl}`,
      getPersonObjectRelationshipsByPersonNameCount: `${personObjectRelationshipsBaseUrl}/count`,
    },
    nomenclatures: {
      objectTypes: {
        getAll: `${nomenclaturesBaseUrl}/object-type`,
      },
      legalDecisionTypes: {
        getAll: `${nomenclaturesBaseUrl}/legal-decision-type`,
      },
      legalDecisionGroundTypes: {
        getAll: `${nomenclaturesBaseUrl}/legal-decision-ground-type`,
      },
      markKinds: {
        getAll: `${nomenclaturesBaseUrl}/markkind`,
      },
      ipoAreas: {
        getAll: `${nomenclaturesBaseUrl}/agent-speciality`,
      },
      agentStatuses: {
        getAll: `${nomenclaturesBaseUrl}/agent-status`,
      },
      agentCities: {
        getAll: `${agentsBaseUrl}/cities`,
      },
      countries: {
        getAll: `${nomenclaturesBaseUrl}/country`,
      },
      viennaClasses: {
        autocomplete: `${nomenclaturesBaseUrl}/viennaclass/autocomplete`,
      },
      locarnoClasses: {
        autocomplete: `${nomenclaturesBaseUrl}/locarnoclass/autocomplete`,
      },
      ipc: {
        autocomplete: `${nomenclaturesBaseUrl}/patent-ipc-class/autocomplete`,
      },
      cpc: {
        autocomplete: `${nomenclaturesBaseUrl}/patent-cpc-class/autocomplete`,
      },
      objectSubtypes: {
        byObjectRange: `${nomenclaturesBaseUrl}/objectsubtype/objecttype`,
      },
      niceClasses: {
        getAll: `${nomenclaturesBaseUrl}/nice-classes`,
      },
      plantTaxon: {
        getAllLatinClassifications: `${nomenclaturesBaseUrl}/plant-taxon/latin-classification`,
        getAllBgClassifications: `${nomenclaturesBaseUrl}/plant-taxon/bg-classification`,
      },
      representativeTypes: {
        getAll: `${nomenclaturesBaseUrl}/reptype`,
      },
    },
    files: {
      download: "/files/download",
      thumbnails: {
        getByObjectIdAndType: `${thumbnailsBaseUrl}/ipobjects?objectId={objectId}&objectType={objectType}`,
      },
    },
    receipts: {
      pdf: {
        view: `${receiptsBaseUrl}/{registerType}/view/{id}/{lang}`,
        search: `${receiptsBaseUrl}/{registerType}/search/{lang}`,
      },
      excel: {
        search: `${receiptsBaseUrl}/{registerType}/search/{lang}/excel`,
        personObjects: `${receiptsBaseUrl}/person-objects/{lang}/excel`,
      },
    },
  },
  portal: {
    panels: { get: `/panels/register` },
  },
};

export { ApiEndpoints };
