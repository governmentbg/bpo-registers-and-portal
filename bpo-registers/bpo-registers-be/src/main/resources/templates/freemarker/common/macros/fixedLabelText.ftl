<#include "label.ftl">
<#macro fixedLabelText _label _value _width="49%" _defaultValue=''>
    <#if _value?? && _value?has_content>
        <div style="width: ${_width}; display: inline-block; vertical-align: top"><b>${_label}:</b> ${_value}</div>
    <#elseif (!_value?? || !_value?has_content) && _defaultValue?has_content>
        <div style="width: ${_width}; display: inline-block; vertical-align: top"><b>${_label}:</b> <@label _defaultValue/></div>
    </#if>
</#macro>
