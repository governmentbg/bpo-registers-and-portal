<#include "../../../common/macros/label.ftl">
<#include "../../../common/macros/labelText.ftl">
<#include "../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>

    <#assign types = ["DRAW"]>

    <#switch registerType>
        <#case "MARK">
            <#assign attachments = object.markAttachments![]>
            <#break>
        <#case "GEO_INDICATION">
            <#assign attachments = object.ipObject.attachments![]>
            <#break>
        <#default>
            <#assign attachments = []>
    </#switch>

    <#assign images = []>
    <#if attachments?has_content>
        <#list attachments as x>
            <#if x.attachment?? && x.attachment.attachmentType??>
                <#if x.attachment.attachmentType.categories??>
                    <#list x.attachment.attachmentType.categories as category>
                        <#if category?? && category.id?? && types?seq_contains(category.id)>
                            <#assign images = images + [x]>
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </#if>
        </#list>
    </#if>

    <#assign wordElements = "">
    <#if registerType=='MARK'>
        <#if lang == 'bg'>
            <#assign wordElements = object.ipObject.title!"">
        <#else>
            <#if object.markTranslation?has_content>
                <#assign wordElements = object.markTranslation>
            <#elseif object.markTransliteration?has_content>
                <#assign wordElements = object.markTransliteration>
            <#else>
                <#assign wordElements = object.ipObject.title!"">
            </#if>
        </#if>
    </#if>

    <div class="page-unbreakable">
        <@sectionHeading "l.section.images.data.${registerType}"/>
        <#if images?has_content || wordElements?has_content>
            <div>
                <#if images?has_content>
                    <#list images as x>
                        <#if x.attachment?? && x.attachment.bucketName?? && x.attachment.fileLocation?? && x.attachment.attachmentType.id??>
                            <#if x.attachment.attachmentType.id == 'IMAGE'>
                                <div style="width: 40%; display: inline-block">
                                    <#if registerType=='MARK'>
                                        <div>
                                            <@label "l.mark.image.inid.code"/>
                                        </div>
                                    </#if>
                                    <img style="width: 100%" data-fullPath="${x.attachment.fileLocation}"
                                         data-bucket="${x.attachment.bucketName}"/>
                                </div>
                            <#elseif x.attachment.attachmentType.id == 'VIDEO'>
                                <@label "l.video.file"/>
                            <#elseif x.attachment.attachmentType.id == 'AUDIO'>
                                <@label "l.audio.file"/>
                            </#if>
                        </#if>
                    </#list>
                </#if>
                <#if wordElements?has_content>
                    <div>
                        <#if object.markKind?? && object.markKind.id == "D">
                            ${wordElements}
                        <#else>
                            <@labelText _label="l.word.elements" _value=wordElements/>
                        </#if>
                    </div>
                </#if>
            </div>
        <#else>
            <@label "l.no.data"/>
        </#if>
    </div>
</#escape>