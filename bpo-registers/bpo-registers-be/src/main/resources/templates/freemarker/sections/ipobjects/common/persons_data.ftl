<#include "../../../common/macros/label.ftl">
<#include "../../../common/macros/labelText.ftl">
<#include "../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>

    <#if !personType?? || !personType?has_content>
        <#assign personType = "OWNER">
    </#if>

    <#assign filteredPersons = []>
    <#if object.ipObject?? && object.ipObject.persons?has_content>
        <#list object.ipObject.persons as x>
            <#if x.id?? && x.id.personRole?? && x.id.personRole == personType>
                <#assign filteredPersons = filteredPersons + [x]>
            </#if>
        </#list>
    </#if>

    <div class="page-unbreakable">
        <@sectionHeading "l.section.persons.data.${personType}.${registerType}"/>
        <#if filteredPersons?has_content>
            <div>
                <table class="table table-striped mt-3">
                    <thead>
                    <tr>
                        <th><@label "l.person.name"/></th>
                        <th><@label "l.person.nationality"/></th>
                        <th><@label "l.person.address"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list filteredPersons as x>
                        <tr>
                            <td>
                                <#if personType == "REPRESENTATIVE" && x.representativeType?? && x.representativeType.description??>
                                    ${x.representativeType.description + ": "}
                                </#if>
                                <#if x.person?? && x.person.name??>
                                    ${x.person.name}
                                </#if>
                            </td>
                            <td>
                                <#if x.person?? && x.person.nationalityCountryCode?? && x.person.nationalityCountryCode.name??>
                                    ${x.person.nationalityCountryCode.name}
                                </#if>
                            </td>
                            <td>
                                <#if x.person?? && x.person.address??>
                                    <#assign address = x.person.address>
                                    <#if address.residenceCountryCode?? && address.residenceCountryCode.name??>
                                        ${address.residenceCountryCode.name}<br/>
                                    </#if>
                                    <#if address.cityName??>
                                        ${address.cityName}<br/>
                                    </#if>
                                    <#if address.addressStreet??>
                                        ${address.addressStreet}<br/>
                                    </#if>
                                </#if>
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