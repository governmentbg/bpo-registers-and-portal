<#include "../../common/macros/label.ftl">
<#escape x as x?html>
    <th style="width: 5%">№</th>
    <th style="width: 14%"><@label "l.objectId"/></th>
    <th style="width: 10%"><@label "l.filingDate"/></th>
    <th style="width: 14%"><@label "l.title"/></th>
    <th style="width: 9%"><@label "l.registrationNumber"/></th>
    <th style="width: 14%"><@label "l.status"/></th>
    <th style="width: 14%"><@label "l.object.mainOwner.MARK"/></th>
    <th style="width: 9%"><@label "l.niceClasses.h"/></th>
    <th style="width: 10%"><@label "l.object.image"/></th>
    <#assign imageCounter = 0>
</#escape>