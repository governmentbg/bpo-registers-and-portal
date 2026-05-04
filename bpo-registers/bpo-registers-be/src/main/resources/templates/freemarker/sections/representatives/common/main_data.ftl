<#include "../../../common/macros/label.ftl">
<#include "../../../common/macros/labelText.ftl">
<#include "../../../common/macros/labelTwoTexts.ftl">
<#include "../../../common/macros/labelBool.ftl">
<#include "../../../common/macros/labelDate.ftl">
<#include "../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading 'l.section.main.data.${registerType}'/>
        <#if agent?has_content>
            <div style="display: inline-block; width: 100%">
                <@labelTwoTexts _label="l.detail.agentName.${registerType}" _value=agent.name!"" _value2=agent.agent.nameEn!""/>
                <#if registerType == 'PARTNERSHIP'>
                    <@labelTwoTexts _label="l.detail.agentType" _value=agent.agent.representativeType.description!"" _value2=agent.agent.representativeType.descriptionEn!""/>
                </#if>
                <@labelTwoTexts _label="l.detail.ipoArea" _value=agent.agent.agentSpeciality.name!"" _value2=agent.agent.agentSpeciality.nameEn!""/>
                <#if registerType == 'AGENT'>
                    <@labelTwoTexts _label="l.detail.certification.country" _value=agent.agent.qualifCountryCode.name!"" _value2=agent.agent.qualifCountryCode.nameEn!""/>
                </#if>
                <@labelTwoTexts _label="l.detail.origin.country.${registerType}" _value=agent.nationalityCountryCode.name!"" _value2=agent.nationalityCountryCode.nameEn!""/>
                <#if registerType == 'AGENT'>
                    <@labelTwoTexts _label="l.detail.speciality" _value=agent.agent.speciality!"" _value2=agent.agent.specialityEn!""/>
                </#if>
                <br/>
                <#if agent.address?has_content && agent.agent.address?has_content>
                    <#assign addressBG = [agent.address.residenceCountryCode.name!"", agent.address.zipCode!"", agent.address.cityName!"", agent.address.addressStreet!""]>
                    <#assign nonEmptyaddressBG = addressBG?filter(it -> it?has_content)>
                    <#assign addressEN = [agent.address.residenceCountryCode.nameEn!"", agent.address.zipCode!"", agent.agent.address.cityNameEn!"", agent.agent.address.addressStreetEn!""]>
                    <#assign nonEmptyaddressEN = addressEN?filter(it -> it?has_content)>
                    <@labelTwoTexts _label="l.detail.official.address.${registerType}" _value=nonEmptyaddressBG?join(", ")!"" _value2=nonEmptyaddressEN?join(", ")!""/>
                    <@labelText _label="l.detail.telephone" _value=agent.address.telephone!"" _width="100%"/>
                    <@labelText _label="l.detail.email" _value=agent.address.email!"" _width="100%"/>
                    <@labelText _label="l.detail.website" _value=agent.agent.address.website!"" _width="100%"/>
                </#if>
                <br/>
                <#if agent.agent.address.addressStreetCa?has_content || agent.agent.address.addressStreetCaEn?has_content || agent.agent.address.zipCodeCa?has_content || agent.agent.address.phoneCa?has_content || agent.agent.address.emailCa?has_content || agent.agent.address.faxCa?has_content || agent.agent.address.cityNameCa?has_content || agent.agent.address.cityNameCaEn?has_content>
                    <#assign addressBG = [agent.agent.address.zipCodeCa!"", agent.agent.address.addressStreetCa!"", agent.agent.address.cityNameCa!""]>
                    <#assign nonEmptyaddressBG = addressBG?filter(it -> it?has_content)>
                    <#assign addressEN = [agent.agent.address.zipCodeCa!"", agent.agent.address.addressStreetCaEn!"", agent.agent.address.cityNameCaEn!""]>
                    <#assign nonEmptyaddressEN = addressEN?filter(it -> it?has_content)>
                    <@labelTwoTexts _label="l.detail.ca.address" _value=nonEmptyaddressBG?join(", ")!"" _value2=nonEmptyaddressEN?join(", ")!""/>
                    <@labelText _label="l.detail.ca.telephone" _value=agent.agent.address.phoneCa!"" _width="100%"/>
                    <@labelText _label="l.detail.ca.email" _value=agent.agent.address.emailCa!"" _width="100%"/>
                </#if>
            </div>
        <#else>
            <@label "l.no.data"/>
        </#if>
    </div>
</#escape>