<#include "../../common/macros/label.ftl">
<#escape x as x?html>
    <th style="width: 5%">№</th>
    <th style="width: 15%"><@label "l.objectId"/></th>
    <th style="width: 10%"><@label "l.filingDate"/></th>
    <th style="width: 20%"><@label "l.title.${registerType}"/></th>
    <th style="width: 10%"><@label "l.protectionNumber.${registerType}"/></th>
    <th style="width: 19%"><@label "l.status"/></th>
    <th style="width: 20%"><@label "l.object.mainOwner.${registerType}"/></th>
</#escape>