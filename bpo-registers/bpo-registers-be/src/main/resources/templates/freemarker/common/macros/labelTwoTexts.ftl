<#include "label.ftl">
<#escape x as x?html>
    <#macro labelTwoTexts _label _value _value2 _width="100%" _defaultValue=''>
        <#if _value?? && _value?has_content>
            <div style="width: ${_width}; display: inline-block; vertical-align: top"><b><@label _label/>:</b> ${_value} <#if _value2?? && _value2?has_content>(${_value2})</#if></div>
        <#elseif (!_value?? || !_value?has_content) && _defaultValue?has_content>
            <div style="width: ${_width}; display: inline-block; vertical-align: top"><b><@label _label/>:</b> <@label _defaultValue/></div>
        </#if>
    </#macro>
</#escape>