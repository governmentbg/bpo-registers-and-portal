<#include "../../../../common/macros/label.ftl">
<#include "../../../../common/macros/labelText.ftl">
<#include "../../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading 'l.section.colours.data'/>
        <#assign colours = []>
        <#if object.markAttachments?has_content>
            <#list object.markAttachments as x>
                <#if x.colorDescription?has_content>
                    <#assign colours = colours + [x.colorDescription]>
                </#if>
            </#list>
        </#if>
        <#if colours?has_content>
            ${colours?join("; ")}
        <#else>
            <@label "l.according.representation"/>
        </#if>
    </div>
</#escape>