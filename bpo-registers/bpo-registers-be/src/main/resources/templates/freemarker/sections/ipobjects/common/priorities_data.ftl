<#include "../../../common/macros/label.ftl">
<#include "../../../common/macros/labelText.ftl">
<#include "../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading 'l.section.priorities.data.${registerType}'/>
        <#if object.ipObject.priorities?has_content>
            <div>
                <table class="table table-striped mt-3">
                    <thead>
                    <tr>
                        <th><@label "l.priority.country.${registerType}"/></th>
                        <th><@label "l.priority.number.${registerType}"/></th>
                        <th><@label "l.priority.date.${registerType}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list object.ipObject.priorities as x>
                        <tr>
                            <td>
                                <#if x.country??>
                                    ${x.country.name + " (" + x.country.id + ")"}
                                </#if>
                            </td>
                            <td>
                                <#if x.priorityNumber??>
                                    ${x.priorityNumber}
                                </#if>
                            </td>
                            <td>
                                <#if x.priorityDate??>
                                    ${x.priorityDate?date.iso?string("dd.MM.yyyy")}
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