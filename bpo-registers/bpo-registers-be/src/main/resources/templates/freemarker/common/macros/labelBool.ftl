<#include "label.ftl">
<#macro labelBool _label _value _width="49%">
    <#if _value??>
        <div style="width: ${_width}; display: inline-block; vertical-align: top"><b><@label _label/>:</b> <@label _value?then("l.yes", "l.no")/></div>
    </#if>
</#macro>
