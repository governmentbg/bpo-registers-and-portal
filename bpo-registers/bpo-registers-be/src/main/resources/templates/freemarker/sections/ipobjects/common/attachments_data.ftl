<#include "../../../common/macros/label.ftl">
<#include "../../../common/macros/labelText.ftl">
<#include "../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>

    <#switch registerType>
        <#case "MARK">
        <#case "EU_PATENT">
        <#case "PATENT">
        <#case "UTILITY_MODEL">
            <#assign types = ["DC"]>
            <#break>
        <#case "PLANT_BREED">
            <#assign types = ["THUMB", "DRAW"]>
            <#break>
        <#default>
            <#assign types = []>
    </#switch>

    <#assign filteredAttachments = []>
    <#if object.ipObject?? && object.ipObject.attachments?has_content>
        <#list object.ipObject.attachments as x>
            <#if x.attachment?? && x.attachment.attachmentType??>
                <#if x.attachment.attachmentType.categories??>
                    <#list x.attachment.attachmentType.categories as category>
                        <#if category?? && category.id?? && types?seq_contains(category.id)>
                            <#assign filteredAttachments = filteredAttachments + [x]>
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </#if>
        </#list>
    </#if>

    <div class="page-unbreakable">
        <@sectionHeading "l.section.attachments.data.${registerType}"/>
        <#if filteredAttachments?has_content>
            <div>
                <table class="table table-striped mt-3">
                    <thead>
                    <tr>
                        <th><@label "l.attachment.attachmentType"/></th>
                        <th><@label "l.attachment.download"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list filteredAttachments as x>
                        <tr>
                            <td>
                                <#if lang == 'bg' && x.attachment?? && x.attachment.attachmentType?? && x.attachment.attachmentType.name??>
                                    ${x.attachment.attachmentType.name}
                                </#if>
                                <#if lang != 'bg' && x.attachment?? && x.attachment.attachmentType?? && x.attachment.attachmentType.nameEn??>
                                    ${x.attachment.attachmentType.nameEn}
                                </#if>
                            </td>
                            <td>
                                <#if x.attachment?? && x.attachment.fileName??>
                                    ${x.attachment.fileName}
                                </#if>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        <#else>
            <@label "l.no.data"/>
        </#if>
    </div>
</#escape>