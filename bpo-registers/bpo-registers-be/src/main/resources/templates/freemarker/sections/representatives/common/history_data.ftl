<#include "../../../common/macros/label.ftl">
<#include "../../../common/macros/labelText.ftl">
<#include "../../../common/macros/fixedLabelText.ftl">
<#include "../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading "l.section.history.data"/>
        <#if agentHistory?? && agentHistory?has_content>
            <div>
                <table class="table table-striped mt-3">
                    <tbody>
                    <#list agentHistory as r>
                        <tr>
                            <td colspan="2" style="background: lightgrey">
                                [${r.historyTimestamp?datetime.iso?string("dd.MM.yyyy")}] <#if lang == "bg">${r.historyType.name}<#else>${r.historyType.nameEn}</#if>
                            </td>
                        </tr>
                        <#if r.historyRecord.grounds?has_content && r.historyRecord.grounds.historyGroundDescription?has_content>
                            <tr>
                                <td colspan="2" style="background: lightsteelblue">
                                    <div style="display: inline-block; width: 100%; margin-left: 20px">
                                        ${r.historyRecord.grounds.historyGroundDescription}
                                    </div>
                                </td>
                            </tr>
                        </#if>
                        <tr>
                            <td colspan="2">
                                <div style="display: inline-block; width: 100%; margin-left: 20px">
                                    <#if r.historyRecord.newData?has_content>
                                        <div style="display: inline-block; width: 100%">
                                            <#assign invalidatedStatus = r.historyType.id == "RECORD_INVALIDATED" || r.historyType.id == "RECORD_TEMPORARY_INACTIVATED"/>
                                            <#if registerType == "AGENT">
                                                <#assign newData = r.historyRecord.newData.agent/>
                                            <#elseif registerType == "PARTNERSHIP">
                                                <#assign newData = r.historyRecord.newData.partnership/>
                                            </#if>

                                            <@labelText _label="l.detail.agentCode" _value=newData.agentCode!""/>
                                            <#if newData.representativeType?has_content && registerType == "PARTNERSHIP">
                                                <#if lang == "bg">
                                                    <@labelText _label="l.detail.agentType" _value=newData.representativeType.description!""/>
                                                <#else>
                                                    <@labelText _label="l.detail.agentType" _value=newData.representativeType.descriptionEn!""/>
                                                </#if>
                                            </#if>
                                            <#if invalidatedStatus>
                                                <#if lang == "bg">
                                                    <@labelText _label="l.detail.status" _value=r.historyType.name!""/>
                                                <#else>
                                                    <@labelText _label="l.detail.status" _value=r.historyType.nameEn!""/>
                                                </#if>
                                            </#if>
                                            <#if newData.nameAddress?has_content>
                                                <@labelText _label="l.detail.agentName.${registerType}" _value=newData.nameAddress.name!""/>
                                                <@labelText _label="l.detail.agentNameEn.${registerType}" _value=newData.nameAddress.nameEn!""/>
                                            </#if>
                                            <#if newData.agentSpeciality?has_content>
                                                <#if lang == "bg">
                                                    <@labelText _label="l.detail.ipoArea" _value=newData.agentSpeciality.name!""/>
                                                <#else>
                                                    <@labelText _label="l.detail.ipoArea" _value=newData.agentSpeciality.nameEn!""/>
                                                </#if>
                                            </#if>
                                            <#if registerType == "PARTNERSHIP">
                                                <@labelText _label="l.detail.origin.country" _value=newData.oriCountryCode!""/>
                                            </#if>
                                            <#if registerType == "AGENT">
                                                <@labelText _label="l.detail.speciality" _value=newData.diplomaSpeciality!""/>
                                                <@labelText _label="l.detail.specialityEn" _value=newData.diplomaSpecialityEn!""/>
                                                <@labelText _label="l.detail.certification.country" _value=newData.qualificationCountryCode!""/>
                                            </#if>

                                            <#if newData.nameAddress?has_content && newData.nameAddress.address?has_content>
                                            <#--AddressHistory-->
                                                <br/><br/>
                                                <#assign newAddressBG = [newData.nameAddress.address.countryCode!"", newData.nameAddress.address.zipCode!"", newData.nameAddress.address.cityName!"", newData.nameAddress.address.address!""]>
                                                <#assign nonEmptyNewAddressBG = newAddressBG?filter(it -> it?has_content)>
                                                <#assign newAddressEN = [newData.nameAddress.address.countryCode!"", newData.nameAddress.address.zipCode!"", newData.nameAddress.address.cityNameEn!"", newData.nameAddress.address.addressEn!""]>
                                                <#assign nonEmptyNewAddressEN = newAddressEN?filter(it -> it?has_content)>
                                                <@labelText _label="l.detail.official.address.${registerType}" _value=nonEmptyNewAddressBG?join(", ")!""/>
                                                <@labelText _label="l.detail.official.addressEn.${registerType}" _value=nonEmptyNewAddressEN?join(", ")!""/>
                                                <@labelText _label="l.detail.telephone" _value=newData.nameAddress.address.phone!""/>
                                                <@labelText _label="l.detail.email" _value=newData.nameAddress.address.email!""/>
                                                <@labelText _label="l.detail.website" _value=newData.nameAddress.address.website!""/>

                                            <#--CorrespondenceAddressHistory-->
                                                <br/><br/>
                                                <#assign newCaAddressBG = [newData.nameAddress.address.caZipCode!"", newData.nameAddress.address.caCity!"", newData.nameAddress.address.caAddress!""]>
                                                <#assign nonEmptyNewCaAddressBG = newCaAddressBG?filter(it -> it?has_content)>
                                                <#assign newCaAddressEN = [newData.nameAddress.address.caZipCode!"", newData.nameAddress.address.caCityEn!"", newData.nameAddress.address.caAddressEn!""]>
                                                <#assign nonEmptyNewCaAddressEN = newCaAddressEN?filter(it -> it?has_content)>

                                                <#if nonEmptyNewCaAddressBG?has_content || nonEmptyNewCaAddressEN?has_content || newData.nameAddress.address.caPhone?has_content || newData.nameAddress.address.caEmail?has_content || newData.nameAddress.address.caWebsite?has_content>
                                                    <@labelText _label="l.detail.ca.address" _value=nonEmptyNewCaAddressBG?join(", ")!""/>
                                                    <@labelText _label="l.detail.ca.addressEn" _value=nonEmptyNewCaAddressEN?join(", ")!""/>
                                                    <@labelText _label="l.detail.ca.telephone" _value=newData.nameAddress.address.caPhone!""/>
                                                    <@labelText _label="l.detail.ca.email" _value=newData.nameAddress.address.caEmail!""/>
                                                    <@labelText _label="l.detail.ca.website" _value=newData.nameAddress.address.caWebsite!""/>
                                                </#if>
                                            </#if>

                                            <#--PartnershipAgentHistory-->
                                            <#if registerType == "PARTNERSHIP" && newData.partnershipMembers?has_content>
                                                <@labelText _label="l.detail.partnership.participants" _value=newData.partnershipMembers?map(x -> x.agentName + " : " + x.agentCode)?join(", ")!"" _width="100%"/>
                                            </#if>
                                        </div>
                                    </#if>
                                </div>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        <#else>
            <@label "l.no.data"/>
        </#if>
    </div>
</#escape>