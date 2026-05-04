<#include "../../common/macros/labelText.ftl">
<#include "../../common/macros/labelDate.ftl">
<#include "../../common/macros/labelLabel.ftl">
<#include "../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <@sectionHeading 'l.section.filters'/>
    <div style="display: inline-block; width: 100%">
        <@labelText _label="l.name" _value=filter.name!""/>

        <#if ipoArea?has_content>
            <#if lang == "bg">
                <@labelText _label="l.ipoArea" _value=ipoArea.name!""/>
            <#else>
                <@labelText _label="l.ipoArea" _value=ipoArea.nameEn!""/>
            </#if>
        </#if>

        <@labelText _label="l.agentCode" _value=filter.agentCode!""/>

        <#if status?has_content>
            <#if lang == "bg">
                <@labelText _label="l.status" _value=status.name!""/>
            <#else>
                <@labelText _label="l.status" _value=status.nameEn!""/>
            </#if>
        </#if>

        <@labelText _label="l.agentSpeciality" _value=filter.agentSpeciality!""/>

        <@labelText _label="l.maxResults" _value=maxResultsCount!""/>
    </div>
</#escape>