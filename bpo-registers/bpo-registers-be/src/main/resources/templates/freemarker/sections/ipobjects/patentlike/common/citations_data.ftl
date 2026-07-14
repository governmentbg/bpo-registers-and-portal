<#include "../../../../common/macros/label.ftl">
<#include "../../../../common/macros/labelText.ftl">
<#include "../../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading 'l.section.citations.data'/>
        <#if object.citations?has_content>
            <div>
                <table class="table table-striped mt-3">
                    <thead>
                    <tr>
                        <th><@label "l.citation.refNbr"/></th>
                        <th><@label "l.citation.refDescription"/></th>
                        <th><@label "l.citation.refClaims"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list object.citations as x>
                        <tr>
                            <td>
                                <#if x.id?? && x.id.refNumber??>
                                    ${x.id.refNumber}
                                </#if>
                            </td>
                            <td>
                                <#if x.refDescription??>
                                    ${x.refDescription}
                                </#if>
                            </td>
                            <td>
                                <#if x.refClaims??>
                                    ${x.refClaims}
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