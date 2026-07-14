<#include "../../../common/macros/label.ftl">
<#include "../../../common/macros/labelText.ftl">
<#include "../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading "l.section.relations.data.${registerType}"/>
        <#if agentRelations?has_content>
            <div>
                <table class="table table-striped mt-3">
                    <thead>
                    <tr>
                        <th><#if registerType=="AGENT">
                                <@label "l.table.head.partnershipName"/>
                            <#else>
                                <@label "l.table.head.agentName"/>
                            </#if>
                        </th>
                        <th><@label "l.table.head.agentCode"/></th>
                        <th>
                            <#if registerType=="AGENT">
                                <@label "l.table.head.representativeType"/>
                            <#else>
                                <@label "l.table.head.agentSpeciality"/>
                            </#if>
                        </th>
                        <th><@label "l.table.head.ipoArea"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list agentRelations as x>
                        <#if x.agent?has_content>
                            <tr>
                                <td>
                                    <#if lang == "bg">
                                        ${x.name!""}
                                    <#else>
                                        ${x.agent.nameEn!""}
                                    </#if>
                                </td>
                                <td>
                                    ${x.agent.agentCode!""}
                                </td>
                                <td>
                                    <#if registerType == "AGENT">
                                        <#if x.agent.representativeType?has_content>
                                            <#if lang == "bg">
                                                ${x.agent.representativeType.description!""}
                                            <#else>
                                                ${x.agent.representativeType.descriptionEn!""}
                                            </#if>
                                        </#if>
                                    <#else>
                                        <#if lang == "bg">
                                            ${x.agent.speciality!""}
                                        <#else>
                                            ${x.agent.specialityEn!""}
                                        </#if>
                                    </#if>
                                </td>
                                <td>
                                    <#if x.agent.agentSpeciality?has_content>
                                        <#if lang == "bg">
                                            ${x.agent.agentSpeciality.name!""}
                                        <#else>
                                            ${x.agent.agentSpeciality.nameEn!""}
                                        </#if>
                                    </#if>
                                </td>
                            </tr>
                        </#if>
                    </#list>
                    </tbody>
                </table>
            </div>
        <#else>
            <@label "l.no.data"/>
        </#if>
    </div>
</#escape>