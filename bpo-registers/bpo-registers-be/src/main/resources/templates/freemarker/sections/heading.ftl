<#include "../common/macros/label.ftl">
<#escape x as x?html>
    <div style="margin-bottom: 20px; display: inline-block">
        <div style="width: 40%; display: inline-block">
        <span style="display: block; text-align: left; font-weight: bold; font-size: large">
            <@label "l.bpo.electronic.services"/>
        </span>
        </div>
        <div style="width: 59%; display: inline-block">
            <div style="display: block; text-align: right; font-weight: bold; font-size: medium">
                <@label "l.${registerType}.receipt"/>
            </div>
            <div style="display: block; text-align: right; font-weight: bold; font-size: medium">
                <#assign now = .now>
                ${now?string("dd.MM.yyyy HH:mm:ss")}
            </div>
        </div>
    </div>

</#escape>