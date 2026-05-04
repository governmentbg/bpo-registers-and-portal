<#include "label.ftl">
<#escape x as x?html>
    <#macro labelLabel _label _value _width="49%" _defaultValue=''>
        <#if _value?? && _value?has_content>
            <div style="width: ${_width}; display: inline-block; vertical-align: top"><b><@label _label/>:</b> <@label "l."+_value/></div>
        <#elseif (!_value?? || !_value?has_content) && _defaultValue?has_content>
            <div style="width: ${_width}; display: inline-block; vertical-align: top"><b><@label _label/>:</b> <@label _defaultValue/></div>
        </#if>
    </#macro>
</#escape>