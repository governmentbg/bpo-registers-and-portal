<#include "../../../../common/macros/label.ftl">
<#include "../../../../common/macros/labelText.ftl">
<#include "../../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading 'l.section.description.data'/>
        <#if object.markDescription?has_content>
            ${object.markDescription}
        <#else>
            <@label "l.according.representation"/>
        </#if>
    </div>
</#escape>