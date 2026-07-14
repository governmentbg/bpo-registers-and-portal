<#escape x as x?html>
    <td>
        <#if lang == "bg">
            ${object.name!""}
        <#else>
            ${object.agent.nameEn!""}
        </#if>
    </td>
    <td>
        ${object.agent.agentCode!""}
    </td>
    <td>
        <#if lang == "bg">
            ${object.agent.agentSpeciality.name!""}
        <#else>
            ${object.agent.agentSpeciality.nameEn!""}
        </#if>
    </td>
    <td>
        <#if lang == "bg">
            ${object.agent.agentStatus.name!""}
        <#else>
            ${object.agent.agentStatus.nameEn!""}
        </#if>
    </td>
    <td>
        <#if lang == "bg">
            ${object.agent.representativeType.description!""}
        <#else>
            ${object.agent.representativeType.descriptionEn!""}
        </#if>
    </td>
</#escape>