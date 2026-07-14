<#include "../../../common/macros/label.ftl">
<#include "../../../common/macros/labelText.ftl">
<#include "../../../common/macros/labelBool.ftl">
<#include "../../../common/macros/labelDate.ftl">
<#include "../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading 'l.section.main.data.${registerType}'/>
        <div style="display: inline-block; width: 100%">

            <#if registerType == "EU_PATENT">
                <#if object.ipObject.registrationNumber??>
                    <#assign registrationNumber = "EP" + object.ipObject.registrationNumber>
                </#if>
            <#else>
                <#assign registrationNumber = object.ipObject.registrationNumber!"">
            </#if>

            <@labelText _label="l.object.applicationNumber.${registerType}" _value=object.id!""/>
            <@labelDate _label="l.object.filingDate.${registerType}" _value=object.ipObject.filingDate!""/>
            <@labelText _label="l.object.registrationNumber.${registerType}" _value=registrationNumber!""/>
            <@labelDate _label="l.object.registrationDate.${registerType}" _value=object.ipObject.registrationDate!""/>
            <@labelDate _label="l.object.expirationDate.${registerType}" _value=object.ipObject.expirationDate!""/>

            <#if object.ipObject.status??>
                <@labelText _label="l.object.status" _value=(lang=='bg')?then(object.ipObject.status.bpoOnlineStatus!"", object.ipObject.status.bpoOnlineStatusEn!"")/>
            </#if>

            <@labelDate _label="l.object.notInForceDate" _value=object.notInForceDate!""/>

            <#if registerType != "MARK">
                <#if registerType == "GEO_INDICATION">
                    <#if lang == "bg">
                        <@labelText _label="l.object.title.${registerType}" _value=object.ipObject.title!"" _width="100%"/>
                    <#else>
                        <#if object.markTranslation?has_content>
                            <@labelText _label="l.object.title.${registerType}" _value=object.markTranslation _width="100%"/>
                        <#elseif object.markTransliteration?has_content>
                            <@labelText _label="l.object.title.${registerType}" _value=object.markTransliteration _width="100%"/>
                        <#else>
                            <@labelText _label="l.object.title.${registerType}" _value=object.ipObject.title!"" _width="100%"/>
                        </#if>
                    </#if>
                <#else>
                    <@labelText _label="l.object.title.${registerType}" _value=object.ipObject.title!"" _width="100%"/>
                </#if>
            </#if>

            <#if registerType == "DESIGN">
                <@labelText _label="l.object.title.en" _value=object.ipObject.titleEn!"" _width="100%"/>
                <#if object.ipObject.objectSubType?has_content>
                    <@labelText _label="l.object.subtype" _value=object.ipObject.objectSubType.description!""/>
                </#if>
            </#if>

            <#if registerType == "SPC">
                <@labelText _label="l.object.mainAbstract" _value=object.mainAbstract!"" _width="100%"/>
            </#if>

            <#if ['MARK', 'GEO_INDICATION']?seq_contains(registerType)>
                <@labelText _label="l.object.disclaimer" _value=object.disclaimer!"" _defaultValue="l.no.declared"/>
                <@labelBool _label="l.object.acquired.distinctiveness" _value=object.acquiredDistinctiveness!false/>
                <#if object.markKind??>
                    <@labelText _label="l.object.markKind" _value=(lang=='bg')?then(object.markKind.description!"", object.markKind.descriptionEn!"")/>
                </#if>
                <#if object.ipObject.objectType??>
                    <@labelText _label="l.object.markType" _value=(lang=='bg')?then(object.ipObject.objectType.description!"", object.ipObject.objectType.descriptionEn!"")/>
                </#if>
            </#if>
            <#if ['PATENT', 'SPC','UTILITY_MODEL','EU_PATENT']?seq_contains(registerType)>
                <@labelText _label="l.object.lastPaidYear" _value=object.lastPaidYear!"" />
                <@labelDate _label="l.object.paymentDate" _value=object.renewalFeesLastPaid!""/>
                <@labelDate _label="l.object.renewalFeesPaidTo" _value=object.renewalFeesPaidTo!""/>
            </#if>
        </div>
    </div>
</#escape>