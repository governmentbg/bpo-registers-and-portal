<#include "label.ftl">
<#macro labelDate _label _value _width="49%">
    <#if _value?? && _value?has_content>
        <div style="width: ${_width}; display: inline-block; vertical-align: top"><b><@label _label/>:</b> ${_value?date.iso?string("dd.MM.yyyy")}</div>
    </#if>
</#macro>
