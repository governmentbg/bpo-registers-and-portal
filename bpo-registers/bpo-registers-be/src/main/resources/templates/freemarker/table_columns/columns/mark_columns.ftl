<#escape x as x?html>
    <td>
        ${object.niceClasses?join(", ")!""}
    </td>
    <td>
        <#if object.hasImg && images?has_content>
            <img style="width: 100%" src="${images[imageCounter]}"/>
            <#assign imageCounter = imageCounter + 1>
        </#if>
    </td>
</#escape>