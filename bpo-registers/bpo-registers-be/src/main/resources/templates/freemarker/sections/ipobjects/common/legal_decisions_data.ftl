<#include "../../../common/macros/label.ftl">
<#include "../../../common/macros/labelText.ftl">
<#include "../../../common/macros/fixedLabelText.ftl">
<#include "../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading 'l.section.decisions.data'/>
        <#if decisions?? && decisions?has_content>
            <div>
                <table class="table table-striped mt-3">
                    <thead>
                    <tr>
                        <th><@label "l.table.head.decision.docType"/></th>
                        <th><@label "l.table.head.decision.docNumber"/></th>
                        <th><@label "l.table.head.decision.docDate"/></th>
                        <th><@label "l.table.head.decision.link"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list decisions as r>
                        <tr>
                            <td>
                                <#if r.documentType?? && r.documentType.name??>
                                    ${r.documentType.name}
                                </#if>
                            </td>
                            <td>
                                <#if r.documentNumber??>
                                    ${r.documentNumber}
                                </#if>
                            </td>
                            <td>
                                <#if r.documentDate??>
                                    ${r.documentDate}
                                </#if>
                            </td>
                            <td>
                                <#if r.attachment?? && r.attachment.fileName??>
                                    ${r.attachment.fileName}
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