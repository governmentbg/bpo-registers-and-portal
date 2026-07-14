<#include "../../common/macros/labelText.ftl">
<#include "../../common/macros/labelDate.ftl">
<#include "../../common/macros/labelLabel.ftl">
<#include "../../common/macros/sectionHeading.ftl">
<#escape x as x?html>
    <@sectionHeading 'l.section.filters'/>
    <div style="display: inline-block; width: 100%">
        <#if registerType == "COMBINED" && objectRange?has_content>
            <div>
                <@labelText _label="l.object.range" _value=objectRange _width="100%"/>
            </div>
        </#if>
        <#if filter.objectName?has_content && filter.objectName.text?has_content>
            <div>
                <@labelText _label="l.name.${registerType}" _value=filter.objectName.text!""/>
                <@labelLabel _label="l.searchType" _value=filter.objectName.searchType!""/>
            </div>
        </#if>

        <#if filter.singleDesignName?has_content>
            <div>
                <@labelText _label="l.single.design.name" _value=filter.singleDesignName!""/>
                <@labelLabel _label="l.searchType" _value=filter.singleDesignNameSearchType!""/>
            </div>
        </#if>

        <#if filter.singleDesignVerbalElement?has_content>
            <div>
                <@labelText _label="l.single.design.verbal.element" _value=filter.singleDesignVerbalElement!""/>
                <@labelLabel _label="l.searchType" _value=filter.singleDesignVerbalElementSearchType!""/>
            </div>
        </#if>

        <#if filter.filingNumber?has_content>
            <div>
                <#if filter.filingNumber.from?has_content>
                    <@labelText _label="l.filingNumberFrom.${registerType}" _value=filter.filingNumber.from.name!""/>
                </#if>
                <#if filter.filingNumber.to?has_content>
                    <@labelText _label="l.filingNumberTo.${registerType}" _value=filter.filingNumber.to.name!""/>
                </#if>
            </div>
        </#if>

        <#if filter.filingDate?has_content>
            <div>
                <#if filter.filingDate.from?has_content>
                    <@labelDate _label="l.filingDateFrom.${registerType}" _value=filter.filingDate.from!""/>
                </#if>
                <#if filter.filingDate.to?has_content>
                    <@labelDate _label="l.filingDateTo.${registerType}" _value=filter.filingDate.to!""/>
                </#if>
            </div>
        </#if>

        <#if filter.priority?has_content>
            <div>
                <#if filter.priority.priorityDate?has_content>
                    <#if filter.priority.priorityDate.from?has_content>
                        <@labelDate _label="l.priorityDateFrom.${registerType}" _value=filter.priority.priorityDate.from!""/>
                    </#if>
                    <#if filter.priority.priorityDate.to?has_content>
                        <@labelDate _label="l.priorityDateTo.${registerType}" _value=filter.priority.priorityDate.to!""/>
                    </#if>
                </#if>
                <#if priorityCountry?has_content>
                    <#if lang == "bg">
                        <@labelText _label="l.priorityCountry.${registerType}" _value=priorityCountry.name!""/>
                    <#else>
                        <@labelText _label="l.priorityCountry.${registerType}" _value=priorityCountry.nameEn!""/>
                    </#if>
                </#if>
                <@labelText _label="l.priorityNumber.${registerType}" _value=filter.priority.priorityNumber!""/>
            </div>
        </#if>

        <#if filter.registrationNumber?has_content>
            <div>
                <#if filter.registrationNumber.from?has_content>
                    <@labelText _label="l.registrationNumberFrom.${registerType}" _value=filter.registrationNumber.from.name!""/>
                </#if>
                <#if filter.registrationNumber.to?has_content>
                    <@labelText _label="l.registrationNumberTo.${registerType}" _value=filter.registrationNumber.to.name!""/>
                </#if>
            </div>
        </#if>

        <#if filter.expirationDate?has_content>
            <div>
                <#if filter.expirationDate.from?has_content>
                    <@labelDate _label="l.expirationDateFrom.${registerType}" _value=filter.expirationDate.from!""/>
                </#if>
                <#if filter.expirationDate.to?has_content>
                    <@labelDate _label="l.expirationDateTo.${registerType}" _value=filter.expirationDate.to!""/>
                </#if>
            </div>
        </#if>

        <#if filter.representativeTypes?has_content>
            <div>
                <@labelText _label="l.representativeTypes" _value=filter.representativeTypes?map(x->x.description)?join(", ")!""/>
            </div>
        </#if>
        <#if filter.representativeName?has_content>
            <div>
                <@labelText _label="l.representativeName.${registerType}" _value=filter.representativeName!""/>
                <@labelLabel _label="l.searchType" _value=filter.representativeNameSearchType!""/>
            </div>
        </#if>
        <#if filter.representatives?has_content>
            <div>
                <@labelText _label="l.representatives.${registerType}" _value=filter.representatives?map(x->x.name + " (" + x.agentCode + ")")?join(", ")!""/>
            </div>
        </#if>

        <#if filter.niceClasses?has_content>
            <#if filter.niceClasses.niceClassCodes?has_content>
                <div>
                    <@labelText _label="l.niceClasses" _value=filter.niceClasses.niceClassCodes?join(", ")!""/>
                    <@labelLabel _label="l.searchType" _value=filter.niceClasses.niceClassCodeType!""/>
                </div>
            </#if>
            <#if filter.niceClasses.specification?has_content>
                <div>
                    <@labelText _label="l.specification" _value=filter.niceClasses.specification.text!""/>
                    <@labelLabel _label="l.searchType" _value=filter.niceClasses.specification.searchType!""/>
                </div>
            </#if>
        </#if>

        <#if filter.viennaClasses?has_content && filter.viennaClasses.viennaClasses?has_content>
            <div>
                <@labelText _label="l.viennaClasses" _value=filter.viennaClasses.viennaClasses?map(x-> x.id + " - " + x.name)?join(", ")!""/>
                <@labelLabel _label="l.searchType" _value=filter.viennaClasses.viennaClassCodeType!""/>
            </div>
        </#if>

        <#if filter.ipcClasses?has_content>
            <div>
                <@labelText _label="l.ipcClasses" _value=filter.ipcClasses.ipcClasses?map(x->x.id.sectionCode + x.id.classCode + x.id.subclassCode + " " + x.id.groupCode + "/" + x.id.subgroupCode + " (" + x.id.editionCode + ")")?join(", ")!""/>
                <@labelLabel _label="l.searchType" _value=filter.ipcClasses.ipcClassOperatorType!""/>
            </div>
        </#if>
        <#if filter.ipcCode?has_content>
            <div>
                <@labelText _label="l.ipcCode" _value=filter.ipcCode!""/>
            </div>
        </#if>

        <#if filter.cpcClasses?has_content>
            <div>
                <@labelText _label="l.cpcClasses" _value=filter.cpcClasses.cpcClasses?map(x->x.id.sectionCode + x.id.classCode + x.id.subclassCode + " " + x.id.groupCode + "/" + x.id.subgroupCode + " (" + x.id.editionCode + ")")?join(", ")!""/>
                <@labelLabel _label="l.searchType" _value=filter.cpcClasses.cpcClassOperatorType!""/>
            </div>
        </#if>
        <#if filter.cpcCode?has_content>
            <div>
                <@labelText _label="l.cpcCode" _value=filter.cpcCode!""/>
            </div>
        </#if>

        <#if filter.applicantOwner?has_content>
            <div>
                <@labelText _label="l.applicantOwner.${registerType}" _value=filter.applicantOwner!""/>
                <@labelLabel _label="l.searchType" _value=filter.applicantOwnerPersonSearchType!""/>
                <#if applicantOwnerCountry?has_content>
                    <#if lang == "bg">
                        <@labelText _label="l.applicantOwnerCountry" _value=applicantOwnerCountry.name!""/>
                    <#else>
                        <@labelText _label="l.applicantOwnerCountry" _value=applicantOwnerCountry.nameEn!""/>
                    </#if>
                </#if>
            </div>
        </#if>

        <#if filter.inventor?has_content>
            <div>
                <@labelText _label="l.inventor.${registerType}" _value=filter.inventor!""/>
                <@labelLabel _label="l.searchType" _value=filter.inventorPersonSearchType!""/>
            </div>
        </#if>

        <#if filter.status?has_content>
            <div>
                <#if lang == "bg">
                    <@labelText _label="l.status" _value=filter.status.bpoOnlineStatus!""/>
                <#else>
                    <@labelText _label="l.status" _value=filter.status.bpoOnlineStatusEn!""/>
                </#if>
            </div>
        </#if>

        <#if filter.publications?has_content>
            <div>
                <@labelText _label="l.publicationYear.${registerType}" _value=filter.publications.publicationYear!""/>
                <@labelText _label="l.publicationNumber.${registerType}" _value=filter.publications.publicationNumber!""/>
                <#if publicationSection?has_content>
                    <#if lang == "bg">
                        <@labelText _label="l.publicationSection.${registerType}" _value=publicationSection.nmsection!""/>
                    <#else>
                        <@labelText _label="l.publicationSection.${registerType}" _value=publicationSection.nmsectionEn!""/>
                    </#if>
                </#if>
                <#if filter.publications.publicationDate?has_content>
                    <div>
                        <#if filter.publications.publicationDate.from?has_content>
                            <@labelDate _label="l.publicationDateFrom.${registerType}" _value=filter.publications.publicationDate.from!""/>
                        </#if>
                        <#if filter.publications.publicationDate.to?has_content>
                            <@labelDate _label="l.publicationDateTo.${registerType}" _value=filter.publications.publicationDate.to!""/>
                        </#if>
                    </div>
                </#if>
            </div>
        </#if>

        <@labelText _label="l.bgClassification" _value=filter.bgClassification!""/>
        <@labelText _label="l.latinClassification" _value=filter.latinClassification!""/>

        <#if filter.markKind?has_content>
            <div>
                <#if lang == "bg">
                    <@labelText _label="l.markKind" _value=filter.markKind.description!""/>
                <#else>
                    <@labelText _label="l.markKind" _value=filter.markKind.descriptionEn!""/>
                </#if>
            </div>
        </#if>

        <#if objectSubtypes?has_content>
            <div>
                <#if lang == "bg">
                    <@labelText _label="l.markType.${registerType}" _value=objectSubtypes?map(x -> x.description)?join(", ")!""/>
                <#else>
                    <@labelText _label="l.markType.${registerType}" _value=objectSubtypes?map(x -> x.descriptionEn)?join(", ")!""/>
                </#if>
            </div>
        </#if>

        <#if filter.abstractDetails?has_content && filter.abstractDetails.text?has_content>
            <div>
                <@labelText _label="l.abstract.${registerType}" _value=filter.abstractDetails.text!""/>
                <@labelLabel _label="l.searchType" _value=filter.abstractDetails.searchType!""/>
            </div>
        </#if>

        <@labelText _label="l.maxResults" _value=maxResultsCount!""/>
    </div>
</#escape>