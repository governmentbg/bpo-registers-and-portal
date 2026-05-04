<#include "../../../../common/macros/label.ftl">
<#include "../../../../common/macros/labelText.ftl">
<#include "../../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading 'l.section.nice.classes.data'/>
        <#if object.niceClasses?has_content>
            <#list object.niceClasses as r>
                ${r.id.classId}. ${r.specification}<br/>
            </#list>
        <#else>
            <@label "l.no.data"/>
        </#if>
    </div>
</#escape>