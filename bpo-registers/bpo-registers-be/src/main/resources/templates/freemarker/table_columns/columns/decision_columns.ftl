<#escape x as x?html>
    <td>
        ${object.objectId!""}
    </td>
    <td>
        ${object.title!""}
    </td>
    <td>
        <#if lang == "bg">
            ${object.documentType.name!""}
        <#else>
            ${object.documentType.nameEn!""}
        </#if>
    </td>
    <td>
        ${object.documentNumber!""}
    </td>
    <td>
        ${object.documentDate?date.iso?string("dd.MM.yyyy")!""}
    </td>
</#escape>