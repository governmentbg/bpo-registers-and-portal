#!/usr/bin/node
const { ExternalConfigManager } = require("@duosoftbg/bpo-frontend-config-manager/dist/cjs/index");

const profile = process.argv[2];
console.log(`Active profile: ${profile}`);

let configServerUrl = "http://duosoft-smallapps.devzone.sirma.bg:8302/bpo-config-server";
if (profile && profile === "stage") {
  configServerUrl = "http://duosoft-smallapps.devzone.sirma.bg:8302/bpo-config-server";
} else if (profile && profile === "production") {
  configServerUrl = "http://duosoft-smallapps.devzone.sirma.bg:8302/bpo-config-server";
}

ExternalConfigManager.run({
  configServerUrl: configServerUrl,
  configName: "bpo-registers-fe",
  profile: profile,
  allProfiles: ["dev", "office", "stage", "production"],
});
