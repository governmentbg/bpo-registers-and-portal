<#include "../../common/macros/labelText.ftl">
<#include "../../common/macros/labelDate.ftl">
<#include "../../common/macros/labelLabel.ftl">
<#include "../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <@sectionHeading 'l.section.filters'/>
    <div style="display: inline-block; width: 100%">
        <@labelText _label="l.name" _value=filter.name!""/>

        <#if objectType?has_content>
            <#if lang == "bg">
                <@labelText _label="l.objectType" _value=objectType.name!""/>
            <#else>
                <@labelText _label="l.objectType" _value=objectType.nameEn!""/>
            </#if>
        </#if>

        <@labelText _label="l.objectId" _value=filter.objectId!""/>

        <#if filter.documentDate?has_content>
            <div>
                <#if filter.documentDate.from?has_content>
                    <@labelDate _label="l.documentDateFrom" _value=filter.documentDate.from!""/>
                </#if>
                <#if filter.documentDate.to?has_content>
                    <@labelDate _label="l.documentDateTo" _value=filter.documentDate.to!""/>
                </#if>
            </div>
        </#if>

        <@labelText _label="l.documentNumber" _value=filter.documentNumber!""/>

        <#if filter.documentType?has_content>
            <#if lang == "bg">
                <@labelText _label="l.documentType" _value=filter.documentType.name!""/>
            <#else>
                <@labelText _label="l.documentType" _value=filter.documentType.nameEn!""/>
            </#if>
        </#if>

        <#if filter.legalGroundTypes?has_content>
            <#if lang == "bg">
                <@labelText _label="l.ground.types" _value=filter.legalGroundTypes?map(x->x.name)?join("; ")!""/>
            <#else>
                <@labelText _label="l.ground.types" _value=filter.legalGroundTypes?map(x->x.nameEn)?join("; ")!""/>
            </#if>
            <@labelLabel _label="l.searchType" _value=filter.legalGroundTypesOperatorType!""/>
        </#if>

        <@labelText _label="l.maxResults" _value=maxResultsCount!""/>
    </div>
</#escape>