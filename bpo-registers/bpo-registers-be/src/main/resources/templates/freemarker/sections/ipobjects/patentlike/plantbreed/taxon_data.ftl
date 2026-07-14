<#include "../../../../common/macros/label.ftl">
<#include "../../../../common/macros/labelText.ftl">
<#include "../../../../common/macros/labelBool.ftl">
<#include "../../../../common/macros/labelDate.ftl">
<#include "../../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading 'l.section.taxon.data'/>
        <#if object.taxon??>
            <div style="display: inline-block; width: 100%">
                <@labelText _label="l.taxon.taxonCode" _value=object.taxon.taxonCode!""/>
                <@labelText _label="l.taxon.commonClassifyBg" _value=object.taxon.commonClassifyBg!""/>
                <@labelText _label="l.taxon.latinClassify" _value=object.taxon.latinClassify!"" _width="100%"/>
            </div>
        <#else>
            <@label "l.no.data"/>
        </#if>
    </div>
</#escape>