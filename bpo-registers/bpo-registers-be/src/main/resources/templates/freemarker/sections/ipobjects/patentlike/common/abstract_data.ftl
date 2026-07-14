<#include "../../../../common/macros/label.ftl">
<#include "../../../../common/macros/labelText.ftl">
<#include "../../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading 'l.section.abstract.data.${registerType}'/>
        <#if object.mainAbstract?has_content>
            ${object.mainAbstract}
        <#else>
            <@label "l.no.data"/>
        </#if>
    </div>
</#escape>