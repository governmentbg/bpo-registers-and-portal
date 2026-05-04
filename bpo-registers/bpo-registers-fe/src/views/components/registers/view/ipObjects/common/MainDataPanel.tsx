import { BlockText, GridContainer, isNotEmpty, iTxt } from "@duosoftbg/bpo-components";
import React, { useEffect, useState } from "react";
import { RegisterType } from "../../../../../../utils/constants";
import i18n from "i18next";
import { useTranslation } from "react-i18next";
import PriorityDataPanel from "./PriorityDataPanel";

const MainDataPanel = ({ data, registerType }) => {
  const { t } = useTranslation();

  const [registrationNumber, setRegistrationNumber] = useState(null);

  useEffect(() => {
    if (registerType === RegisterType.EU_PATENT) {
      if (isNotEmpty(data?.ipObject?.registrationNumber)) {
        setRegistrationNumber("EP" + data?.ipObject?.registrationNumber);
      }
    } else {
      setRegistrationNumber(data?.ipObject?.registrationNumber);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [data, registerType]);

  return (
    <>
      <div className={"main-data-panel"}>
        <GridContainer spacing={3}>
          <BlockText
            propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
            withGrid
            label={`l.object.applicationNumber.${registerType}`}
            text={data?.id}
          />
          <BlockText
            propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
            withGrid
            label={`l.object.filingDate.${registerType}`}
            text={data?.ipObject?.filingDate}
          />
          <BlockText
            propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
            withGrid
            label={`l.object.registrationNumber.${registerType}`}
            text={registrationNumber}
          />
          <BlockText
            propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
            withGrid
            label={`l.object.registrationDate.${registerType}`}
            text={data?.ipObject?.registrationDate}
          />
          <BlockText
            propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
            withGrid
            label={`l.object.expirationDate.${registerType}`}
            text={data?.ipObject?.expirationDate}
          />
          <BlockText
            propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
            withGrid
            label={`l.object.status`}
            text={iTxt(
              i18n.language,
              data?.ipObject?.status?.bpoOnlineStatus,
              data?.ipObject?.status?.bpoOnlineStatusEn
            )}
          />
          {[
            RegisterType.PATENT,
            RegisterType.EU_PATENT,
            RegisterType.UTILITY_MODEL,
            RegisterType.SPC,
            RegisterType.PLANT_BREED,
          ].includes(registerType) && (
            <>
              <BlockText
                propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                withGrid
                label={`l.object.lastPaidYear`}
                text={data?.lastPaidYear}
              />
              <BlockText
                propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                withGrid
                label={`l.object.paymentDate`}
                text={data?.renewalFeesLastPaid}
              />
              <BlockText
                propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                withGrid
                label={`l.object.renewalFeesPaidTo`}
                text={data?.renewalFeesPaidTo}
              />
            </>
          )}
          <BlockText
            propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
            withGrid
            label={`l.object.notInForceDate`}
            text={data?.notInForceDate}
          />
          {registerType !== RegisterType.MARK && (
            <BlockText
              propsGrid={{ xs: 12, sm: 12, md: 12, lg: 12 }}
              withGrid
              label={`l.object.title.${registerType}`}
              text={
                registerType === RegisterType.GEO_INDICATION
                  ? iTxt(
                      i18n.language,
                      data?.ipObject?.title,
                      data?.markTranslation && data?.markTranslation !== ""
                        ? data?.markTranslation
                        : data?.markTransliteration && data?.markTransliteration !== ""
                        ? data?.markTransliteration
                        : data?.ipObject?.title
                    )
                  : data?.ipObject?.title
              }
            />
          )}
          {registerType === RegisterType.SPC && (
            <>
              <BlockText
                propsGrid={{ xs: 12, sm: 12, md: 12, lg: 12 }}
                withGrid
                label={`l.spcAbstractData`}
                text={data?.mainAbstract}
              />
            </>
          )}
          {registerType === RegisterType.DESIGN && (
            <>
              <BlockText
                propsGrid={{ xs: 12, sm: 12, md: 12, lg: 12 }}
                withGrid
                label={`l.object.titleEn.${registerType}`}
                text={data?.ipObject?.titleEn}
              />
              <BlockText
                propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                withGrid
                label={`l.object.subtype`}
                text={data?.ipObject?.objectSubType?.description}
              />
            </>
          )}
          {(registerType === RegisterType.MARK || registerType === RegisterType.GEO_INDICATION) && (
            <>
              <BlockText
                propsGrid={{ xs: 12, sm: 12, md: 12, lg: 12 }}
                withGrid
                label={`l.object.disclaimer`}
                text={data?.disclaimer ? data?.disclaimer : t("l.no.declared")}
              />
              <BlockText
                propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                withGrid
                label={`l.object.acquired.distinctiveness`}
                text={data?.acquiredDistinctiveness ? t("l.yes") : t("l.no")}
              />
              <BlockText
                propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                withGrid
                label={`l.object.markKind`}
                text={iTxt(i18n.language, data?.markKind?.description, data?.markKind?.descriptionEn)}
              />
              <BlockText
                propsGrid={{ xs: 12, sm: 12, md: 6, lg: 4 }}
                withGrid
                label={`l.object.markType`}
                text={iTxt(
                  i18n.language,
                  data?.ipObject?.objectSubType?.description,
                  data?.ipObject?.objectSubType?.descriptionEn
                )}
              />
            </>
          )}
        </GridContainer>
      </div>
      <PriorityDataPanel data={data} registerType={registerType} t={t} />
    </>
  );
};
export default MainDataPanel;
