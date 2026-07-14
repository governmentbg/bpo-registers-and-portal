<#include "../../../common/macros/label.ftl">
<#include "../../../common/macros/labelText.ftl">
<#include "../../../common/macros/fixedLabelText.ftl">
<#include "../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading 'l.section.recordal.data.${registerType}'/>
        <#if recordals?? && recordals?has_content>
            <div>
                <table class="table table-striped mt-3">
                    <thead>
                    <tr>
                        <th><@label "l.table.head.recordalNumber"/></th>
                        <th><@label "l.table.head.recordalType"/></th>
                        <th><@label "l.table.head.recordalDate"/></th>
                        <th><@label "l.table.head.invalidationDate"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list recordals as r>
                        <tr>
                            <td>
                                <#if r.recordalNumber??>
                                    ${r.recordalNumber}
                                </#if>
                            </td>
                            <td>
                                <#if r.recordalType?? && r.recordalType.description??>
                                    ${r.recordalType.description}
                                </#if>
                            </td>
                            <td>
                                <#if r.registrationDate??>
                                    ${r.registrationDate?date.iso?string("dd.MM.yyyy")}
                                <#else>
                                    <@label "l.table.head.recordalDate.empty"/>
                                </#if>
                            </td>
                            <td>
                                <#if r.invalidationDate??>
                                    ${r.invalidationDate?date.iso?string("dd.MM.yyyy")}
                                </#if>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <#if r.details?has_content>
                                    <div style="display: inline-block; width: 100%; margin-left: 20px">
                                        <#list r.details as d>
                                            <@fixedLabelText _label=d.type.description _value=(d.description!"")?xml _width="100%"/>
                                        </#list>
                                    </div>
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