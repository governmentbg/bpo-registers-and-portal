<#include "../../../../common/macros/label.ftl">
<#include "../../../../common/macros/labelText.ftl">
<#include "../../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading 'l.section.vienna.classes.data'/>
        <#assign viennaClasses = []>
        <#if object.markAttachments?has_content>
            <#list object.markAttachments as x>
                <#if x.viennaClasses?has_content>
                    <#list x.viennaClasses as y>
                        <#if y.id.categoryId?has_content>
                            <#assign viennaClasses = viennaClasses + [y.id.categoryId]>
                        </#if>
                    </#list>
                </#if>
            </#list>
        </#if>
        <#if viennaClasses?has_content>
            ${viennaClasses?join("; ")}
        <#else>
            <@label "l.no.data"/>
        </#if>
    </div>
</#escape>