<#include "../../../common/macros/label.ftl">
<#include "../../../common/macros/labelText.ftl">
<#include "../../../common/macros/sectionHeading.ftl">
<#include "../../../common/macros/sectionSubheading.ftl">
<#escape x as x?html>

    <#assign singleDesigns = object.singleDesigns![]>

    <div class="page-unbreakable">
        <@sectionHeading "l.section.single.designs.data"/>
        <#if singleDesigns?has_content>
            <div>
                <#list singleDesigns as x>
                    <@sectionSubheading (lang=='bg')?then(x.name!"", x.nameEn!"") + " [" + x.alternateKey + "] " + (lang=='bg')?then(x.status.bpoOnlineStatus!"", x.status.bpoOnlineStatusEn!"")/>
                    <@labelText _label="l.single.design.identifier" _value=x.id/>
                    <@labelText _label="l.single.design.alternateKey" _value=x.alternateKey/>
                    <#if x.designType?has_content>
                        <@labelText _label="l.single.design.designType" _value=(lang=='bg')?then(x.designType.description!"", x.designType.descriptionEn!"")/>
                    </#if>
                    <#if x.status?has_content>
                        <@labelText _label="l.single.design.status" _value=(lang=='bg')?then(x.status.bpoOnlineStatus!"", x.status.bpoOnlineStatusEn!"")/>
                    </#if>
                    <@labelText _label="l.single.design.name.view" _value=x.name/>
                    <@labelText _label="l.single.design.nameEn" _value=x.nameEn/>
                    <#if x.locarnos?has_content>
                        <@labelText _label="l.single.design.locarno" _value=x.locarnos?map(x->x.id.locarnoClassCode)?join("; ")!""/>
                    </#if>
                    <@labelText _label="l.single.design.verbal.element.view" _value=x.verbalElement/>
                    <@labelText _label="l.single.design.verbal.element.en.view" _value=x.verbalElementEn/>

                    <div style="width: 100%">
                        <#if x.drawings?has_content>
                            <#list x.drawings as img>
                                <#if img.attachment?? && img.attachment.bucketName?? && img.attachment.fileLocation?? && img.attachment.attachmentType.id??>
                                    <#if img.attachment.attachmentType.id == 'IMAGE'>
                                        <div style="width: 40%; display: inline-block">
                                            <div>
                                                <#if img.viewType?has_content>
                                                    ${img.viewType.viewTypeName}
                                                </#if>
                                            </div>
                                            <img style="width: 100%" data-fullPath="${img.attachment.fileLocation}"
                                                 data-bucket="${img.attachment.bucketName}"/>
                                        </div>
                                    </#if>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                </#list>
            </div>
        <#else>
            <@label "l.no.data"/>
        </#if>
    </div>
</#escape>