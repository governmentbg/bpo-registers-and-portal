<#include "../../../../common/macros/label.ftl">
<#include "../../../../common/macros/labelText.ftl">
<#include "../../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading 'l.section.ipc.data'/>
        <#if object.ipcClasses?has_content>
            <div style="display: inline-block; width: 100%">
                <#list object.ipcClasses as x>
                    <div style="display: inline-block; width: 32.9%">
                        <#if x.id??>
                            ${x.id.sectionCode +
                            x.id.classCode +
                            x.id.subclassCode +
                            " " +
                            x.id.groupCode +
                            "/" +
                            x.id.subgroupCode +
                            " (" +
                            x.id.editionCode +
                            ")"}
                        </#if>
                    </div>
                </#list>
            </div>
        <#else>
            <@label "l.no.data"/>
        </#if>
    </div>
</#escape>