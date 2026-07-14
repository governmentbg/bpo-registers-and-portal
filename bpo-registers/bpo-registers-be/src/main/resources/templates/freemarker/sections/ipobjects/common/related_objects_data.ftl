<#include "../../../common/macros/label.ftl">
<#include "../../../common/macros/labelText.ftl">
<#include "../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading 'l.section.related.objects.data'/>
        <#if objectRelationships?? && objectRelationships?has_content>
            <div>
                <table class="table table-striped mt-3">
                    <thead>
                    <tr>
                        <th><@label "l.related.object.relationshipType"/></th>
                        <th><@label "l.related.object.description.${registerType}"/></th>
                        <th><@label "l.related.object.link"/></th>
                        <th><@label "l.related.object.additional.data"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list objectRelationships as r>
                        <tr>
                            <td>
                                <#if lang == 'bg' && r.relationshipName??>
                                    ${r.relationshipName}
                                </#if>
                                <#if lang != 'bg' && r.relationshipNameEn??>
                                    ${r.relationshipNameEn}
                                </#if>
                            </td>
                            <td>
                                <#if lang == 'bg' && r.id?? && r.id.description??>
                                    ${r.id.description}
                                </#if>
                                <#if lang != 'bg' && r.id?? && r.id.description??>
                                    ${r.id.descriptionEn}
                                </#if>
                            </td>
                            <td>
                                <#if r.id?? && r.id.objectReference?? && r.id.objectReference != "NONE">
                                    ${r.id.objectReference}
                                </#if>
                            </td>
                            <td>
                                <#if r.id?? && r.id.relationshipType?? && r.id.applicationType??>
                                    <#if r.id.relationshipType == "ТМ" && r.id.applicationType == "WO">
                                        <#if r.registrationNumber??>
                                            <@label "l.related.object.registration.number"/>: ${r.registrationNumber}
                                            <br/>
                                        </#if>
                                        <#if r.priorityDate??>
                                            <@label "l.related.object.priority.date"/>: ${r.priorityDate?date.iso?string("dd.MM.yyyy")}
                                            <br/>
                                        </#if>
                                    </#if>
                                    <#if r.id.relationshipType == "ТМ" && r.id.applicationType == "EM">
                                        <#if r.registrationNumber??>
                                            <@label "l.related.object.registration.number"/>: ${r.registrationNumber}
                                            <br/>
                                        </#if>
                                        <#if r.priorityDate??>
                                            <@label "l.related.object.priority.date"/>: ${r.priorityDate?date.iso?string("dd.MM.yyyy")}
                                            <br/>
                                        </#if>
                                        <#if r.serveMessageDate??>
                                            <@label "l.related.object.serve.message.date"/>: ${r.serveMessageDate?date.iso?string("dd.MM.yyyy")}
                                            <br/>
                                        </#if>
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