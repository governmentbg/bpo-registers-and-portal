<#include "../../../common/macros/label.ftl">
<#include "../../../common/macros/labelText.ftl">
<#include "../../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <div class="page-unbreakable">
        <@sectionHeading 'l.section.publications.data.${registerType}'/>
        <#if object.ipObject.publications?has_content>
            <div>
                <table class="table table-striped mt-3">
                    <thead>
                    <tr>
                        <th><@label "l.publication.journal"/></th>
                        <th><@label "l.publication.date"/></th>
                        <th><@label "l.publication.section"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list object.ipObject.publications as x>
                        <tr>
                            <td>
                                <#if x.publicationNumber?? && x.publicationYear??>
                                    ${x.publicationNumber} / ${x.publicationYear?string("#")}
                                </#if>
                            </td>
                            <td>
                                <#if x.publicationDate??>
                                    ${x.publicationDate?date.iso?string("dd.MM.yyyy")}
                                </#if>
                            </td>
                            <td>
                                <#if x.publicationSection?? && x.publicationSection.nmsection??>
                                    ${x.publicationSection.nmsection}
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